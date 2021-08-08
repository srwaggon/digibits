package com.github.srwaggon.digibits.monster;

import com.github.srwaggon.digibits.util.Identified;

import java.io.Serializable;
import java.util.UUID;

public class Monster implements Identified<UUID> {

  private final MonsterClass monsterClass;
  private UUID id = UUID.randomUUID();
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

  public String getName() {
    return name;
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

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setId(UUID id) {
    this.id = id;
  }
}
