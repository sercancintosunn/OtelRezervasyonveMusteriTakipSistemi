package com.otel.state;

public class RezervasyonFactory {

    public static IRezervasyonState getState(String durum){
        if(durum == null){
            return null;
        }

        switch(durum.toUpperCase()){
            case "BEKLEMEDE":
                return new Beklemede();
            case "ONAYLANDI":
                return new Onaylandi();
            case "TAMAMLANDI":
                return new Tamamlandi();
            case "IPTAL":
                return new Iptal();
            default:
                return new Beklemede();
        }
    }
}
