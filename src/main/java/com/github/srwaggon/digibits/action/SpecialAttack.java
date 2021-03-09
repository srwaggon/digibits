package com.github.srwaggon.digibits.action;

import com.github.srwaggon.digibits.monster.Monster;

import static java.lang.String.format;

class SpecialAttack implements Action {
  public void apply(StringBuilder transcript, Monster actor, Monster receiver) {
    if (actor.isTooTiredToDoSpecialAttack()) {
      transcript.append(format("%s is too tired to attack!\n", actor.getName()));
    } else {
      transcript.append(format("%s used a special attack!\n", actor.getName()));
      int damageTaken = receiver.takeSpecialDamage(actor);
      transcript.append(format("%s took %d damage.\n", receiver.getName(), damageTaken));
      transcript.append(format("%s is at %d health.\n", receiver.getName(), receiver.getHealthPoints()));
      actor.spendMana();
      transcript.append(format("%s has %d mana left.\n", actor.getName(), receiver.getManaPoints()));
    }
  }

}
