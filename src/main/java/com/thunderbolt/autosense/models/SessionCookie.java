package com.thunderbolt.autosense.models;

import org.springframework.web.bind.annotation.CookieValue;

public class SessionCookie{

    private String _sessionToken;
    private String _username;

    public String get_sessionToken() {
        return _sessionToken;
    }

    public void set_sessionToken(String _sessionToken) {
        this._sessionToken = _sessionToken;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }



}
