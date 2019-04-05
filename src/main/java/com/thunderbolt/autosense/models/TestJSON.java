package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.utils.ErrorUtil;
import com.thunderbolt.autosense.utils.PgUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestJSON {


    public static void main(String[] args) throws Exception {

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"xadmin");
        map.put(2,"Alexashu@3");
        map.put(3,"localhost");

        System.out.println(ErrorUtil.returns("asd","asda","asd"));
        System.out.println(
                PgUtils.executeQuery("appengine.f_user_login",map)

        );
    }
}