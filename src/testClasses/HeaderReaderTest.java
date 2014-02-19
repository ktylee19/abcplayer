package testClasses;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import sound.HeaderReader;

import abstractSyntaxTree.Voice;


public class HeaderReaderTest {
    /**
     * Different things to check within the file:
     * -basic XTK
     * -basic XTK + all extra methods
     * -comments in the header
     * 
     * Different methods to check: 
     *  -constructor
     *  -getComposer
     *  -getMeter
     *  -getDefaultNoteLength
     *  -getTempo
     *  
     */
    
    @Test
    public void basicHeaderReaderTest() {
        //test basic header reader only.
        String fileName="basicHeader";
        String path = "sample_abc/"+fileName+".abc";
        HeaderReader myHeader = new HeaderReader(path);
        
        assertEquals("Unknown", myHeader.getComposer());
        
        assertEquals(1.0, myHeader.getMeter(), 0);
        assertEquals(8, myHeader.getDefaultNoteLength(), 0);
        assertEquals(100, myHeader.getTempo());
        
        
    }
    
    @Test
    public void simpleHeaderReaderTest() {    
        String fileName="scale";
        String path = "sample_abc/"+fileName+".abc";
        HeaderReader myHeader = new HeaderReader(path);
        
        assertEquals("Unknown", myHeader.getComposer());
        assertEquals(1.0, myHeader.getMeter(), 0);
        assertEquals(4, myHeader.getDefaultNoteLength(), 0);
        assertEquals(120, myHeader.getTempo());
    }
    
    @Test
    public void commentsInHeaderTest(){
        String fileName="commentHeader";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        HeaderReader myHeader = new HeaderReader(path);
        
        assertEquals("Unknown", myHeader.getComposer());
        assertEquals(1.0, myHeader.getMeter(), 0);
        assertEquals(4, myHeader.getDefaultNoteLength(), 0);
        assertEquals(120, myHeader.getTempo());
    }

    
    @Test
    public void inventionHeaderTest() {
        String fileName="invention";
        String path = "sample_abc/"+fileName+".abc";
        HeaderReader myHeader = new HeaderReader(path);
        //Header Reader works to give right values.
        //The known error:  getDefaultNoteLength and getMeter does not initially check for 
        //                  non-legitimate values because I couldn't get commented-out
        //                  fractions (forwardslash specifically) to work.
        
        assertEquals("Johann Sebastian Bach", myHeader.getComposer());
        assertEquals(1.0, myHeader.getMeter(), 0);
        assertEquals(8.0, myHeader.getDefaultNoteLength(), 0);
        assertEquals(140, myHeader.getTempo());
        assertEquals("1", myHeader.getVoices().get(0).toString());
        assertEquals("2", myHeader.getVoices().get(1).toString());
        
        
    }
    
    @Test
    public void HeaderReaderTest2() {
        String fileName="piece2";
        String path = "sample_abc/"+fileName+".abc";
        HeaderReader header = new HeaderReader(path);

        assertEquals("Unknown", header.getComposer());
        assertEquals(4.0, header.getDefaultNoteLength(), 0);
        assertEquals(200, header.getTempo());
        assertEquals(1.0, header.getMeter(), 0);
        assertEquals("Default", header.getVoices().get(0).toString());
        assertEquals(1, header.getVoices().size());
    }
    
    @Test
    public void furEliseHeaderTest() {
        String fileName = "fur_elise";
        String path = "sample_abc/"+fileName+".abc";
        
        HeaderReader myHeader = new HeaderReader(path);
        assertEquals("Ludwig van Beethoven", myHeader.getComposer());
        assertEquals(0.375, myHeader.getMeter(), 0);
        assertEquals(16.0, myHeader.getDefaultNoteLength(), 0);
        assertEquals(240, myHeader.getTempo());
        List<Voice> voiceList = myHeader.getVoices();
        assertEquals("1", voiceList.get(0).toString());
        assertEquals("2", voiceList.get(1).toString());
        assertEquals(2, voiceList.size());
    }

}
