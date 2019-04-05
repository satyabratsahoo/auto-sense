package com.thunderbolt.autosense.beans;

public class UserLoginBean {

    private String message_id;
    private String message_code;
    private String error_message;
    private UserDataBean value;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_code() {
        return message_code;
    }

    public void setMessage_code(String message_code) {
        this.message_code = message_code;
    }

    public UserDataBean getValue() {
        return value;
    }

    public void setValue(UserDataBean value) {
        this.value = value;
    }


    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
