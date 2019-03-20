package com.thunderbolt.autosense.models;

import java.sql.*;

public abstract class PgVariables {



    private String hostname;
    private String dbName;
    private String username;
    private String password;
    private String executablePath;

    private int port;
    private boolean isConnected;
    private boolean isSSL;

    //Connection Variables

    private Connection pgConnection;
    private Driver pgDriver;
    private DriverManager pgDriverManager;
    private ResultSet pgResultSet;
    private ResultSetMetaData pgResultSetMetaData;


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExecutablePath() {
        return executablePath;
    }

    public void setExecutablePath(String executablePath) {
        this.executablePath = executablePath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isSSL() {
        return isSSL;
    }

    public void setSSL(boolean SSL) {
        isSSL = SSL;
    }

    public Connection getPgConnection() {
        return pgConnection;
    }

    public void setPgConnection(Connection pgConnection) {
        this.pgConnection = pgConnection;
    }

    public Driver getPgDriver() {
        return pgDriver;
    }

    public void setPgDriver(Driver pgDriver) {
        this.pgDriver = pgDriver;
    }

    public DriverManager getPgDriverManager() {
        return pgDriverManager;
    }

    public void setPgDriverManager(DriverManager pgDriverManager) {
        this.pgDriverManager = pgDriverManager;
    }

    public ResultSet getPgResultSet() {
        return pgResultSet;
    }

    public void setPgResultSet(ResultSet pgResultSet) {
        this.pgResultSet = pgResultSet;
    }

    public ResultSetMetaData getPgResultSetMetaData() {
        return pgResultSetMetaData;
    }

    public void setPgResultSetMetaData(ResultSetMetaData pgResultSetMetaData) {
        this.pgResultSetMetaData = pgResultSetMetaData;
    }



}
