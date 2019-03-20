package com.thunderbolt.autosense.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserSession {
    public static Map<String, String> getSessionDetails(String jsonInput) {
        ObjectMapper jCheck = new ObjectMapper();
        Map<String, String> sessionMap = new HashMap<>();
        try {

            JsonNode root = jCheck.readTree(jsonInput);
            int mId = root.path("message_id").asInt();

            if (mId == 100) {
                try {
                    Iterator<HashMap.Entry<String, JsonNode>> sessionVal = root.path("value").fields();
                    while (sessionVal.hasNext()) {
                        Map.Entry<String, JsonNode> sMap = sessionVal.next();
                        sessionMap.put(sMap.getKey(), sMap.getValue().asText());
                    }
                    sessionMap.put("message_id","100");
                    sessionMap.put("message_code","SUCCESS");
                    return sessionMap;

                } catch (Exception e) {
                    sessionMap.put("error",e.getMessage());
                    return sessionMap;
                }

            }

            else{
                try {
                    Iterator<HashMap.Entry<String, JsonNode>> sessionVal = root.fields();
                    while (sessionVal.hasNext()) {
                        Map.Entry<String, JsonNode> sMap = sessionVal.next();
                        sessionMap.put(sMap.getKey(), sMap.getValue().asText());
                    }
                    return sessionMap;

                } catch (Exception e) {
                    sessionMap.put("error",e.getMessage());
                    return sessionMap;
                }

            }

        } catch (Exception e) {
            sessionMap.put("error",e.getMessage());
            return sessionMap;
        }

        finally {

            return sessionMap;
        }
    }
}