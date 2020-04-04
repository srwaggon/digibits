package digibits.action;

import digibits.Monster;

public interface Action {

  void apply(StringBuilder transcript, Monster actor, Monster receiver);
}
