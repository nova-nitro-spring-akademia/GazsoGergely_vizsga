package com.gergely.vizsga_0902.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, Long> {



}
