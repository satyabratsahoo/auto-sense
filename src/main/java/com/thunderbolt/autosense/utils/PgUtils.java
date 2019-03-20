package com.thunderbolt.autosense.utils;


import com.thunderbolt.autosense.models.PgVariables;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PgUtils extends PgVariables {
    final static Logger logger = Logger.getLogger(PgUtils.class);


    public void pgDisconnect() throws SQLException {
        if (this.getPgConnection() != null) {
            this.getPgConnection().close();
        }
    }

    public static Connection initConnection()
             {
                 Connection connection = null;
                 Properties connProp = new Properties();
       try {

           Map<String,String> dbConn =DbProp.getProp();

           Class.forName("org.postgresql.Driver");

           try {
               connection = DriverManager.getConnection(
                       "jdbc:postgresql://" + dbConn.get("hostname") + ":" +  dbConn.get("port") + "/"
                               + dbConn.get("database"), dbConn.get("username"), dbConn.get("password"));

               if (connection != null) {
                   logger.info("Connected to the Database");
                   return connection;
               }
           } catch (SQLException e) {
               logger.error(e.getMessage());
               return connection;
           }
       }
       catch (Exception e){
           logger.error(e.toString());
           return connection;
       }

       finally {

           return connection;
       }
    }

    public static String executeQuery(String procName,Map<Integer,String> pMap){
        Connection connection = null;
        try {

            connection=initConnection();
            CallableStatement callProc =
                    ProcUtil.buildCallableProc(procName, connection,pMap);

            callProc.registerOutParameter(1, Types.VARCHAR);

            callProc.execute();
            String result = callProc.getString(1);
            callProc.close();
            connection.close();
            return result;
        }

        catch (SQLException e){
            //logger.error(e.getMessage());
            return "{\"message_id\" : \"000\", \"message_code\" : \"JAVA_DB_READER_ERROR\", \"value\" : \""+ e.getMessage()+"\"}";
        }



    }
}
