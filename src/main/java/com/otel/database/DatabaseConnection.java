package com.otel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/dbotel";
    private static final String username = "root";
    private static final String password = "1234";

    private DatabaseConnection(){
        try{
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Veritabanı bağlanstısı başarılı");
        }catch (SQLException e){
            System.err.println("Veritabanı bağlantı hatası");
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try{
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(url,username,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("Veritabanı bağlantısı kapatıldı");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
