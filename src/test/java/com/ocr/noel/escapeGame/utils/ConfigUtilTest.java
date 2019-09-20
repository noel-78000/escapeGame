package com.ocr.noel.escapeGame.utils;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConfigUtilTest {

    @Test
    void Given_IntegerString_When_callUtil_getIntegerFromString_Then_GetTheGoodInteger() {
        Integer testInteger = ConfigUtil.getIntegerFromString("1234", "");
        assertEquals(1234, testInteger);
        testInteger = ConfigUtil.getIntegerFromString("bad Integer", "");
        assertEquals(null, testInteger);
    }

    @Test
    void Given_nothing_When_getSannerIn_Then_getSanner() {
        Scanner scanner = ConfigUtil.getScannerIn();
        assertEquals(Scanner.class, scanner.getClass());
    }
}