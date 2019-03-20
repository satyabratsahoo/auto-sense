package com.thunderbolt.autosense.models;


import com.thunderbolt.autosense.utils.PgUtils;
import com.thunderbolt.autosense.utils.JMap;
import com.thunderbolt.autosense.utils.ProcUtil;

import javax.xml.crypto.dsig.keyinfo.PGPData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestJSON {


    public static void main(String[] args) throws IOException {
        Map<Integer,String> mMap = new HashMap<>();
        mMap.put(1,"xadmin");
        mMap.put(2,"Alexashu@3");
        mMap.put(3,"localhost");




        //System.out.println(mMap.get(1));
        System.out.println(PgUtils.executeQuery("appengine.f_user_login",mMap));




    }
}
