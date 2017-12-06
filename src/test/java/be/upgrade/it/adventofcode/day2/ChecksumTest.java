package be.upgrade.it.adventofcode.day2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChecksumTest {

    @Test
    public void checksum() {
        Checksum checksum = new Checksum();
        int i = checksum.checksum("5\t1\t9\t5\n7\t5\t3\n2\t4\t6\t8");

        assertEquals(18,i);
    }
}