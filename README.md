🏨 Otel Rezervasyon ve Müşteri Takip Sistemi 

Bu proje, Java (Swing) kullanılarak geliştirilmiş, MVC (Model-View-Controller) mimarisine uygun, basit bir otel rezervasyon ve yönetim otomasyonudur. Proje, nesne tabanlı programlama prensiplerini ve birçok Yazılım Tasarım Kalıbını (Design Patterns) aktif olarak kullanmaktadır.

🚀 Özellikler
Kullanıcı Yönetimi:

Müşteri ve Personel için giriş/kayıt ekranları.

Rol tabanlı yetkilendirme ve arayüz yönetimi (SessionManager).

Oda Yönetimi:

Oda ekleme, güncelleme, silme ve listeleme.

Tarih, oda tipi ve kişi sayısına göre müsaitlik filtreleme.

Rezervasyon Sistemi:

Dinamik fiyat hesaplama.

Giriş/Çıkış tarihi kontrolü.

Ekstra Hizmetler: Havuz, Kahvaltı, Otopark gibi hizmetlerin odaya dinamik olarak eklenmesi.

Ödeme Sistemi:

Nakit veya Kredi Kartı ile ödeme seçenekleri.

Durum Takibi:

Rezervasyon durumlarının (Beklemede, Onaylandı, İptal, Tamamlandı) yönetimi.

Durum değişikliklerinde müşteriye (simüle edilmiş) email bildirimi.

Raporlama:

Müşteri geçmiş konaklama dökümü ve harcama istatistikleri.

🛠️ Kullanılan Teknolojiler
Dil: Java (JDK 11+)

Arayüz: Java Swing (GUI)

Veritabanı: MySQL

Build Tool: Maven

JDBC: Veritabanı bağlantısı için.

🏗️ Mimari ve Tasarım Kalıpları (Design Patterns)
Bu proje, temiz kod ve sürdürülebilirlik için aşağıdaki tasarım kalıplarını uygular:

1. MVC (Model-View-Controller)
Proje paket yapısı model, view ve controller olarak ayrılarak iş mantığı ile arayüz birbirinden bağımsız hale getirilmiştir.

2. Singleton Pattern
Kullanım: DatabaseConnection, SessionManager

Amaç: Veritabanı bağlantısının ve aktif kullanıcı oturumunun uygulama genelinde tek bir örnek (instance) üzerinden yönetilmesi.

3. Factory Pattern
Kullanım: UserFactory, MusteriFactory, PersonelFactory

Amaç: Kullanıcı tipine (Müşteri veya Personel) göre nesne oluşturma sürecinin soyutlanması.

4. Decorator Pattern
Kullanım: OdaDecorator, HavuzDecorator, KahvaltiDecorator, OtoparkDecorator

Amaç: Oda nesnesine çalışma zamanında (runtime) ekstra özellikler (havuz, kahvaltı vb.) ekleyerek fiyatın dinamik olarak hesaplanması.

5. State Pattern
Kullanım: IRezervasyonState, Beklemede, Onaylandi, Iptal, Tamamlandi

Amaç: Bir rezervasyonun durum değiştirmesi (Örn: Beklemede -> İptal) ve her durumda farklı davranışlar sergilemesi.

6. Strategy Pattern
Kullanım: PaymentStrategy, Nakit, KrediKarti, StrategyManagment

Amaç: Ödeme algoritmalarının (Nakit veya Kredi Kartı) birbiriyle değiştirilebilir hale getirilmesi.

7. Observer Pattern
Kullanım: RezervasyonSubject, IRezervasyonObserver, Email

Amaç: Rezervasyon durumu değiştiğinde (Örn: Onaylandığında), ilgili birimlerin (Email servisi) otomatik olarak haberdar edilmesi.

💾 Kurulum
Projeyi Klonlayın:

Bash

git clone https://github.com/kullaniciadi/otel-otomasyonu.git
Veritabanını Hazırlayın:

MySQL'de dbotel adında bir veritabanı oluşturun.

Proje içerisindeki database/schema.sql dosyasını çalıştırarak tabloları oluşturun.

Veritabanı Bağlantısını Yapılandırın:

src/main/java/com/otel/database/DatabaseConnection.java dosyasını açın.

Kendi MySQL kullanıcı adı ve şifrenizi girin:

Java

private static final String username = "root";
private static final String password = "sifreniz";
Projeyi Çalıştırın:

Maven bağımlılıklarını yükleyin.

src/main/java/com/otel/Main.java sınıfını çalıştırın.

Ekran Görüntüleri:

Giriş Sayfası:

<img width="723" height="484" alt="image" src="https://github.com/user-attachments/assets/d2a370fa-f510-40f9-90e7-ffd275ff107c" />

Kayıt Ol:
<img width="1173" height="882" alt="Ekran görüntüsü 2025-12-15 230856" src="https://github.com/user-attachments/assets/c45fc8d4-dbd5-4fa3-b17d-3129366759a1" />


Ana Sayfa:
<img width="1474" height="1030" alt="Ekran görüntüsü 2025-12-15 230651" src="https://github.com/user-attachments/assets/1aeaac6e-36dd-4d55-b914-2bc166a5dc4a" />


Personel Rezervasyon Ekleme Ekranı:
<img width="1475" height="1035" alt="Ekran görüntüsü 2025-12-15 230617" src="https://github.com/user-attachments/assets/89038914-e088-44f8-a6d1-ddbab65cf4c7" />


Müşteri Oda Rezerve Etme Ekranı:
<img width="1468" height="1030" alt="Ekran görüntüsü 2025-12-15 230704" src="https://github.com/user-attachments/assets/38441354-0bc2-4300-a276-d91449cedd92" />


Müşteri Profil Ekranı:
<img width="1470" height="1032" alt="Ekran görüntüsü 2025-12-15 230723" src="https://github.com/user-attachments/assets/31d61fd5-07a8-4e8a-8998-6826919d3473" />


Personel Müşteri Detaylarını ve Rezervasyon Detayları Ekranı:
<img width="1469" height="1034" alt="Ekran görüntüsü 2025-12-15 230805" src="https://github.com/user-attachments/assets/026edc40-294c-4f75-85c6-3603c40fef5e" />









