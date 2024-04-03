package com.example.teste.repository;

import com.example.teste.entity.ClienteEntity;
import com.example.teste.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

}
