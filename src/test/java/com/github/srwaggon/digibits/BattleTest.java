package com.github.srwaggon.digibits;


import com.github.srwaggon.digibits.battle.Battle;
import com.github.srwaggon.digibits.monster.Monster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.github.srwaggon.digibits.action.ActionType.PHYSICAL_ATTACK;
import static com.github.srwaggon.digibits.action.ActionType.SPECIAL_ATTACK;
import static com.github.srwaggon.digibits.action.ActionType.WAIT;
import static org.assertj.core.api.Assertions.assertThat;

public class BattleTest {

  private final Monster sven = new Monster(UUID.fromString("4546d0e0-988a-4517-b79c-2d25ae4f7e7f"), "Sven");
  private final Monster yarl = new Monster(UUID.fromString("2252bea3-5146-4f78-90ae-2180e74a0bb0"), "Yarl");
  private final Monster freya = new Monster(UUID.fromString("51368a98-58ea-4e1a-a500-c27c7b6ee9fd"), "Freya");
  private final Monster ort = new Monster(UUID.fromString("b813fd7c-4fff-4ba7-a720-4a2b1fbf1af5"), "Ort");

  @BeforeEach
  public void setUp() {
    System.out.println(sven);
    System.out.println(yarl);
    System.out.println(freya);
    System.out.println(ort);
  }

  @Test
  public void aRoundConsistsOfMovesFromTwoPlayers() {
    Battle battle = new Battle(sven, yarl);

    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);

    assertThat(battle.transcript()).isEqualTo("" +
        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 53 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 45 health.\n" +
        ""
    );
  }

  @Test
  public void monstersCanBeDifferent() {
    Battle battle = new Battle(freya, ort);

    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);

    assertThat(battle.transcript()).isEqualTo("" +
        "Freya attacked!\n" +
        "Ort took 7 damage.\n" +
        "Ort is at 30 health.\n" +
        "Ort attacked!\n" +
        "Freya took 19 damage.\n" +
        "Freya is at 37 health.\n" +
        ""
    );
  }

  @Test
  public void monstersDieWhenHpIsBelowZero() {
    Battle battle = new Battle(sven, yarl);

    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);

    assertThat(battle.transcript()).isEqualTo("" +
        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 53 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 45 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 52 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 37 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 51 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 29 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 50 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 21 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 49 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 13 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 48 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 5 health.\n" +

        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 47 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 0 health.\n" +

        "Sven died!\n" +
        ""
    );
  }

  @Test
  public void monstersCanDoSpecialAttacks() {
    Battle battle = new Battle(sven, yarl);

    battle.round(PHYSICAL_ATTACK, PHYSICAL_ATTACK);
    battle.round(SPECIAL_ATTACK, SPECIAL_ATTACK);

    assertThat(battle.transcript()).isEqualTo("" +
        "Sven attacked!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 53 health.\n" +
        "Yarl attacked!\n" +
        "Sven took 8 damage.\n" +
        "Sven is at 45 health.\n" +

        "Sven used a special attack!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 52 health.\n" +
        "Sven has 49 mana left.\n" +
        "Yarl used a special attack!\n" +
        "Sven took 1 damage.\n" +
        "Sven is at 44 health.\n" +
        "Yarl has 45 mana left.\n" +
        ""
    );
  }

  @Test
  public void monstersCanRunOutOfMana() {
    Battle battle = new Battle(sven, yarl);

    battle.round(SPECIAL_ATTACK, SPECIAL_ATTACK);
    battle.round(SPECIAL_ATTACK, SPECIAL_ATTACK);
    battle.round(SPECIAL_ATTACK, SPECIAL_ATTACK);
    battle.round(SPECIAL_ATTACK, SPECIAL_ATTACK);

    assertThat(battle.transcript()).isEqualTo("" +
        "Sven used a special attack!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 53 health.\n" +
        "Sven has 49 mana left.\n" +
        "Yarl used a special attack!\n" +
        "Sven took 1 damage.\n" +
        "Sven is at 52 health.\n" +
        "Yarl has 45 mana left.\n" +

        "Sven used a special attack!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 52 health.\n" +
        "Sven has 36 mana left.\n" +
        "Yarl used a special attack!\n" +
        "Sven took 1 damage.\n" +
        "Sven is at 51 health.\n" +
        "Yarl has 43 mana left.\n" +

        "Sven used a special attack!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 51 health.\n" +
        "Sven has 23 mana left.\n" +
        "Yarl used a special attack!\n" +
        "Sven took 1 damage.\n" +
        "Sven is at 50 health.\n" +
        "Yarl has 41 mana left.\n" +

        "Sven used a special attack!\n" +
        "Yarl took 1 damage.\n" +
        "Yarl is at 50 health.\n" +
        "Sven has 10 mana left.\n" +
        "Yarl is too tired to attack!\n" +
        ""
    );
  }

  @Test
  public void monstersCanWait() {
    Battle battle = new Battle(sven, yarl);

    battle.round(WAIT, WAIT);

    assertThat(battle.transcript()).isEqualTo("" +
        "Sven is waiting.\n" +
        "Yarl is waiting.\n" +
        ""
    );
  }
}
