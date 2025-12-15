🏨 Otel Rezervasyon ve Müşteri Takip Sistemi
Bu proje, Java (Swing) kullanılarak geliştirilmiş, MVC (Model-View-Controller) mimarisine uygun, kapsamlı bir otel rezervasyon ve yönetim otomasyonudur. Proje, nesne tabanlı programlama prensiplerini ve birçok Yazılım Tasarım Kalıbını (Design Patterns) aktif olarak kullanmaktadır.

🚀 Özellikler
Kullanıcı Yönetimi:

Müşteri ve Personel için ayrı giriş/kayıt ekranları.

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
