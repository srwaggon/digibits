package digibits.action;

import digibits.Monster;

import static java.lang.String.format;

class Wait implements Action{

  public void apply(StringBuilder transcript, Monster actor, Monster receiver) {
    transcript.append(format("%s is waiting.\n", actor.getName()));
  }
}
