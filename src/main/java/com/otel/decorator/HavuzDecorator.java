package com.otel.decorator;

public class HavuzDecorator extends OdaDecorator{

    public HavuzDecorator(IOdaComponent oda){
        super(oda);
    }

    @Override
    public String getAciklama() {
        return super.getAciklama() + " Havuz dahil";
    }

    @Override
    public double getFiyat() {
        return super.getFiyat() + 500.0;
    }
}
