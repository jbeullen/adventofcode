package be.upgrade.it.adventofcode.day1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InverseCaptchaTest {
    private InverseCaptcha inverseCaptcha;

    @Before
    public void setUp() throws Exception {
        inverseCaptcha = new InverseCaptcha();
    }

    @After
    public void tearDown() throws Exception {
        inverseCaptcha = null;
    }

    @Test
    public void inverseIt_when_inputIs1122_then_outputIs3() {
        int i = inverseCaptcha.inverseIt("1122");

        assertEquals(i, 3);
    }

    @Test
    public void inverseIt_when_inputIs1111_then_outputIs4() {
        int i = inverseCaptcha.inverseIt("1111");

        assertEquals(i, 4);
    }

    @Test
    public void inverseIt_when_inputIs1234_then_outputIs0() {
        int i = inverseCaptcha.inverseIt("1234");

        assertEquals(i, 0);
    }

    @Test
    public void inverseIt_when_inputIs91212129_then_outputIs9() {
        int i = inverseCaptcha.inverseIt("91212129");

        assertEquals(i, 9);
    }
}