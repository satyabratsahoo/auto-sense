package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.modules.sql.PgUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestJSON {


    public static void main(String[] args) throws IOException {

        PgUtils pgUtils = new PgUtils();
  System.out.println(

          UserSession.getSessionDetails(pgUtils.executeQuery("xadmin","Alexashu@3","autosense")));




    }
}
