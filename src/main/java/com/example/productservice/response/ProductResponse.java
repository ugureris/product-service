package com.example.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    //response'ta görmek isteyeceğimiz tüm alanlar eklenecek
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Long productCreatedDate; //date kısımları entity class'ta date olarak tanımlanmıştı.
    private Long productUpdatedDate; //burada long olarak tanımlanma nedeni response'u setlerken controller'da gelen değern getTime metodunun kullanılması
}
