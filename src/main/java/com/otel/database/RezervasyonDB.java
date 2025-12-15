package com.otel.database;

import com.otel.model.Musteri;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonDB {

    private Connection connection;

    public RezervasyonDB(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean rezervasyonEkle(Rezervasyon rezervasyon){
        String sql = "insert into rezervasyonlar (musteriId,odaId,girisTarihi,cikisTarihi,kisiSayisi,toplamFiyat,durum)" +
                    "values (?,?,?,?,?,?,?)";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,rezervasyon.getMusteriId());
            pst.setInt(2,rezervasyon.getOdaId());
            pst.setDate(3, new java.sql.Date(rezervasyon.getGirisTarihi().getTime()));
            pst.setDate(4, new java.sql.Date(rezervasyon.getCikisTarihi().getTime()));
            pst.setInt(5,rezervasyon.getKisiSayisi());
            pst.setDouble(6,rezervasyon.getToplamFiyat());
            pst.setString(7,rezervasyon.getDurum());

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Rezervasyon oluşturma hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Rezervasyon rezervasyonGetir(int id){
        String sql = "select * from rezervasyonlar where id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                return mapToRezervasyon(sonuc);
            }
        } catch (SQLException e) {
            System.err.println("Rezervasyon getirme hatası:  " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Rezervasyon rezervasyonGetirDetayli(int id){
        String sql = "select r.*, m.ad as musteriAd, m.soyad as musteriSoyad, m.telefon as musteriTelefon, " +
                    "o.odaNumarasi, o.odaTipi, o.fiyat as odaFiyat" +
                    "from rezervasyonlar r" +
                    "join musteriler m on r.musteriId = m.id" +
                    "join odalar o on r.odaId = o.id" +
                    "where r.id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                return mapToRezervasyonDetayli(sonuc);
            }
        } catch (SQLException e) {
            System.err.println("Rezervaasyon deatli getirme hatasi: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Rezervasyon> musteriRezervasyonlari(int musteriId){
        List<Rezervasyon> rezervasyonlar = new ArrayList<>();

        String sql = "select * from rezervasyonlar where musteriId = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,musteriId);

            ResultSet sonuc = pst.executeQuery();

            while(sonuc.next()){
                rezervasyonlar.add(mapToRezervasyonDetayli(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Müşteri rezervasyonları getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return rezervasyonlar;
    }

    public List<Rezervasyon> gecmisMusteriKonaklama(int musteriId){
        List<Rezervasyon> rezervasyonlar = new ArrayList<>();

        String sql = "select r.*, o.odaNumarasi, o.odaTipi  " +
                    "from rezevasyonlar r" +
                    "join odalar o on r.odaId = o.id" +
                    "where r.musteriId = ? and r.durum = 'TAMAMLANDI'" +
                    "order by r.cikisTarihi desc";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,musteriId);

            ResultSet sonuc = pst.executeQuery();

            while(sonuc.next()){
                rezervasyonlar.add(mapToRezervasyonDetayli(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Geçmiş konakları getirme hatası:  " + e.getMessage());
            e.printStackTrace();
        }
        return rezervasyonlar;
    }

    public List<Rezervasyon> tumRezervasyonlar(){
        List<Rezervasyon> rezervasyonlar = new ArrayList<>();

        String sql = "select r.*, m.ad as musteriAd, m.soyad as musteriSoyad, o.odaNumarasi " +
                    "from rezervasyonlar r " +
                    "join musteriler m on r.musteriId = m.id " +
                    "join odalar o on r.odaId = o.id " +
                    "order by r.girisTarihi desc";

        try(Statement statement = connection.createStatement();
            ResultSet sonuc = statement.executeQuery(sql)){

            while(sonuc.next()){
                rezervasyonlar.add(mapToRezervasyonDetayli(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Rezervasyon listeleme hatasi: "  +e.getMessage());
            e.printStackTrace();
        }
        return rezervasyonlar;
    }

    public List<Rezervasyon> rezervasyonAra(String anahtar) {
        List<Rezervasyon> rezervasyonlar = new ArrayList<>();
        String sql = "SELECT r.*, m.ad as musteri_ad, m.soyad as musteri_soyad, o.oda_numarasi " +
                "FROM rezervasyonlar r " +
                "JOIN musteriler m ON r.musteri_id = m.id " +
                "JOIN odalar o ON r.oda_id = o.id " +
                "WHERE m.ad LIKE ? OR m.soyad LIKE ? OR o.oda_numarasi LIKE ? " +
                "ORDER BY r.giris_tarihi DESC";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            String aramaKelimesi = "%" + anahtar + "%";
            pst.setString(1, aramaKelimesi);
            pst.setString(2, aramaKelimesi);
            pst.setString(3, aramaKelimesi);

            ResultSet sonuc = pst.executeQuery();
            while (sonuc.next()) {
                rezervasyonlar.add(mapToRezervasyonDetayli(sonuc));
            }
        } catch (SQLException e) {
            System.err.println("Rezervasyon arama hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return rezervasyonlar;
    }

    public List<Rezervasyon> rezervasyonDurumaGore(String durum) {
        List<Rezervasyon> rezervasyonlar = new ArrayList<>();
        String sql = "SELECT r.*, m.ad as musteri_ad, m.soyad as musteri_soyad, o.oda_numarasi " +
                "FROM rezervasyonlar r " +
                "JOIN musteriler m ON r.musteri_id = m.id " +
                "JOIN odalar o ON r.oda_id = o.id " +
                "WHERE r.durum = ? " +
                "ORDER BY r.giris_tarihi DESC";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, durum);
            ResultSet sonuc = pst.executeQuery();

            while (sonuc.next()) {
                rezervasyonlar.add(mapToRezervasyonDetayli(sonuc));
            }
        } catch (SQLException e) {
            System.err.println("Durum rezervasyon getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return rezervasyonlar;
    }

    public boolean rezervasyonDurumGuncelle(int rezervasyonId, String yeniDurum) {
        String sql = "UPDATE rezervasyonlar SET durum = ? WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, yeniDurum);
            pst.setInt(2, rezervasyonId);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;

        } catch (SQLException e) {
            System.err.println("Rezervasyon durum güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean rezervasyonIptal(int rezervasyonId) {
        return rezervasyonDurumGuncelle(rezervasyonId, "IPTAL");
    }

    public boolean rezervasyonOnayla(int rezervasyonId) {
        return rezervasyonDurumGuncelle(rezervasyonId, "ONAYLANDI");
    }

    public boolean rezervasyonTamamla(int rezervasyonId) {
        return rezervasyonDurumGuncelle(rezervasyonId, "TAMAMLANDI");
    }

    public boolean rezervasyonSil(int id) {
        String sql = "DELETE FROM rezervasyonlar WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            int sonuc = pst.executeUpdate();
            return sonuc > 0;

        } catch (SQLException e) {
            System.err.println("Rezervasyon silme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Rezervasyon mapToRezervasyon(ResultSet sonuc) {
        try {
            Rezervasyon rezervasyon = new Rezervasyon();
            rezervasyon.setId(sonuc.getInt("id"));
            rezervasyon.setMusteriId(sonuc.getInt("musteriId"));
            rezervasyon.setOdaId(sonuc.getInt("odaId"));
            rezervasyon.setGirisTarihi(sonuc.getDate("girisTarihi"));
            rezervasyon.setCikisTarihi(sonuc.getDate("cikisTarihi"));
            rezervasyon.setKisiSayisi(sonuc.getInt("kisiSayisi"));
            rezervasyon.setToplamFiyat(sonuc.getDouble("toplamFiyat"));
            rezervasyon.setDurum(sonuc.getString("durum"));
            return rezervasyon;
        } catch (SQLException e) {
            System.err.println("Rezervasyon nesnesine dönüştürme hatası: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Rezervasyon mapToRezervasyonDetayli(ResultSet sonuc) {
        try {
            Rezervasyon rezervasyon = mapToRezervasyon(sonuc);


            try {
                Musteri musteri = new Musteri();
                musteri.setId(rezervasyon.getMusteriId());
                musteri.setAd(sonuc.getString("musteriAd"));
                musteri.setSoyad(sonuc.getString("musteriSoyad"));
                rezervasyon.setMusteri(musteri);
            } catch (SQLException e) {

            }


            try {
                Oda oda = new Oda();
                oda.setId(rezervasyon.getOdaId());
                oda.setOdaNumarasi(sonuc.getString("odaNumarasi"));
                oda.setOdaTipi(sonuc.getString("odaTipi"));
                rezervasyon.setOda(oda);
            } catch (SQLException e) {
            }

            return rezervasyon;
        } catch (Exception e) {
            System.err.println("Rezervasyon detaylı dönüştürme hatası: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
