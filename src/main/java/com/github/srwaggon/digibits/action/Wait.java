package com.github.srwaggon.digibits.action;

import com.github.srwaggon.digibits.monster.Monster;

import static java.lang.String.format;

class Wait implements Action{

  public void apply(StringBuilder transcript, Monster actor, Monster receiver) {
    transcript.append(format("%s is waiting.\n", actor.getName()));
  }
}
