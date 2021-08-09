package com.github.srwaggon.digibits.monster.species;

public class Attribute {
  private final int dnaIndex;
  private final int base;

  public Attribute(int dnaIndex, int base) {
    this.dnaIndex = dnaIndex;
    this.base = base;
  }

  public int value(Dna dna) {
    return base + dna.select(dnaIndex);
  }

}
