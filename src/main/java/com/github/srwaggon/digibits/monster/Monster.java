package com.github.srwaggon.digibits.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.srwaggon.digibits.monster.species.Species;
import com.github.srwaggon.digibits.util.Identified;

import java.util.UUID;

public class Monster implements Identified<UUID> {

  private UUID id = UUID.randomUUID();
  private Species species;
  private final String name;
  private int takenDamage = 0;
  private int energySpent = 0;
  private int hunger = 10;

  public Monster(UUID speciesId, String name) {
    this.species = new Species(speciesId);
    this.name = name;
  }

  @JsonIgnore
  public Species getSpecies() {
    return species;
  }

  public int takeDamage(Monster attacker) {
    int damageTaken = Math.max(1, attacker.species.getStrength() - species.getDefense());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int takeSpecialDamage(Monster attacker) {
    int damageTaken = Math.max(1, attacker.species.getPower() - species.getResistance());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int getHealthPoints() {
    return Math.max(0, species.getHealth() - takenDamage);
  }

  @JsonIgnore
  public boolean isDead() {
    return getHealthPoints() <= 0;
  }

  public int getEnergyPoints() {
    return species.getEnergy() - energySpent;
  }

  public void spendEnergy() {
    energySpent += species.getPower();
  }

  @JsonIgnore
  public boolean isTooTiredToDoSpecialAttack() {
    return getEnergyPoints() < species.getPower();
  }

  public String getName() {
    return name;
  }

  public void feed() {
    hunger = Math.max(0, hunger - 1);
  }

  public int getHunger() {
    return hunger;
  }

  @Override
  public String toString() {
    return "Monster{" +
        "monsterClass=" + species +
        ", name='" + name + '\'' +
        ", takenDamage=" + takenDamage +
        ", energySpent=" + energySpent +
        '}';
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getSpeciesId() {
    return getSpecies().getDna().getId();
  }

  public void setSpeciesId(UUID speciesId) {
    this.species = new Species(speciesId);
  }
}
