package testClasses;

import static org.junit.Assert.*;
import org.junit.Test;

import sound.Token;
import sound.Token.Type;

import abstractSyntaxTree.Element;
import abstractSyntaxTree.Note;
import abstractSyntaxTree.Rest;
import abstractSyntaxTree.Voice;

import java.util.ArrayList;
import java.util.List;

public class VoiceTest {
    
    @Test
    public void simpleVoiceTest(){
        Voice simpleVoice = new Voice("simple");
        //add a c note
        Note first  = new Note(new Token(Token.Type.BASENOTE, "c"), 1, 0, 12);
        assertEquals("#C':12 ticks", first.toString());
        simpleVoice.addElement(first);
        assertEquals(12, simpleVoice.getGlobalTicks());
        Rest second = new Rest(6);
        simpleVoice.addElement(second);
        assertEquals(18, simpleVoice.getGlobalTicks());
        
        //call a repeat and make sure the correct elements are duplicated
        simpleVoice.repeat();
        assertEquals(4, simpleVoice.getElements().size());
        assertEquals(first, simpleVoice.getElements().get(0));
        assertEquals(second, simpleVoice.getElements().get(1));
        assertEquals(first, simpleVoice.getElements().get(2));
        assertEquals(second, simpleVoice.getElements().get(3));
        assertEquals(36, simpleVoice.getGlobalTicks());
        
        //call an open repeat
        simpleVoice.setOpenRepeat();
        simpleVoice.addElement(first);
        simpleVoice.addElement(first);
        simpleVoice.addElement(second);
        assertEquals(66, simpleVoice.getGlobalTicks());
        
        //now repeat again
        simpleVoice.repeat();
        List<Element> voiceElems = simpleVoice.getElements();
        assertEquals(10, voiceElems.size());
        assertEquals(96, simpleVoice.getGlobalTicks());
        assertEquals(first, simpleVoice.getElements().get(4));
        assertEquals(first, simpleVoice.getElements().get(5));
        assertEquals(second, simpleVoice.getElements().get(6));
        assertEquals(first, simpleVoice.getElements().get(7));
        assertEquals(first, simpleVoice.getElements().get(8));
        assertEquals(second, simpleVoice.getElements().get(9));       
    }
    
    @Test
    public void testAlternateEndings(){
        Voice alternateVoice = new Voice("alternate");
        Note first  = new Note(new Token(Token.Type.BASENOTE, "d"), 1, 0, 8);
        Note second  = new Note(new Token(Token.Type.BASENOTE, "e"), 1, 0, 6);
        
        alternateVoice.addElement(first);
        alternateVoice.addElement(first);
        alternateVoice.addElement(second);
        alternateVoice.addElement(first);
        
        //check that elements are added:
        assertEquals(first, alternateVoice.getElements().get(0));
        assertEquals(first, alternateVoice.getElements().get(1));
        assertEquals(second, alternateVoice.getElements().get(2));
        assertEquals(first, alternateVoice.getElements().get(3));
        
        assertEquals(30, alternateVoice.getGlobalTicks());
        
        //call nth repeat1 and then fill in some elements as the first ending
        alternateVoice.setNthRepeatOne();
        alternateVoice.addElement(second);
        alternateVoice.addElement(second);
        alternateVoice.addElement(second);
        assertEquals(48, alternateVoice.getGlobalTicks());
        assertEquals(second, alternateVoice.getElements().get(4));
        assertEquals(second, alternateVoice.getElements().get(5));
        assertEquals(second, alternateVoice.getElements().get(6));
        
        //call the point where you start the second ending
        //should add in the first four elements
        alternateVoice.alternateEnding2();
        assertEquals(78, alternateVoice.getGlobalTicks());

        assertEquals(first, alternateVoice.getElements().get(7));
        assertEquals(first, alternateVoice.getElements().get(8));
        assertEquals(second, alternateVoice.getElements().get(9));
        assertEquals(first, alternateVoice.getElements().get(10));
        
    }

}
