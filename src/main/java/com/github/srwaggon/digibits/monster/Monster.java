package com.github.srwaggon.digibits.monster;

import java.util.stream.Collectors;

import lombok.Getter;

public class Monster {

  private final MonsterClass monsterClass;
  @Getter
  private final String name;
  private int takenDamage = 0;
  private int manaSpent = 0;

  public Monster(MonsterClass monsterClass, String name) {
    this.monsterClass = monsterClass;
    this.name = name;
  }

  public int takeDamage(Monster attacker) {
    int damageTaken = Math.max(1, attacker.monsterClass.getStrength() - monsterClass.getDefense());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int takeSpecialDamage(Monster attacker) {
    int damageTaken = Math.max(1, attacker.monsterClass.getPower() - monsterClass.getResistance());
    takenDamage += damageTaken;
    return damageTaken;
  }

  public int getHealthPoints() {
    return Math.max(0, monsterClass.getHealth() - takenDamage);
  }

  public boolean isDead() {
    return getHealthPoints() <= 0;
  }

  public int getManaPoints() {
    return monsterClass.getMana() - manaSpent;
  }

  public void spendMana() {
    manaSpent += monsterClass.getPower();
  }

  public boolean isTooTiredToDoSpecialAttack() {
    return getManaPoints() < monsterClass.getPower();
  }

  @Override
  public String toString() {
    return "Monster{" +
        "monsterClass=" + monsterClass +
        ", name='" + name + '\'' +
        ", takenDamage=" + takenDamage +
        ", manaSpent=" + manaSpent +
        '}';
  }
}
