package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
}
