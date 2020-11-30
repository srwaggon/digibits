package com.github.srwaggon.digibits.action;

import com.github.srwaggon.digibits.Monster;

import static java.lang.String.format;

public class PhysicalAttack implements Action {
  public void apply(StringBuilder transcript, Monster actor, Monster receiver) {
    transcript.append(format("%s attacked!\n", actor.getName()));
    int damageTaken = receiver.takeDamage(actor.getStrength());
    transcript.append(format("%s took %d damage.\n", receiver.getName(), damageTaken));
    transcript.append(format("%s is at %d health.\n", receiver.getName(), receiver.getHealthPoints()));
  }

}
