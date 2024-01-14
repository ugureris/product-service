package com.example.productservice.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
    //productId vermiyoruz çünkü product entity'de id'nin otomatik artacağı ayarlanmıştı
    private String productName;
    private Integer quantity;
    private Double price;

    //created ve updated date alanları otomatik setleneceği için buraya eklemiyoruz


}
