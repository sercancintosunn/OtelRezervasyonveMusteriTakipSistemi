package com.otel.decorator;

public abstract class OdaDecorator implements IOdaComponent {

    protected IOdaComponent oda;

    public OdaDecorator(IOdaComponent oda){
        this.oda = oda;
    }

    @Override
    public String getAciklama() {
        return oda.getAciklama();
    }

    @Override
    public double getFiyat() {
        return oda.getFiyat();
    }
}
