package rms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordTest {
    public void testComp(int v) {
        Word value = new Word(v);
        int vv = value.value;
        boolean p1 = false;
        if (vv == v)
            p1 = true;
        assertTrue("Expect: "+v+"; But got: "+vv, p1);
    }

    public void testCompA(int v, String s1,String s2, String s3) {
        Word value = new Word(v);
        String hex = value.toString();
        String ascii = value.showAscii();
        String dec = value.showDec();
        boolean p1 = false;
        if (hex.equals(s1) && ascii.equals(s2) && dec.equals(s3))   //Don't use "==" for String
            p1 = true;
        assertTrue("Expect: Hex=>"+s1+" ASCII=>"+s2+" DEC=>"+s3 +"; But got: Hex=>"+hex+" ASCII=>"+ascii+" DEC=>"+dec, p1);
    }

    @Test
    public void testUnitComp(){
        testComp(1);
        testComp(130);
        testCompA(269,"0x0000010d","....","269");
    }
}
