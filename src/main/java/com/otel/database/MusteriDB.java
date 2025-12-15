package com.otel.database;

import com.otel.helper.PasswordHelper;
import com.otel.model.Musteri;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusteriDB extends BaseDB {



    public MusteriDB() {
        super();
    }

    public boolean musteriEkle(Musteri musteri) {
        String sql = "INSERT INTO musteriler(tcNo,ad,soyad,email,telefon,userName,sifre)" +
                "VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, musteri.getTcNo());
            pst.setString(2, musteri.getAd());
            pst.setString(3, musteri.getSoyad());
            pst.setString(4, musteri.getEmail());
            pst.setString(5, musteri.getTelefon());
            pst.setString(6, musteri.getUserName());
            pst.setString(7, PasswordHelper.hashSifre(musteri.getSifre()));

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        } catch (SQLException e) {
            System.err.println("Müşteri ekleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }

    public boolean musteriBilgileriGuncelle(int id, String username,String email, String telefon , String sifre){
        String sql = "UPDATE musteriler SET userName = ? , email = ? , telefon = ? , sifre = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, telefon);
            pst.setString(4, PasswordHelper.hashSifre(sifre));
            pst.setInt(5, id);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        } catch (SQLException e) {
            System.err.println("Müşteri güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }

    public boolean musteriBilgileriGuncelle(int id, String username,String email, String telefon){
        String sql = "UPDATE musteriler SET userName = ? , email = ? , telefon = ?  WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, telefon);
            pst.setInt(4, id);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        } catch (SQLException e) {
            System.err.println("Müşteri güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }

    public Musteri getMusteri(int id) {
        String sql = "SELECT * FROM musteriler WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet sonuc = pst.executeQuery();

            if (sonuc.next()) {
                return mapToMusteri(sonuc);
            }

        } catch (SQLException e) {
            System.err.println("Müşteri getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Musteri getMusteri(String tcNo) {
        String sql = "SELECT * FROM musteriler WHERE tcNo = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, tcNo);
            ResultSet sonuc = pst.executeQuery();

            if (sonuc.next()) {
                return mapToMusteri(sonuc);
            }

        } catch (SQLException e) {
            System.err.println("Müşteri getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Musteri login(String tcNo,String sifre){
        String sql = "SELECT * from musteriler WHERE tcNo = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,tcNo);


            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                String veritabaniSifresi = sonuc.getString("sifre");
                if(PasswordHelper.sifreDogrulama(sifre,veritabaniSifresi)){
                    return mapToMusteri(sonuc);
                }
            }
        }catch (SQLException e){
            System.err.println("Giriş Hatası: "  + e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public List<Musteri> tumMusteriler(){
        List<Musteri> musteriler = new ArrayList<>();
        String sql = "SELECT * from musteriler ORDER BY id DESC"
                ;

        try(Statement st = connection.createStatement();
            ResultSet sonuc = st.executeQuery(sql)){
                while (sonuc.next()){
                    musteriler.add(mapToMusteri(sonuc));
                }
        }catch (SQLException e){
            System.err.println("Müşteri Listeleme hatası: " + e.getMessage() );
            e.printStackTrace();
        }
        return musteriler;
    }

    public List<Musteri> musteriAra(String anahtar){
        List<Musteri> musteriler = new ArrayList<>();

        String sql = "SELECT * FROM musteriler WHERE ad LIKE ? OR soyad LIKE ? OR tcNo LIKE ? OR telefon LIKE ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            String kelime = "%" + anahtar + "%";
            pst.setString(1,kelime);
            pst.setString(2,kelime);
            pst.setString(3,kelime);
            pst.setString(4,kelime);

            ResultSet sonuc = pst.executeQuery();
            while(sonuc.next()){
                musteriler.add(mapToMusteri(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Müşteri arama hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return musteriler;
    }

    public boolean musteriGuncelle(Musteri musteri){
        String sql = "UPDATE musteriler SET email = ?, telefon = ? WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,musteri.getEmail());
            pst.setString(2,musteri.getTelefon());
            pst.setInt(3,musteri.getId());

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Müşteri güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean sifreGuncelle(int musteriId,String yeniSifre){
        String sql = "UPDATE musteriler SET sifre = ? WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,PasswordHelper.hashSifre(yeniSifre));
            pst.setInt(2,musteriId);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Şifre güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean musteriSil(int id){
        String sql = "DELETE FROM musteriler WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Müşteri silme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    private Musteri mapToMusteri(ResultSet sonuc) {
        try {
            Musteri musteri = new Musteri();
            musteri.setId(sonuc.getInt("id"));
            musteri.setTcNo(sonuc.getString("tcNo"));
            musteri.setAd(sonuc.getString("ad"));
            musteri.setSoyad(sonuc.getString("soyad"));
            musteri.setEmail(sonuc.getString("email"));
            musteri.setTelefon(sonuc.getString("telefon"));
            musteri.setUserName(sonuc.getString("userName"));
            musteri.setSifre(sonuc.getString("sifre"));
            return musteri;
        } catch (SQLException e) {
            System.err.println("Musteri nesnesine dönüştürme hatası: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
