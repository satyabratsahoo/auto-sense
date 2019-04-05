package com.thunderbolt.autosense.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thunderbolt.autosense.models.ErrorBean;

public class ErrorUtil {


    public static String returns(String message_id,String message_code, String value) {
    try {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setMessage_id(message_id);
        errorBean.setMessage_code(message_code);
        errorBean.setValue(value);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(errorBean);
        return json;
    }
    catch (JsonProcessingException e){
        return "{\"message_id\" : \"000\", \"message_code\" : \"JAVA_DB_READER_ERROR\", \"value\" : \""+ e.getMessage()+"\"}";
    }

    }

}
