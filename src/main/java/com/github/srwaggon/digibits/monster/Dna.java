package com.github.srwaggon.digibits.monster;

import java.util.UUID;

class Dna {

  private final long dna;

  public Dna(UUID dna) {
    this.dna = dna.getMostSignificantBits();
  }

  public int select(int startingIndex) {
    return (int) (((long) 0x1f << startingIndex & dna) >>> startingIndex);
  }

  @Override
  public String toString() {
    return "" + dna;
  }
}
