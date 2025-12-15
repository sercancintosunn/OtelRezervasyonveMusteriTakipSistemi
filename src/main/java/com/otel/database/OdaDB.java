package com.otel.database;

import com.otel.model.Oda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdaDB extends BaseDB {



    public OdaDB(){
        super();
    }

    public boolean odaEkle(Oda oda){
        String sql = "INSERT INTO odalar (odaNumarasi,odaTipi,kapasite,fiyat,durum)" +
                "VALUES (?,?,?,?,?)";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, oda.getOdaNumarasi());
            pst.setString(2,oda.getOdaTipi());
            pst.setInt(3,oda.getKapasite());
            pst.setDouble(4,oda.getFiyat());
            pst.setString(5, oda.getDurum());

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e) {
            System.err.println("Oda ekleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Oda getOda(int id){
        String sql = "SELECT * FROM odalar where id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            ResultSet sonuc = pst.executeQuery();

            if(sonuc.next()){
                return mapToOda(sonuc);
            }
        }catch (SQLException e){
            System.err.println("Oda getirme hatası:  " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> getOdaNumaraları(){
        String sql = "SELECT odaNumarasi FROM odalar";
        List<Integer> list = new ArrayList<>();
        try(PreparedStatement pst = connection.prepareStatement(sql)){
            ResultSet sonuc = pst.executeQuery();
            while (sonuc.next()) {
                list.add(sonuc.getInt("odaNumarasi"));
            }
            return list;
        }catch (SQLException e){
            System.err.println("Oda getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Oda getOdaNumarasi(String odaNumarasi){
        String sql = "SELECT * FROM odalar WHERE odaNumarasi = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,odaNumarasi);

            ResultSet sonuc = pst.executeQuery();
            if(sonuc.next()){
                return mapToOda(sonuc);
            }
        }catch (SQLException e){
            System.err.println("Oda getirme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Oda> tumOdalar(){
        List<Oda> odalar = new ArrayList<>();
        String sql = "SELECT * from odalar order by odaNumarasi";

        try(Statement statement = connection.createStatement();
            ResultSet sonuc = statement.executeQuery(sql)){
            while (sonuc.next()){
                odalar.add(mapToOda(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Oda listeleme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return odalar;
    }

    public List<Oda> musaitOdalar(){
        List<Oda> odalar = new ArrayList<>();
        String sql = "select * from odalar where durum = 'MUSAİT' order by odaNumarasi";

        try(Statement statement = connection.createStatement();
            ResultSet sonuc = statement.executeQuery(sql)){
            while(sonuc.next()){
                odalar.add(mapToOda(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Oda listeleme hatası: " + e.getMessage());
            e.printStackTrace();
        }
        return odalar;
    }



     public List<Oda> odaAra(java.sql.Date girisTarihi,java.sql.Date cikisTarihi,String odaTipi,int minKapasite){

        List<Oda> odalar = new ArrayList<>();

        String sql = "select o.* from odalar o where o.durum = 'MUSAİT' and o.kapasite >= ?";

        if(girisTarihi != null && cikisTarihi != null){
            sql += " and o.id not in (select odaId from rezervasyonlar where durum in ('BEKLEMEDE', 'ONAYLANDI')"+
                    " and ((girisTarihi <= ? and cikisTarihi >= ?) or (girisTarihi <= ? and cikisTarihi >= ?) "+
                    " or (girisTarihi >= ? and cikisTarihi <= ?))) ";
        }

        if(odaTipi != null && !odaTipi.isEmpty()){
            sql += " and odaTipi = ?";
        }

        sql += "order by o.fiyat";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            int index = 1;

            pst.setInt(index++,minKapasite);

            if(girisTarihi != null && cikisTarihi != null){
                pst.setDate(index++,girisTarihi);
                pst.setDate(index++,girisTarihi);
                pst.setDate(index++,cikisTarihi);
                pst.setDate(index++,cikisTarihi);
                pst.setDate(index++,girisTarihi);
                pst.setDate(index++,cikisTarihi);

            }

            if(odaTipi != null && !odaTipi.isEmpty()){
                pst.setString(index,odaTipi);
            }

            ResultSet sonuc = pst.executeQuery();
            while(sonuc.next()){
                odalar.add(mapToOda(sonuc));
            }
        }catch (SQLException e){
            System.err.println("Oda arama hatası: " +e.getMessage());
            e.printStackTrace();
        }
        return odalar;
     }

     public boolean odaGuncelle(Oda oda){
        String sql = "update odalar set odaNumarasi = ?, odaTipi = ?, kapasite = ?,fiyat = ?,durum = ? where id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, oda.getOdaNumarasi());
            pst.setString(2,oda.getOdaTipi());
            pst.setInt(3,oda.getKapasite());
            pst.setDouble(4,oda.getFiyat());
            pst.setString(5,oda.getDurum());

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Oda güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
     }

     public boolean odaDurumGuncelle(int odaId,String yeniDurum){
        String sql = "update odalar set durum = ? where odaId = ? ";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1,yeniDurum);
            pst.setInt(2,odaId);

            int sonuc = pst.executeUpdate();
            return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Oda durum güncelleme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
     }

     public boolean odaSil(int id){
        String sql = "delete from odalar where ıd = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1,id);

            int sonuc = pst.executeUpdate();
             return sonuc > 0;
        }catch (SQLException e){
            System.err.println("Oda silme hatası: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
     }

     private Oda mapToOda(ResultSet sonuc){
        try {
            Oda oda = new Oda();
            oda.setId(sonuc.getInt("id"));
            oda.setOdaNumarasi(sonuc.getString("odaNumarasi"));
            oda.setOdaTipi(sonuc.getString("odaTipi"));
            oda.setKapasite(sonuc.getInt("kapasite"));
            oda.setFiyat(sonuc.getDouble("fiyat"));
            oda.setDurum(sonuc.getString("durum"));
            return oda;
        }catch (SQLException e){
            System.err.println("Oda dönğştürme hatası: " +e.getMessage());
            e.printStackTrace();
            return null;
        }
     }
}
