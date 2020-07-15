package com.itheima.travel.domain;

import lombok.Data;

@Data
public class ResultInfo {
    private Boolean success;//true表示一切成功，false表示失败
    private String message;//提示消息
    private Object data;//数据

    public ResultInfo() {
    }

    public ResultInfo(Boolean success) {
        this.success = success;
    }

    public ResultInfo(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultInfo(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
