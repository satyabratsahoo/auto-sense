package com.thunderbolt.autosense.modules.sql;


import com.thunderbolt.autosense.models.PgVariables;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class PgUtils extends PgVariables {
    final static Logger logger = Logger.getLogger(PgUtils.class);
    Properties connProp = new Properties();
    Connection connection = null;

    public void pgDisconnect() throws SQLException {
        if (this.getPgConnection() != null) {
            this.getPgConnection().close();
        }
    }

    public  Connection initConnection()
             {


       try {
           try (InputStream fis = new FileInputStream(ClassLoader.getSystemResource("autosense_db_config.properties").getFile())) {
               connProp.load(fis);
           }
           String hostname = connProp.getProperty("hostname");
           String port = connProp.getProperty("port");
           String dbname = connProp.getProperty("dbname");
           String dbuser = connProp.getProperty("root_user");
           String dbpass = connProp.getProperty("root_password");

           Class.forName("org.postgresql.Driver");

           try {
               connection = DriverManager.getConnection(
                       "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname, dbuser, dbpass);

               if (connection != null) {
                   logger.info("Connected to the Database");
                   return connection;
               }
           } catch (Exception e) {
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

    public String executeQuery(String username,String password,String clientIp){
        Connection connection = null;
        try {

            connection=initConnection();
            CallableStatement callProc = connection.prepareCall("{? = call appengine.f_user_login( ?,?,? ) }");
            callProc.registerOutParameter(1, Types.VARCHAR);
            callProc.setString(2, username);
            callProc.setString(3, password);
            callProc.setString(4, clientIp);
            callProc.execute();
            String result = callProc.getString(1);
            callProc.close();
            connection.close();
            return result;
        }

        catch (Exception e){
            //logger.error(e.getMessage());
            return "{\"message_id\" : 000, \"message_code\" : \"JAVA_ERROR\", \"value\" : \""+ e.getMessage()+"\"}";
        }

    }
}
