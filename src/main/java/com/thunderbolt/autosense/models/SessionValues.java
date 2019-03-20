package com.thunderbolt.autosense.models;

public class SessionValues {

    private String session_token;
    private int user_id;
    private int role_id;
    private String user_role;
    private String username;
    private String email_id;
    private String first_name;
    private String last_name;

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    //{"message_id" : 100, "message_code" : "SUCCESS",
// "value" : {"session_token" : "77464bc1-7db9-251c-86f2-2af0bcd7c7bd",
// "user_id" : 3, "role_id" : 1, "user_role" : "ADMIN", "username" : "xadmin",
// "email_id" : "xadmin@localhost", "first_name" : "alex", "last_name" : "decosta"}}
}
