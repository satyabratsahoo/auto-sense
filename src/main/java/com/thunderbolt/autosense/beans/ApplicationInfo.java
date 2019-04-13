package com.thunderbolt.autosense.beans;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ApplicationInfo {


    private String applicationName;
    private String version;
    private String author;
    private ArrayList<String> technology;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }





    public ArrayList<String> getTechnology() {
        return technology;
    }

    public void setTechnology(ArrayList<String> technology) {
        this.technology = technology;
    }


    public void setAppProperties() throws IOException {
        Resource resource = new ClassPathResource("/appInfo.properties");
        Properties appProp = PropertiesLoaderUtils.loadProperties(resource);
        ArrayList<String> techList = new ArrayList<>();
        this.setApplicationName(appProp.getProperty("appInfo.appName"));
        this.setVersion(appProp.getProperty("appInfo.appVersion"));
        this.setAuthor(appProp.getProperty("appInfo.appAuthor"));

        String[] s = appProp.getProperty("appInfo.appTechnology").split(",");
        for(String f:s){
            techList.add(f.trim());
        }
        this.setTechnology(techList);


    }
}
