package com.example.productservice.repository;

import com.example.productservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* CRUD işlemlerini sağlamak için kullanılır
* 3 farklı şekilde extend edilebilir
* CrudRepository: CRUD işlemlerini sağlar. save, saveAll, findById, findAll, delete vs metotlarını barındırır
* PagingAndSortingRepository: Pagination ve kayıtları sıralama işlemlerini içerir. içinde findAll metotları bulunur
* JpaRepository: CrudRepository ve PaginationAndSortingRepository'nin içerdiği tüm işlemleri barındırır
*
*  */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getByProductIdAndDeletedFalse(Long productId); //parametre olarak verilen id ile database'e git ve is_Deleted false ise kaydı getir

    List<Product> getAllByDeletedFalse(); //is_deleted false olan tüm alanları database'ten çek

}
