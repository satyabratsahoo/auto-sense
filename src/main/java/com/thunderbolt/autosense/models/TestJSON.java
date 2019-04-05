package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.utils.ErrorUtil;
import com.thunderbolt.autosense.utils.PgUtils;
import com.thunderbolt.autosense.utils.UserLoginUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestJSON {


    public static void main(String[] args) throws Exception {

        UserLoginUtils.userLogin("xadmin","Alexashu@34","dataloc");


    }
}