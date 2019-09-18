package com.wg.demo.common.interceptor;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by chenboge on 2017/5/14.
 * <p>
 * Email:baigegechen@gmail.com
 * <p>
 * description:实现分页的辅助类，用于封装用于分页的一些参数
 */
@Data
public class PageParam {
    public PageParam() {
        pageSize=0;
        pageNum=0;
        total = 0;
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    private Integer pageNum;
    //    默认每页显示条数
    private Integer pageSize;
    //    是否启用分页功能
    @JSONField(serialize = false)
    private Boolean useFlag;
    //    是否检测当前页码的合法性（大于最大页码或小于最小页码都不合法）
    @JSONField(serialize = false)
    private Boolean checkFlag;
    //当前sql查询的总记录数，回填
    private Integer total;
    //    当前sql查询实现分页后的总页数，回填
    private Integer totalPage;
    @JSONField(serialize = false)
    private String orderColumn;
    @JSONField(serialize = false)
    private String order;

    public Boolean isUseFlag() {
        return useFlag;
    }

    public Boolean istCheckFlag() {
        return checkFlag;
    }

}
