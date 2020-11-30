package com.github.srwaggon.digibits;

import java.util.UUID;

class Dna {

  private final long dna;

  public Dna(UUID dna) {
    this.dna = dna.getMostSignificantBits();
  }

  public int getHealth() {
    return 30 + (int) (0x1f & dna);
  }

  public int getStamina() {
    return select(5);
  }

  public int getMana() {
    return 30 + select(10);
  }

  public int getRegeneration() {
    return select(15);
  }

  public int getStrength() {
    return select(20);
  }

  public int getDefense() {
    return select(25);
  }

  public int getPower() {
    return select(30);
  }

  public int getResistance() {
    return select(35);
  }

  public int getSpeed() {
    return select(40);
  }

  public int getEvasion() {
    return select(45);
  }

  private int select(int startingIndex) {
    return (int) (((long) 0x1f << startingIndex & dna) >>> startingIndex);
  }

  @Override
  public String toString() {
    return "Dna{" +
        "  health: " + getHealth() + ",\n" +
        "  stamina: " + getStamina() + ",\n" +
        "  strength: " + getStrength() + ",\n" +
        "  defense: " + getDefense() + ",\n" +
        "  mana: " + getMana() + ",\n" +
        "  regeneration: " + getRegeneration() + ",\n" +
        "  power: " + getPower() + ",\n" +
        "  resistance: " + getResistance() + ",\n" +
        "  speed: " + getSpeed() + ",\n" +
        "  evasion: " + getEvasion() + "\n" +
        "}";
  }
}
