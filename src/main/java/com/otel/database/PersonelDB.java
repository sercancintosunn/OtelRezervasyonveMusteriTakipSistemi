package com.otel.database;

import com.otel.helper.PasswordHelper;
import com.otel.model.Personel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonelDB extends BaseDB {



    public PersonelDB(){
        super();
    }

    public boolean personelEkle(Personel personel){

        String sql = "INSERT INTO personel (ad,soyad,tcNo,email,telefon,userName,sifre) + " +
                "VAUES (?,?,?,?,?,?,?)";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,personel.getTcNo());
            pst.setString(2, personel.getAd());
            pst.setString(3, personel.getSoyad());
            pst.setString(4, personel.getEmail());
            pst.setString(5, personel.getTelefon());
            pst.setString(6, personel.getUserName());
            pst.setString(7, PasswordHelper.hashSifre(personel.getSifre()));

            int sonuc = pst.executeUpdate();
            return sonuc > 0;

        } catch (SQLException e) {
            System.err.println("Personel ekleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Personel getPersonel(int id){
        String sql = "SELECT ? FROM personel WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                return mapToPersonel(sonuc);
            }
        } catch (SQLException e) {
            System.err.println("Personel getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Personel login(String tc,String sifre){
        String sql = "SELECT * FROM personel WHERE tcNo = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,tc);

            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                String veritabaniSifresi = sonuc.getString("sifre");
                if(PasswordHelper.sifreDogrulama(sifre,veritabaniSifresi)){
                    return  mapToPersonel(sonuc);
                }
            }
        }catch (SQLException e){
            System.err.println("Giriş hatası: "  + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean personelGuncelle(Personel personel){
        String sql = "UPDATE personel SET email = ?, telefon = ? WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, personel.getEmail());
            pst.setString(2, personel.getTelefon());
            pst.setInt(3,personel.getId());

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Personel güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean sifreGuncelle(int personelId,String yeniSifre){
        String sql = "UPDATE personel SET sifre = ? WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,PasswordHelper.hashSifre(yeniSifre));
            pst.setInt(2,personelId);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Sifre guncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean personelSil(int id){
        String sql = "DELETE FROM personel WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            int sonuc = pst.executeUpdate();
            return  sonuc > 0;
        } catch (SQLException e) {
            System.err.println("Personel silme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Personel mapToPersonel(ResultSet sonuc){
        try{
            Personel personel = new Personel();
            personel.setId(sonuc.getInt("id"));
            personel.setAd(sonuc.getString("ad"));
            personel.setSoyad(sonuc.getString("soyad"));
            personel.setTcNo(sonuc.getString("tcNo"));
            personel.setEmail(sonuc.getString("email"));
            personel.setTelefon(sonuc.getString("telefon"));
            personel.setUserName(sonuc.getString("userName"));
            personel.setSifre(sonuc.getString("sifre"));
            return personel;
        }catch (SQLException e){
            System.err.println("Personel nesnesine dönüştürme hatası: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
