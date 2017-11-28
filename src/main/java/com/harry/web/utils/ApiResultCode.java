package com.harry.web.utils;

/**
 * Created by mym on 2017/6/28.
 */
public enum ApiResultCode {
    C200(200, "Success"),
    C401(401, "No Login"),
    C403(403, "Forbidden"),
    C404(404, "Not Found"),
    C406(406, "Parameters Error"),
    C500(500, "Internal Server Error"),

    /**
     * 自定义业务异常
     * 例如:C1000(1000,"插入异常")
     */
    C1000(1000, "插入异常"),
    C1001(1001,"登录失败"),
    C1002(1002,"用户未注册"),
    C1003(1003,"密码错误"),
    C1004(1004,"更新失败"),
    C1005(1005,"用户未登录"),
    C1006(1006,"注册失败"),

    C1007(1007,"删除失败"),
    C1008(1008,"上传图片失败"),
    C1009(1009,"提交更新失败");



    public int code;//code
    public String desc;//description

    ApiResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ApiResultCode format(String name) {
        for (ApiResultCode value : ApiResultCode.values()) {
            if (name.equals(value.toString())) {
                return value;
            }
        }
        return null;

    }

    public static ApiResultCode formatName(int valKey) {
        for (ApiResultCode value : ApiResultCode.values()) {
            if (valKey == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}