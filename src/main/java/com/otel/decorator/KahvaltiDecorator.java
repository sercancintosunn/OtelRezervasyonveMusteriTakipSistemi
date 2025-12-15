package com.otel.decorator;

public class KahvaltiDecorator extends OdaDecorator{

    public KahvaltiDecorator(IOdaComponent oda){
        super(oda);
    }

    @Override
    public String getAciklama() {
        return super.getAciklama() + " Kahvaltı dahil";
    }

    @Override
    public double getFiyat() {
        return super.getFiyat() + 300.0;
    }
}
