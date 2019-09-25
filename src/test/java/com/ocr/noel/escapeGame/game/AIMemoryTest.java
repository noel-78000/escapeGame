package com.ocr.noel.escapeGame.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIMemoryTest {

    @Test
    void givenStringResultWhenGetNewNumberThenTtestNewNumber() {
        AIMemory aiMemory = new AIMemory(4);
        int[] numberInitialTemp = aiMemory.getNewNumber("");
        for (int i = 0; i < numberInitialTemp.length; i++) {
            //force initialization with the values of the array below
            numberInitialTemp[i] = (new int[]{4, 2, 6, 9})[i];
        }
        int[] numberInitial = numberInitialTemp.clone();
        int[] numberNew = aiMemory.getNewNumber("+==-");
        assertTrue(numberInitial[0] < numberNew[0]);
        assertTrue(numberInitial[1] == numberNew[1]);
        assertTrue(numberInitial[2] == numberNew[2]);
        assertTrue(numberInitial[3] > numberNew[3]);
        numberInitial = numberNew.clone();
        numberNew = aiMemory.getNewNumber("===-");
        assertTrue(numberInitial[0] == numberNew[0]);
        assertTrue(numberInitial[1] == numberNew[1]);
        assertTrue(numberInitial[2] == numberNew[2]);
        assertTrue(numberInitial[3] > numberNew[3]);
    }
}