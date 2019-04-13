package com.thunderbolt.autosense.utils;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


public class DbPropReader {
    final static Logger logger = Logger.getLogger(PgUtils.class);
    public static Map<String,String> getProp(){
       Map<String,String> dbMap = new HashMap<>();
        Properties connProp = new Properties();
        try {



            Resource resource = new ClassPathResource("/autosense_db_config.properties");
            connProp = PropertiesLoaderUtils.loadProperties(resource);

//            connProp.load(props);

                for(Entry<Object,Object> eSet:connProp.entrySet()){
                    dbMap.put(eSet.getKey().toString(),eSet.getValue().toString());
                }
                return dbMap;

            }
            catch (IOException e){
            logger.error(e.getMessage());
                dbMap.put("message_id","000");
                dbMap.put("message_code","ERROR_DB_PROP_READER");
                dbMap.put("error_message",e.getMessage());

                return dbMap;
            }
        catch (NullPointerException e){
            logger.error(e.getMessage());
            dbMap.put("message_id","000");
            dbMap.put("message_code","ERROR_PROPERTY_READER");
            dbMap.put("error_message",e.getMessage());
            return dbMap;
        }

    }
}
