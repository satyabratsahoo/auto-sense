package com.thunderbolt.autosense.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProcUtils {
    public static Map<String,String> getProcParam(String procName){
        Map<String,String> dbMap = new HashMap<>();
        Properties connProp = new Properties();
        try {


            Resource resource = new ClassPathResource("/callableProcedure.properties");
            connProp = PropertiesLoaderUtils.loadProperties(resource);

            String procParam = connProp.getProperty(procName);
            String [] paramList =procParam.split("\\|");

            for(String paramPair:paramList){
                String [] pKeyVal =paramPair.split(":");

                if(pKeyVal.length==2){
                    dbMap.put(pKeyVal[0],pKeyVal[1]);
                }
            }

            return dbMap;

        }
        catch (IOException e){
            dbMap.put("message_id","000");
            dbMap.put("message_code","ERROR_PROC_READER");
            dbMap.put("error",e.getMessage());
            return dbMap;
        }

    }

    public static CallableStatement buildCallableProc(String procName, Connection conn,Map<Integer,String> vMap) throws Exception {
        Map<String,String> procParam = getProcParam(procName);

        if(procParam.size()!=vMap.size())
            throw new Exception("Mapping does not match");

        //Build Question String
        String parameters = "";
        int paramLen = procParam.size();
        for (int i =1;i<=paramLen;i++){

            if(i==paramLen){
                parameters +="?";
            }
            else
                parameters +="?,";


        }
        String callableStatement = "{? = call " + procName + "("+parameters+")}";
//        System.out.println(callableStatement);

        CallableStatement callProc = conn.prepareCall(callableStatement);

        for (int i =1;i<=paramLen;i++){

            setCallable(callProc,i,procParam.get(Integer.toString(i)),vMap);
        }
        //System.out.println(vMap);
        return callProc;
    }

    private static void setCallable(CallableStatement cs,int index,String type,Map<Integer,String> vMap) throws SQLException {

        if (type.equals("String"))
        cs.setString(index+1,vMap.get(index));
        else if (type.equals("Integer"))
            cs.setInt(index,Integer.parseInt(vMap.get(index)));
        else
            cs.setString(index,vMap.get(index));


    }
}
