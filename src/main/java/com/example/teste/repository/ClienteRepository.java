package com.example.teste.repository;

import com.example.teste.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findById(Integer id);

    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE clientes SET saldo = :saldo WHERE id = :id")
    void updateSaldo(@Param("saldo") Integer saldo, @Param("id") Integer id);


}
