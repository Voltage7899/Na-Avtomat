package com.company.apteka;

public class ProductModel {
    private String id_product;
    private String id_manufacturer;
    private String name_product;
    private String decr;
    private String shelf_life;
    private String batch_number;
    private String id_provider;
    private String id_category;
    private String price;



    public ProductModel(){

    }

    public ProductModel(String id_product, String id_manufacturer, String name_product, String decr, String shelf_life, String batch_number, String id_provider, String id_category,String price) {
        this.id_product = id_product;
        this.id_manufacturer = id_manufacturer;
        this.name_product = name_product;
        this.decr = decr;
        this.shelf_life = shelf_life;
        this.batch_number = batch_number;
        this.id_provider = id_provider;
        this.id_category = id_category;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(String id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getDecr() {
        return decr;
    }

    public void setDecr(String decr) {
        this.decr = decr;
    }

    public String getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(String shelf_life) {
        this.shelf_life = shelf_life;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getId_provider() {
        return id_provider;
    }

    public void setId_provider(String id_provider) {
        this.id_provider = id_provider;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }
}
