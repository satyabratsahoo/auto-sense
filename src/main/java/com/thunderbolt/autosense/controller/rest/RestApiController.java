package com.thunderbolt.autosense.controller.rest;

import com.thunderbolt.autosense.beans.ApplicationInfo;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestApiController {
    final static Logger logger = Logger.getLogger(RestApiController.class);

    @RequestMapping(value={"rest"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicationInfo getAppInfo() throws IOException {
        ApplicationInfo appInfo = new ApplicationInfo();
        appInfo.setAppProperties();
        return appInfo;

    }


}
