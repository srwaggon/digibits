package com.github.srwaggon.digibits.action;

import com.github.srwaggon.digibits.monster.Monster;

public interface Action {

  void apply(StringBuilder transcript, Monster actor, Monster receiver);
}
