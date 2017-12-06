package be.upgrade.it.adventofcode.day4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PassphraseTest {
    private Passphrase passphrase = new Passphrase();

    @Test
    public void isValid_when_valid() {
        boolean valid = passphrase.isValid("aa bb cc dd ee");
        assertTrue(valid);
    }

    @Test
    public void isValid_when_notValid() {
        boolean valid = passphrase.isValid("aa bb cc dd aa");
        assertFalse(valid);
    }

    @Test
    public void isValid_when_valid2() {
        boolean valid = passphrase.isValid("aa bb cc dd aaa");
        assertTrue(valid);
    }
}