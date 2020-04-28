package com.wht.poetry.api;

public enum ResultCode implements IErrorCode {
    FAILED(0, "操作失败"),
    SUCCESS(1, "操作成功"),
    VALIDATE_FAILED(2, "参数检验失败"),
    UNAUTHORIZED(3, "暂未登录或token已经过期"),
    FORBIDDEN(4, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
