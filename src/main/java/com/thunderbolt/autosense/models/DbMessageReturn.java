package com.thunderbolt.autosense.models;

public class DbMessageReturn {

    private int message_id;
    private String message_code;
    private String msg_value;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_code() {
        return message_code;
    }

    public void setMessage_code(String message_code) {
        this.message_code = message_code;
    }

    public String getMsg_value() {
        return msg_value;
    }

    public void setMsg_value(String msg_value) {
        this.msg_value = msg_value;
    }
}
