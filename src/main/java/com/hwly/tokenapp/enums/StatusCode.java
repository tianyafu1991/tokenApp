package com.hwly.tokenapp.enums;

/**
 *@author  lh
 *@author email  emailyapeng@sina.com
 * 返回给客户端的状态码和状态信息
 */
public enum StatusCode {

    /**
     * 操作成功
     */
    OPERATION_SUCCESS(200, "操作成功"),

    /**
     * 部分推送失败
     */
    SOME_PUSH_FAILED(600, "部分推送失败"),
    /**
     * 全部推送失败
     */
    ALL_PUSH_FAILED(601, "全部推送失败"),


    NOTHING_NEED_PUSH(602, "没有需要推送的"),


    PUSH_IS_REPETITION(602, "传入的参数中有正在推送的或者已推送成功的"),

    NOTHING_NEED_PUSH_AUTO(605, "没有需要自动推送的"),

    /**
     * 操作失败
     */
    OPERATION_ERROR(500, "操作失败"),
    /**
     * 参数错误
     */
    QUERY_DATA_ERROR(400, "参数错误"),
    /**
     * 未登录
     */
    UNAUTHORIZED_ERROR(401, "未登录"),
    /**
     * 权限不足
     */
    FORBIDDEN_ERROR(403, "权限不足"),
    /**
     * 服务器错误
     */
    SERVER_ERROR(500, "服务器错误"),
    /**
     * 查询到数据为空
     */
    QUERY_ID_EMPTY(503, "没有找到匹配信息"),
    /**
     * 查询到数据为空
     */
    QUERY_DATA_EMPTY(504, "没有查询到数据");

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回状态信息
     */
    private String msg;

    StatusCode(int statusCode, String statusMsg) {
        this.code = statusCode;
        this.msg = statusMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
