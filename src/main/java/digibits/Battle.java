package digibits;

import digibits.action.ActionType;

import static java.lang.String.format;

public class Battle {

  private StringBuilder transcript = new StringBuilder();

  private final Monster monsterA;
  private final Monster monsterB;

  public Battle(Monster monsterA, Monster monsterB) {
    this.monsterA = monsterA;
    this.monsterB = monsterB;
  }

  public void round(ActionType monsterAAction, ActionType monsterBAction) {
    monsterAAction.apply(transcript, monsterA, monsterB);

    if (monsterB.isDead()) {
      transcript.append(format("%s died!\n", monsterB.getName()));
      return;
    }

    monsterBAction.apply(transcript, monsterB, monsterA);

    if (monsterA.isDead()) {
      transcript.append(format("%s died!\n", monsterA.getName()));
    }
  }

  public String transcript() {
    return transcript.toString();
  }
}
