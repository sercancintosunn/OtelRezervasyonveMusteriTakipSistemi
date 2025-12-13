package com.otel.state;

import com.otel.model.Rezervasyon;

public interface IRezervasyonState {

    void onayla(Rezervasyon rezervasyon);

    void iptalEt(Rezervasyon rezervasyon);

    void tamamla(Rezervasyon rezervasyon);

    String getDurumAdi();
}
