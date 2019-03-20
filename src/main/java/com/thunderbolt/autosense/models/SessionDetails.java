package com.thunderbolt.autosense.models;

public class SessionDetails {

    private int message_id;
    private String message_code;
    private SessionValues value;

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

    public SessionValues getValue() {
        return value;
    }

    public void setValue(SessionValues value) {
        this.value = value;
    }




}
