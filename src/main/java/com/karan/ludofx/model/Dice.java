package com.karan.ludofx.model;

import java.util.random.RandomGenerator;

/**
 * Class for simple Dice object. Uses default RandomGenerator to return a value 1-6 on each roll() method call
 */
class Dice {
    private final RandomGenerator rnd;
    public Dice() {
        this.rnd = RandomGenerator.getDefault();
    }
    public int roll(){
        return rnd.nextInt(6)+1;
    }
}
