package com.example.teste.repository;

import com.example.teste.entity.CustomerEntity;
import com.example.teste.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.customerId.id = :customerId")
    List<TransactionEntity> findByCustomerId(@Param("customerId") Integer customerId);

}
