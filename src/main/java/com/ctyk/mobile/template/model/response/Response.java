package com.ctyk.mobile.template.model.response;

/**
 * 带数据的响应
 * Created by wei.yang on 2017/8/12.
 */
public class Response<T> {

    private int status;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
