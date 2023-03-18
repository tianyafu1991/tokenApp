package com.hwly.tokenapp.response;

import java.io.Serializable;

/**
 * 回调返回值结果
 */
public class CallBackResponse implements Serializable {

    private Boolean success;

    private String msg;

    /**
     * 成功结果的快速生成
     * @return
     */
    public static CallBackResponse  successInstance() {
        CallBackResponse response = new CallBackResponse();
        response.setSuccess(Boolean.TRUE);
        response.setMsg("处置结果推送成功");
        return response;
    }

    /**
     * 失败结果的快速生成
     * @param msg
     * @return
     */
    public static CallBackResponse  errorInstance(String msg) {
        CallBackResponse response = new CallBackResponse();
        response.setSuccess(Boolean.FALSE);
        response.setMsg(msg);
        return response;
    }

    public CallBackResponse() {
    }

    public CallBackResponse(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CallBackResponse{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
