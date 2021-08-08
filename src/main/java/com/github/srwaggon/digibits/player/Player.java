package com.github.srwaggon.digibits.player;

import com.github.srwaggon.digibits.util.Identified;

import java.util.List;
import java.util.UUID;

public class Player implements Identified<String> {

  private String id;

  private String name;

  private List<UUID> monsters;

  public Player() {
  }

  public Player(String id, String name, List<UUID> characters) {
    this.id = id;
    this.name = name;
    this.monsters = characters;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<UUID> getMonsters() {
    return monsters;
  }
}
