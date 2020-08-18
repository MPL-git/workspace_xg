package com.jf.vo;

/**
 * @author luoyb
 * Created on 2019/10/18
 */
public class ResponseMsg {

    public static final String SUCCESS = "0000";
    public static final String ERROR = "4004";
    public static final String SUCCESS_MSG = "操作成功";
    public static final String ERROR_MSG = "操作失败";
    private String returnCode;
    private String returnMsg;
    private Object returnData=null;

    public ResponseMsg(){
    }
    public ResponseMsg(String returnCode,String returnMsg){
        this.returnCode=returnCode;
        this.returnMsg=returnMsg;
    }
    public ResponseMsg(String returnCode,String returnMsg,Object returnData){
        this.returnCode=returnCode;
        this.returnMsg=returnMsg;
        this.returnData=returnData;
    }
    public static ResponseMsg success() {
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }

    public static ResponseMsg success(Object data) {
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, data);
    }

    public static ResponseMsg error(String msg) {
        return new ResponseMsg(ResponseMsg.ERROR, msg);
    }

    public static ResponseMsg error(String code, String msg) {
        return new ResponseMsg(code, msg);
    }

    public static ResponseMsg error(String code, String msg, Object data) {
        return new ResponseMsg(code, msg, data);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
