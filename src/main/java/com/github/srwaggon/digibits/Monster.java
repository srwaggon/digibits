package com.github.srwaggon.digibits;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Monster {

  @Getter
  private final String name;
  private final Dna dna;
  private int takenDamage = 0;
  private int manaSpent = 0;

  public Monster(String name, UUID dna) {
    this.name = name;
    this.dna = new Dna(dna);
  }

  static Monster newMonster() {
    UUID dna = UUID.randomUUID();
    return new Monster(dna.toString(), dna);
  }

  public boolean isDead() {
    return getHealthPoints() <= 0;
  }

  public int getHealthPoints() {
    return Math.max(0, getHealth() - takenDamage);
  }

  public int getHealth() {
    return dna.getHealth();
  }

  public int getStrength() {
    return dna.getStrength();
  }

  public int takeDamage(int damage) {
    int damageTaken = Math.max(1, damage - dna.getDefense());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int getPower() {
    return dna.getPower();
  }

  public int takeSpecialDamage(int damage) {
    int damageTaken = Math.max(1, damage - dna.getResistance());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int getManaPoints() {
    return dna.getMana() - manaSpent;
  }

  public void spendMana() {
    manaSpent += dna.getPower();
  }

  public boolean isTooTiredToDoSpecialAttack() {
    return getManaPoints() < getPower();
  }
}
