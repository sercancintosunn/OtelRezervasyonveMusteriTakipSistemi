package com.otel.model;

public abstract class User {
    protected int id;
    protected String tcNo;
    protected String ad;
    protected String soyad;
    protected String email;
    protected String telefon;
    protected String userName;
    protected String sifre;

    public User(){
    }

    public User(int id,String tcNo,String ad,String soyad,String email,String telefon,String userName,String sifre){
        this.id = id;
        this.tcNo = tcNo;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
        this.userName = userName;
        this.sifre = sifre;
    }

    public abstract String getUserType();
    public abstract void displayInfo();

    public String getFullname(){
        return ad+" "+soyad;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId(){
        return id;
    }

    public String getTcNo(){
        return tcNo;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefon(){
        return telefon;
    }

    public void setTelefon(String telefon){
        this.telefon = telefon;
    }

    public String getUserName(){
        return userName;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre){
        this.sifre = sifre;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
