package com.wg.demo;

import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.EmployeeMapper;
import com.wg.demo.po.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrefApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IdWorker idWorker;


    public static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int randomNum;
        char randomChar;
        Random random = new Random();
        // StringBuffer类型的可以append增加字符
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < length; i++) {
            // 可生成[0,n)之间的整数，获得随机位置
            randomNum = random.nextInt(base.length());
            // 获得随机位置对应的字符
            randomChar = base.charAt(randomNum);
            // 组成一个随机字符串
            str.append(randomChar);
        }
        return str.toString();
    }
    Employee getRadomEmployee()
    {
        Employee employee = new Employee();
        employee.setAddress("北新街" + getRandomStr(5));
        employee.setAge("22");
        employee.setGender(new Short("1"));
        employee.setCreateTime(new Date());
        employee.setName(getRandomStr(10));
        employee.setId(idWorker.nextId());
        return employee;
    }
    @Test
    public void contextLoads() {

        for(int i=0;i<200;i++)
        {
            employeeMapper.insert(getRadomEmployee());
        }

    }

}

