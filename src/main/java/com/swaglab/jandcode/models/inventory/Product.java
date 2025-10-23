package com.swaglab.jandcode.models.inventory;

import java.util.List;
import java.util.Objects;


public class Product {

    private String nameProduct;
    private String price;

    private static Product instance;

    public Product() {
    }

    public Product(String nameProduct, String price) {
        this.nameProduct = nameProduct;
        this.price = price;
    }

    public static synchronized Product getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = setProduct();
        return instance;
    }

    public static Product setProduct() {
        return Product.builder()
                .nameProduct("")
                .price("")
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String nameProduct;
        private String price;

        public Builder nameProduct(String nameProduct) {
            this.nameProduct = nameProduct;
            return this;
        }

        public Builder price(String price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(nameProduct, price);
        }
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setValueProduct(List<String> dataProduct) {
        Product prod = getInstance();
        prod.setNameProduct(dataProduct.get(0).trim());
        prod.setPrice(dataProduct.get(1).trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProduct, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "nameProduct='" + nameProduct + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
