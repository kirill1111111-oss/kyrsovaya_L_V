package com.devcolibri.secure.repository;

import com.devcolibri.secure.entity.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PCRepository extends JpaRepository<PC, Long> {//создает обращения для БД
    List<PC> findByTitle(String title);

}
