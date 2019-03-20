package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.utils.PgUtils;
import com.thunderbolt.autosense.utils.JMap;

import java.io.IOException;
import java.util.Map;

public class TestJSON {


    public static void main(String[] args) throws IOException {

        PgUtils pgUtils = new PgUtils();

          Map<String,String> dMap= JMap.getMap(pgUtils.executeQuery("xadmin","Alexashu@3","autosense"));

        System.out.println(dMap);


    }
}
