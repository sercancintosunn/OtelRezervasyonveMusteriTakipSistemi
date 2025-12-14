package com.otel.observer;

import com.otel.model.Rezervasyon;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonSubject{

    private List<IRezervasyonObserver> observers = new ArrayList<>();

    public void ekle(IRezervasyonObserver observer){
        if(!observers.contains(observer)){
            observers.add(observer);
            System.out.println("Observer eklendi");
        }
    }

    public void sil(IRezervasyonObserver observer){
        if(!observers.contains(observer)){
            observers.remove(observer);
            System.out.println("Observer silindi");
        }
    }

    public void bilgiObserver(Rezervasyon rezervasyon,String islem){
        System.out.println("Bildirim gönderiliyor: " +islem);
            for(IRezervasyonObserver observer : observers){
                observer.update(rezervasyon,islem);
            }
        System.out.println();
    }

}
