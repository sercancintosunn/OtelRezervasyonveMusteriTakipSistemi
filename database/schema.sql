
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";




CREATE DATABASE IF NOT EXISTS `dbotel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `dbotel`;



DROP TABLE IF EXISTS `musteriler`;
CREATE TABLE IF NOT EXISTS `musteriler` (
                                            `id` int NOT NULL AUTO_INCREMENT,
                                            `ad` varchar(30) NOT NULL,
    `soyad` varchar(30) NOT NULL,
    `tcNo` varchar(11) NOT NULL,
    `email` varchar(100) NOT NULL,
    `telefon` varchar(15) NOT NULL,
    `userName` varchar(30) NOT NULL,
    `sifre` varchar(50) NOT NULL,
    `kayitTarihi` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `tcNo` (`tcNo`),
    UNIQUE KEY `userName` (`userName`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





DROP TABLE IF EXISTS `odalar`;
CREATE TABLE IF NOT EXISTS `odalar` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `odaNumarasi` varchar(10) NOT NULL,
    `odaTipi` enum('STANDART','SUİT','AILE') NOT NULL,
    `kapasite` int NOT NULL,
    `fiyat` decimal(10,2) NOT NULL,
    `durum` enum('MUSAİT','DOLU') DEFAULT 'MUSAİT',
    PRIMARY KEY (`id`),
    UNIQUE KEY `odaNumarasi` (`odaNumarasi`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `personel`;
CREATE TABLE IF NOT EXISTS `personel` (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `ad` varchar(30) NOT NULL,
    `soyad` varchar(30) NOT NULL,
    `tcNo` varchar(11) NOT NULL,
    `email` varchar(100) NOT NULL,
    `telefon` varchar(15) NOT NULL,
    `userName` varchar(30) NOT NULL,
    `sifre` varchar(30) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `tcNo` (`tcNo`),
    UNIQUE KEY `userName` (`userName`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `rezervasyonlar`;
CREATE TABLE IF NOT EXISTS `rezervasyonlar` (
                                                `id` int NOT NULL AUTO_INCREMENT,
                                                `musteriId` int NOT NULL,
                                                `odaId` int NOT NULL,
                                                `girisTarihi` date NOT NULL,
                                                `cikisTarihi` date NOT NULL,
                                                `kisiSayisi` int NOT NULL,
                                                `toplamFiyat` decimal(10,2) NOT NULL,
    `durum` enum('BEKLEMEDE','ONAYLANDI','IPTAL','TAMAMLANDI') DEFAULT 'BEKLEMEDE',
    `olusturmaTarihi` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `musteriId` (`musteriId`),
    KEY `odaId` (`odaId`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE `rezervasyonlar`
    ADD CONSTRAINT `rezervasyonlar_ibfk_1` FOREIGN KEY (`musteriId`) REFERENCES `musteriler` (`id`),
  ADD CONSTRAINT `rezervasyonlar_ibfk_2` FOREIGN KEY (`odaId`) REFERENCES `odalar` (`id`);
COMMIT;

