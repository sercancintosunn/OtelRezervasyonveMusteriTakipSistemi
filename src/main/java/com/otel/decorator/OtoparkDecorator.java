package com.otel.decorator;

public class OtoparkDecorator extends OdaDecorator{

    public OtoparkDecorator(IOdaComponent oda){
        super(oda);
    }

    @Override
    public String getAciklama() {
        return super.getAciklama() + " otopark dahil";
    }

    @Override
    public double getFiyat() {
        return super.getFiyat() + 700.0;
    }
}
