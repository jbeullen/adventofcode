package be.upgrade.it.adventofcode.day3;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpiralMemoryTest {

    @Test
    public void calculateSteps_when_input_1_then_steps_0() {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(1);

        assertEquals(0,i);
    }

    @Test
    public void calculateSteps_when_input_2_then_steps_1() {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(2);

        assertEquals(1,i);
    }

    @Test
    public void calculateSteps_when_input_12_then_steps_3() {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(12);

        assertEquals(3,i);
    }

    @Test
    public void calculateSteps_when_input_23_then_steps_2() {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(23);

        assertEquals(2,i);
    }

    @Test
    public void calculateSteps_when_input_1024_then_steps_31() {
        SpiralMemory spiralMemory = new SpiralMemory();
        int i = spiralMemory.calculateSteps(1024);

        assertEquals(31,i);
    }
}