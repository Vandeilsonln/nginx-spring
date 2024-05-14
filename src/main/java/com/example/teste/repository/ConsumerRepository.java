package com.example.teste.repository;

import com.example.teste.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findById(Integer id);

    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE customers SET balance = :balance WHERE id = :id")
    void updateBalance(@Param("balance") Integer balance, @Param("id") Integer id);


}
