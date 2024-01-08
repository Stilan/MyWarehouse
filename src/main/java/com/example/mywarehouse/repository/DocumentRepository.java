package com.example.mywarehouse.repository;


import com.example.mywarehouse.entity.Document;
import com.example.mywarehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

}
