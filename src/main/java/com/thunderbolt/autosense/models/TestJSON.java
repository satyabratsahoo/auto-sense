package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.beans.UserLoginBean;
import com.thunderbolt.autosense.utils.JsonUtils;

public class TestJSON {


    public static void main(String[] args) throws Exception {


        System.out.println(JsonUtils.getJsonSchema(UserLoginBean.class));


    }
}