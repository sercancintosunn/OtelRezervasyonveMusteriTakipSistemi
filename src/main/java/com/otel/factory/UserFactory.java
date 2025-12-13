package com.otel.factory;

import com.otel.model.Musteri;
import com.otel.model.Personel;
import com.otel.model.User;

public abstract class UserFactory {

    public abstract User createUser(String tcNo,String ad,String soyad,String email,String telefon,String userName,String sifre);

    public static UserFactory getFactory(String userType){
        if(userType == null){
            return null;
        }
        if(userType.equalsIgnoreCase("Musteri")){
            return new MusteriFactory();
        }else if(userType.equalsIgnoreCase("Personel")){
            return new PersonelFactory();
        }
        return null;
    }

}
