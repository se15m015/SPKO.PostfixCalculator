package at.technikum.wien.figl.winterhalder.spko.postfixcalculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by richie on 07/10/16.
 */
public class PostfixCalculatorTest {

    PostfixCalculator pc;

    @Before
    public void PrepareTests(){
        pc = new PostfixCalculator();
    }

    @Test
    public void TestOK1() throws IOException {
        assertEquals(20.0, pc.parse("2 3 + 4 *").doubleValue(), 0.0);
        assertEquals(27.0, pc.parse("3 4 5 +*").doubleValue(), 0.0);
        assertEquals(23.0, pc.parse("3 4 5 *+").doubleValue(), 0.0);
        assertEquals(27.0, pc.parse("3 4 5+*").doubleValue(), 0.0);
        assertEquals(-27.0, pc.parse("-3 4 5+* ").doubleValue(), 0.0);
        assertEquals(27.0, pc.parse("-3 -4 -5 +*").doubleValue(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestEX1()  {
            pc.parse("4 5 +*");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestEX2()  {
            pc.parse("+ 3 4 5 +*");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestEX3()  {
            pc.parse("4 5 5 7 3 +*");
    }

    @Test
    public void TestVar1()  {
        assertEquals(8, pc.parse("i 3 i 5 =+").doubleValue(), 0.0);
        assertEquals(10, pc.parse("x 2 x i 2 i 4 =*=+").doubleValue(), 0.0);
    }
}