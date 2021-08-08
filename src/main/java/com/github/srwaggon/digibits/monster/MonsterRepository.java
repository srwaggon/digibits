package com.github.srwaggon.digibits.monster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.srwaggon.digibits.util.JsonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MonsterRepository extends JsonRepository<Monster, UUID> {

  @Autowired
  public MonsterRepository(ObjectMapper objectMapper) {
    super(objectMapper, Monster.class);
  }
}
