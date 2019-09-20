package com.wg.demo.common.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chenboge on 2017/5/14.
 * <p>
 * Email:baigegechen@gmail.com
 * <p>
 * description:插件分页
 */
@Component
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PageInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private PageConfig pageConfig;
    //    默认页码
    private Integer defaultPageNum = 1;
    //    默认每页显示条数
    private Integer defaultPageSize = 20;
    //    是否启用分页功能
    private boolean defaultUseFlag = true;
    //    检测当前页码的合法性（大于最大页码或小于最小页码都不合法）
    private boolean defaultCheckFlag = true;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getActuralHandlerObject(invocation);
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        String sql = statementHandler.getBoundSql().getSql();

        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        if (!checkIsSelectFalg2(paramObject)) {
            return invocation.proceed();
        }
        logger.info("Mybatis 分页插件当前数据源为: " + pageConfig.getDateSource());
        logger.info("Mybatis 分页插件当前数据源方言为: " + pageConfig.getDialect());
        PageParam pageParam = getPageParam(paramObject);

        if (pageParam == null)
            return invocation.proceed();

        Integer pageNum = pageParam.getPageNum() == null ? defaultPageNum : pageParam.getPageNum();
        Integer pageSize = pageParam.getPageSize() == null ? defaultPageSize : pageParam.getPageSize();

        Boolean useFlag = pageParam.isUseFlag() == null ? defaultUseFlag : pageParam.isUseFlag();
        Boolean checkFlag = pageParam.istCheckFlag() == null ? defaultCheckFlag : pageParam.istCheckFlag();

        //不使用分页功能
        if (!useFlag) {
            return invocation.proceed();
        }

        int totle = getTotal(invocation, metaStatementHandler, boundSql);

        //将动态获取到的分页参数回填到pageParam中
        setTotltToParam(pageParam, totle, pageSize);

        //检查当前页码的有效性
        //checkPage(checkFlag, pageNum, pageParam.getTotalPage());

        //修改sql
        return updateSql2Limit(invocation, metaStatementHandler, boundSql, pageParam);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    //    在配置插件的时候配置默认参数
    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("defaultPageNum");
        String strDefaultPageSize = properties.getProperty("defaultPageSize");
        String strDefaultUseFlag = properties.getProperty("defaultUseFlag");
        String strDefaultCheckFlag = properties.getProperty("defaultCheckFlag");
        defaultPageNum = Integer.valueOf(strDefaultPage);
        defaultPageSize = Integer.valueOf(strDefaultPageSize);
        defaultUseFlag = Boolean.valueOf(strDefaultUseFlag);
        defaultCheckFlag = Boolean.valueOf(strDefaultCheckFlag);
    }


    //    从代理对象中分离出真实statementHandler对象,非代理对象
    private StatementHandler getActuralHandlerObject(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        Object object = null;
//        分离代理对象链，目标可能被多个拦截器拦截，分离出最原始的目标类
        while (metaStatementHandler.hasGetter("h")) {
            object = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }

        if (object == null) {
            return statementHandler;
        }
        return (StatementHandler) object;
    }

    //    判断是否是select语句，只有select语句，才会用到分页
    private boolean checkIsSelectFalg(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }

    //有分页参数，则启动分页
    private boolean checkIsSelectFalg2(Object paramerObject) {
        boolean result = false;
        if (paramerObject == null) {
            return false;
        }
        if (paramerObject instanceof Map) {
            Map<String, Object> params = (Map<String, Object>) paramerObject;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof PageParam) {
                    result = true;
                    break;
                }
            }
        } else if (paramerObject instanceof PageParam) {
//            继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
            result = true;
        }
        return result;
    }

    /*
    获取分页的参数
    参数可以通过map，@param注解进行参数传递。或者请求pojo继承自PageParam  将PageParam中的分页数据放进去
     */
    private PageParam getPageParam(Object paramerObject) {
        if (paramerObject == null) {
            return null;
        }

        PageParam pageParam = null;
        //通过map和@param注解将PageParam参数传递进来，pojo继承自PageParam不推荐使用  这里从参数中提取出传递进来的pojo继承自PageParam
//        首先处理传递进来的是map对象和通过注解方式传值的情况，从中提取出PageParam,循环获取map中的键值对，取出PageParam对象
        if (paramerObject instanceof Map) {
            Map<String, Object> params = (Map<String, Object>) paramerObject;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof PageParam) {
                    return (PageParam) entry.getValue();
                }
            }
        } else if (paramerObject instanceof PageParam) {
//            继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
            pageParam = (PageParam) paramerObject;

        }
        return pageParam;
    }

    //    获取当前sql查询的记录总数
    private int getTotal(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql) {
//        获取mapper文件中当前查询语句的配置信息
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        //获取所有配置Configuration
        org.apache.ibatis.session.Configuration configuration = mappedStatement.getConfiguration();

//        获取当前查询语句的sql
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

//        将sql改写成统计记录数的sql语句,这里是mysql的改写语句,将第一次查询结果作为第二次查询的表
        String sqlParma = pageConfig.getTotalSqlParam();
        String countSql = "select count(*) as total from (" + sql + ") " + sqlParma;
//        获取connection连接对象，用于执行countsql语句
        Connection conn = (Connection) invocation.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;

        try {
//            预编译统计总记录数的sql
            ps = conn.prepareStatement(countSql);
            //构建统计总记录数的BoundSql
            BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //构建ParameterHandler，用于设置统计sql的参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //设置总数sql的参数
            parameterHandler.setParameters(ps);
            //执行查询语句
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                与countSql中设置的别名对应
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null)
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return total;
    }

    //    设置条数参数到pageparam对象
    private void setTotltToParam(PageParam param, int totle, int pageSize) {
        param.setTotal(totle);
        param.setTotalPage(totle % pageSize == 0 ? totle / pageSize : (totle / pageSize) + 1);
    }

    //    修改原始sql语句为分页sql语句
    private Object updateSql2Limit(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, PageParam pageParam) throws InvocationTargetException, IllegalAccessException, SQLException {
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //构建新的分页sql语句
        String sqlParma = pageConfig.getSelectSqlParam(pageParam);
        String limitSql = "select * from (" + sql + ") " + sqlParma;
        //修改当前要执行的sql语句
        metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
        //相当于调用prepare方法，预编译sql并且加入参数，但是少了分页的两个参数，它返回一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //获取sql总的参数总数
        //int count = ps.getParameterMetaData().getParameterCount();
        //ParameterMetaData pdata = ps.getParameterMetaData();
        //设置与分页相关的两个参数
        //Map param = pageConfig.getLimitParam(pageParam);
        //ps.setInt(count - 1, (Integer) param.get("first")); //第一个参数
        //ps.setInt(count, (Integer) param.get("second"));////第二个参数
        return ps;
    }

    //    验证当前页码的有效性
    private void checkPage(boolean checkFlag, Integer pageNumber, Integer pageTotle) throws Exception {
        if (checkFlag) {
            if (pageNumber > pageTotle) {
                throw new Exception("查询失败，查询页码" + pageNumber + "大于总页数" + pageTotle);
            }
            if (pageNumber < 0) {
                throw new Exception("查询失败，查询页码小于0 " + pageNumber );
            }
        }
    }
}
