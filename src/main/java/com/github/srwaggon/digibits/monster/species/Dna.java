package com.github.srwaggon.digibits.monster.species;

import java.util.UUID;

public class Dna {

  private UUID id;
  private final long dna;

  public Dna(UUID id) {
    this.id = id;
    this.dna = id.getMostSignificantBits();
  }

  public UUID getId() {
    return id;
  }

  public int select(int startingIndex) {
    return (int) (((long) 0x1f << startingIndex & dna) >>> startingIndex);
  }

  @Override
  public String toString() {
    return "" + dna;
  }
}
