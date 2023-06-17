package com.angelopicc.saute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
