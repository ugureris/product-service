package com.example.productservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data // Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode anotasyonlarının hepsini barındırır
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "stock_management")  //database'te stock_management şemasında product tablosu oluşacak
public class Product {

    @Id
    @Column(name = "product_id")  //kolon adı
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //primary key'in hangi strateji ile oluşacağı. id 1'den başlayıp artacak
    private long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Builder.Default //constructor'da default olarak her zaman parametre olarak geçecek
    @Column(name = "product_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productUpdatedDate = new Date();

    @Column(name = "product_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productCreatedDate = new Date();

    @Column(name = "is_deleted")
    private boolean deleted;

}
