package com.ocr.noel.escapeGame.utils;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConfigUtilTest {

    @Test
    void Given_IntegerString_When_callUtil_getIntegerFromString_Then_GetTheGoodInteger() {
        Integer testInteger = ConfigUtil.getIntegerFromString("1234", "Test good Integer");
        assertEquals(1234, testInteger);
        testInteger = ConfigUtil.getIntegerFromString("bad Integer", "Test bad Integer");
        assertEquals(null, testInteger);
    }

    @Test
    void Given_nothing_When_getSannerIn_Then_getSanner() {
        Scanner scanner = ConfigUtil.getScannerIn();
        assertEquals(Scanner.class, scanner.getClass());
    }

    @Test
    void Given_secretNumberLength_When_call_generateRandomInteger_Then_getIntArray() {
        int[] number = ConfigUtil.generateRandomIntegerArray(4);
        assertEquals(4, number.length);
        number = ConfigUtil.generateRandomIntegerArray(10);
        assertEquals(10, number.length);
        number = ConfigUtil.generateRandomIntegerArray(5);
        assertEquals(5, number.length);
    }

    @Test
    void Given_intArray_When_getIntegerFromIntArray_Then_getTheGoodNumber() {
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6};
        int res = ConfigUtil.getIntFromIntArray(intArray);
        assertEquals(123456, res);
        intArray = new int[]{8, 2, 6, 4};
        res = ConfigUtil.getIntFromIntArray(intArray);
        assertEquals(8264, res);
        intArray = new int[]{9, 3, 5, 4, 8, 7};
        res = ConfigUtil.getIntFromIntArray(intArray);
        assertEquals(935487, res);
    }

    @Test
    void Given_number_When_getIntArrayFromStringNumber_Then_getGoodArray() {
        String number = "1234";
        int[] numberArray = ConfigUtil.getIntArrayFromStringNumber(number);
        assertEquals(1, numberArray[0]);
        assertEquals(2, numberArray[1]);
        assertEquals(3, numberArray[2]);
        assertEquals(4, numberArray[3]);
    }
}