package com.wg.demo.common;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wanggang
 * @Date: 2018/9/4 17:09
 * @todo
 */
public class ResultMsg {
    /**
     * result : success
     * resultCode : 200  404  500
     * resultMsg : 成功   失败   为啥~
     * total : 0   数量
     * list :
     * map : 同上
     * data : 数据
     */
    private String result;
    private int resultCode;
    private String resultMsg;
    private Object data;

    public ResultMsg() {
        this.result = "SUCCESS";
        this.resultCode = 200;

    }

    public static ResultMsg getMsg() {
        ResultMsg msg = new ResultMsg();
        return msg;
    }

    public static ResultMsg getFailedMsg(String result) {
        ResultMsg msg = new ResultMsg();
        msg.setResult("FAILED");
        msg.setResultMsg(result);
        return msg;
    }

    public static ResultMsg getMapMsg(Object data) {
        ResultMsg msg = new ResultMsg();
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        msg.setData(data);
        return msg;
    }

    public static ResultMsg getStrMsg(String strMsg) {
        ResultMsg msg = new ResultMsg();
        msg.setResult(strMsg);
        msg.setData(strMsg);
        return msg;
    }
    public static ResultMsg getMsg(Object data) {
        ResultMsg msg = new ResultMsg();
        msg.setData(data);
        return msg;
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }


    public void setResultCode(int resultCode) {
        if (resultCode != 200)
            this.result = "FAILED";
        else
            this.result = "SUCCESS";
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "result='" + result + '\'' +
                ", resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +

                ", data=" + data +
                '}';
    }
}
