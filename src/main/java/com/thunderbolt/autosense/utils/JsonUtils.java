package com.thunderbolt.autosense.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import java.io.IOException;

public class JsonUtils {

    public static String getJsonSchema(Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //There are other configuration options you can set.  This is the one I needed.
     //   mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);

        JsonSchema schema = mapper.generateJsonSchema(clazz);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
    }
}
