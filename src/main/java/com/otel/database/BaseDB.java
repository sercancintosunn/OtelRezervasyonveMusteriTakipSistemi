package com.otel.database;

import java.sql.Connection;

public abstract class BaseDB {

    protected Connection connection;

    public BaseDB(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
}
