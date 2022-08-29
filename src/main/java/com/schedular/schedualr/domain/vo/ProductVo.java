package com.schedular.schedualr.domain.vo;

public class ProductVo {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
