package com.github.srwaggon.digibits.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.srwaggon.digibits.util.JsonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository extends JsonRepository<Player, String> {

  @Autowired
  public PlayerRepository(ObjectMapper objectMapper) {
    super(objectMapper, Player.class);
  }
}
