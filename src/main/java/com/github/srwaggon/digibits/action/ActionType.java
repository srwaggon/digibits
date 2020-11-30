package com.github.srwaggon.digibits.action;

import com.github.srwaggon.digibits.Monster;

public enum ActionType {
  PHYSICAL_ATTACK(new PhysicalAttack()),
  SPECIAL_ATTACK(new SpecialAttack()),
  WAIT(new Wait()),
  REST(new Rest());

  private final Action action;

  ActionType(Action action) {
    this.action = action;
  }

  public void apply(StringBuilder transcript, Monster actor, Monster receiver) {
    action.apply(transcript, actor, receiver);
  }
}
