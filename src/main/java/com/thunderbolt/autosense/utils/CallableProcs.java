package com.thunderbolt.autosense.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thunderbolt.autosense.beans.UserLoginBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CallableProcs {


    public static UserLoginBean userLogin(String username,String password, String location) throws IOException {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,username);
        map.put(2,password);
        map.put(3,location);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonOutput =
        PgUtils.executeQuery("appengine.f_user_login",map);

        UserLoginBean userLoginBean = objectMapper.readValue(jsonOutput, UserLoginBean.class);

        return userLoginBean;

    }

    public static UserLoginBean userSession(String sessionToken) throws IOException {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,sessionToken);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonOutput =
                PgUtils.executeQuery("appengine.f_session_retrieve",map);

        UserLoginBean userLoginBean = objectMapper.readValue(jsonOutput, UserLoginBean.class);

        return userLoginBean;

    }
}
