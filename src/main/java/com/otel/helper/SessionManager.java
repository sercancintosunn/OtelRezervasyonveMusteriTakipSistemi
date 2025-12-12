package com.otel.helper;
import com.otel.model.User;

public class SessionManager {

    private static SessionManager instance;

    private User user;

    private SessionManager(){

    }

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(User user1){
            this.user = user1;
            System.out.println("Oturum açıldı: " + user.getFullname() + " (" + user.getUserType() +")");
    }

    public void logOut(){
        if(user != null){
            System.out.println("Oturum Kapatıldı: " + user.getFullname());
            this.user = null;
        }
    }

    public User getUser(){
        return user;
    }

    public boolean isMusteri(){
        return user != null && "Musteri".equals(user.getUserType());
    }

    public boolean isPersonel(){
        return user != null && "Personel".equals(user.getUserType());
    }

    public int getUserId(){
        return user != null ? user.getId() : -1;
    }

}
