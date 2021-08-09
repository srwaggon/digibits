package com.github.srwaggon.digibits.monster.species;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Species {

  private final Dna dna;

  public static final String HEALTH = "health";
  public static final String STAMINA = "stamina";
  public static final String ENERGY = "energy";
  public static final String REGENERATION = "regeneration";
  public static final String STRENGTH = "strength";
  public static final String DEFENSE = "defense";
  public static final String POWER = "power";
  public static final String RESISTANCE = "resistance";
  public static final String SPEED = "speed";
  public static final String EVASION = "evasion";
  private static final Map<String, Attribute> sharedAttributes = new HashMap<>();

  static {
    calculateAttributes();
  }

  public Species(UUID dna) {
    this.dna = new Dna(dna);
  }

  private static void calculateAttributes() {
    addSharedAttribute(HEALTH, 0, 30);
    addSharedAttribute(STAMINA, 5, 0);
    addSharedAttribute(ENERGY, 10, 30);
    addSharedAttribute(REGENERATION, 15, 0);
    addSharedAttribute(STRENGTH, 20, 0);
    addSharedAttribute(DEFENSE, 25, 0);
    addSharedAttribute(POWER, 30, 0);
    addSharedAttribute(RESISTANCE, 35, 0);
    addSharedAttribute(SPEED, 40, 0);
    addSharedAttribute(EVASION, 45, 0);
  }

  private static void addSharedAttribute(String name, int dnaIndex, int base) {
    sharedAttributes.put(name, new Attribute(dnaIndex, base));
  }

  public Dna getDna() {
    return dna;
  }

  public int getHealth() {
    return getValueOfAttribute(HEALTH);
  }

  public int getStamina() {
    return getValueOfAttribute(STAMINA);
  }

  public int getEnergy() {
    return getValueOfAttribute(ENERGY);
  }

  public int getRegeneration() {
    return getValueOfAttribute(REGENERATION);
  }

  public int getStrength() {
    return getValueOfAttribute(STRENGTH);
  }

  public int getDefense() {
    return getValueOfAttribute(DEFENSE);
  }

  public int getResistance() {
    return getValueOfAttribute(RESISTANCE);
  }

  public int getPower() {
    return getValueOfAttribute(POWER);
  }

  public int getSpeed() {
    return getValueOfAttribute(SPEED);
  }

  public int getEvasion() {
    return getValueOfAttribute(EVASION);
  }

  private int getValueOfAttribute(String attributeName) {
    return sharedAttributes.get(attributeName).value(dna);
  }

  @Override
  public String toString() {
    return "MonsterClass{" +
        "dna=" + dna +
        ", attributes{" + sharedAttributes.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue().value(dna)).collect(Collectors.joining(", ")) + "}" +
        '}';
  }

}
