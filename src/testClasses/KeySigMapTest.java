package testClasses;
import static org.junit.Assert.*;

import org.junit.Test;

import sound.KeySigMap;

/**
 * a simple test suite to make sure we get the right note values from the right inputs
 * @author meena
 *
 */

public class KeySigMapTest {

    @Test
    public void simpleKeyTest(){
        KeySigMap gMajor = new KeySigMap("G");
        //check for case sensitivity/handling octaves
        assertEquals(0, gMajor.getNoteValue("C"));
        assertEquals(0, gMajor.getNoteValue("c"));
        assertEquals(0, gMajor.getNoteValue("c,,"));
        assertEquals(0, gMajor.getNoteValue("c'"));
        
        //check actual key
        assertEquals(1, gMajor.getNoteValue("F"));
        assertEquals(1, gMajor.getNoteValue("f"));
        assertEquals(1, gMajor.getNoteValue("f'''"));

        
        //check for accidental adding
        gMajor.addAccidental("d'", 1);
        assertEquals(1, gMajor.getNoteValue("d'"));
        assertEquals(0, gMajor.getNoteValue("d''"));
        assertEquals(0, gMajor.getNoteValue("D"));
        
        //check for clearing
        gMajor.clearAccidental();
        assertEquals(0,gMajor.getNoteValue("d'")); 
        
        //check for natural note added to override key signature
        gMajor.addAccidental("F", 0);
        assertEquals(0, gMajor.getNoteValue("F"));
        assertEquals(1, gMajor.getNoteValue("f"));
    }
    
    @Test
    public void minorKeyTest(){
        KeySigMap bMinor = new KeySigMap("Bm");
        //check for case sensitivity/handling octaves
        assertEquals(1, bMinor.getNoteValue("C"));
        assertEquals(1, bMinor.getNoteValue("c"));
        assertEquals(1, bMinor.getNoteValue("c,,"));
        assertEquals(1, bMinor.getNoteValue("c'"));
        
        //check actual key
        assertEquals(1, bMinor.getNoteValue("F"));
        assertEquals(1, bMinor.getNoteValue("f"));
        assertEquals(1, bMinor.getNoteValue("f'''"));

        
        //check for accidental adding
        bMinor.addAccidental("d'", 1);
        assertEquals(1, bMinor.getNoteValue("d'"));
        assertEquals(0, bMinor.getNoteValue("d''"));
        assertEquals(0, bMinor.getNoteValue("D"));
        
        //check for clearing
        bMinor.clearAccidental();
        assertEquals(0,bMinor.getNoteValue("d'")); 
        
        //check for natural note added to override key signature
        bMinor.addAccidental("F", 0);
        assertEquals(0, bMinor.getNoteValue("F"));
        assertEquals(1, bMinor.getNoteValue("f"));
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void checkException(){
        new KeySigMap("bb");
    }
}
