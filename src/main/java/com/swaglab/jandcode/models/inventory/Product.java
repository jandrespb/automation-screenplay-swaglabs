package com.swaglab.jandcode.models.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String nameProduct;
    private String price;

    private static Product instance;

    public static synchronized Product getInstance(){
        if(instance != null){
            return instance;
        }
        instance = setProduct();
        return instance;
    }

    public static Product setProduct(){
        return Product.builder()
                .nameProduct("")
                .price("")
                .build();
    }

    public void setValueProduct(List<String> dataProduct){
        //note: remember that we save our elements as list element when each space of memory
        // that our element of table will save, the space begin on 0
        instance.setNameProduct(dataProduct.get(0).trim());
        instance.setPrice(dataProduct.get(1).trim());
    }

}
