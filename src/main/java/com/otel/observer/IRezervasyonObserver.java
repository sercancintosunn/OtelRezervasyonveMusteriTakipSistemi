package com.otel.observer;

import com.otel.model.Rezervasyon;

public interface IRezervasyonObserver {

    void update(Rezervasyon rezervasyon, String islem);
}
