package com.thunderbolt.autosense.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.util.*;

public  class JMap {
    public static Map<String, String> getMap(String jsonInput) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, String> jMapper = new HashMap<>();
        try {

            JsonNode root = oMapper.readTree(jsonInput);
            addKeys("", root, jMapper, new ArrayList<Integer>());

            return jMapper;
        }
        catch (Exception e){
            jMapper.put("message_id","000");
            jMapper.put("message_code","ERROR");
            jMapper.put("error",e.getMessage());
            return jMapper;

        }
    }

    //This code is to convert all nodes to map.
    private static void addKeys(String currentPath,JsonNode jsonNode, Map<String, String> map, List<Integer> suffix) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
            String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                addKeys(pathPrefix + entry.getKey(), entry.getValue(), map, suffix);
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;

            for (int i = 0; i < arrayNode.size(); i++) {
                suffix.add(i + 1);
                addKeys(currentPath, arrayNode.get(i), map, suffix);

                if (i + 1 <arrayNode.size()){
                    suffix.remove(arrayNode.size() - 1);
                }
            }

        } else if (jsonNode.isValueNode()) {
            if (currentPath.contains(".")) {
                for (int i = 0; i < suffix.size(); i++) {
                    currentPath += "." + suffix.get(i);
                }

                suffix = new ArrayList<>();
            }

            ValueNode valueNode = (ValueNode) jsonNode;
            map.put(currentPath, valueNode.asText());
        }
    }
}