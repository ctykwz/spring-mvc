package com.ctyk.mobile.template.model.response;

/**
 * 一般响应
 * Created by wei.yang on 2017/3/8.
 */
public class CommonResponse {

    protected int status;

    protected String description;

    public static CommonResponse ok() {
        return new CommonResponse().setStatus(0);
    }

    public static CommonResponse error() {
        return new CommonResponse().setStatus(1);
    }

    public int getStatus() {
        return status;
    }

    public CommonResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CommonResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
