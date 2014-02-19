package testClasses;
import static org.junit.Assert.*;
import org.junit.Test;

import sound.Token;
import sound.Token.Type;

public class TokenTest {
    /* Test cases for Tokens.
     * 
     * Check for all different types of characters.
        BASENOTE,
        REST, // basic rest
        TIME, // time of note cluster
        ACCIDENTAL, //sharp,flat,natural
        OCTAVE, //octave , ' 
        MEASURE_BAR, //measure divider |
        START_REPEAT_BAR, //measure repeat |: 
        END_REPEAT_BAR, //measure repeat :|
        DOUBLE_BAR, //not sure. ||  
        END_BAR, //ultimate end |]
        START_BAR, //ultimate start [|
        NTHREPEAT1, //[1 or 
        NTHREPEAT2, //[2
        TUPLET_SPEC, //defined as "(" [234]
        OPEN_CHORD, // [
        CLOSE_CHORD, // ]
        VOICE, //Voice Token
        NULL
     * 
     */
    
    @Test
    public void baseNoteTest() {
        Token myToken = new Token(Token.Type.BASENOTE, "B");
        assertEquals(Token.Type.BASENOTE, myToken.type);
        assertEquals("B", myToken.text);
    }   
    
    @Test
    public void restTest() {
        Token myToken = new Token(Token.Type.REST, "z");
        assertEquals(Token.Type.REST, myToken.type);
        assertEquals("z", myToken.text);
    }   
    
    @Test
    public void timeTest() {
        Token myToken = new Token(Token.Type.TIME, "2/");
        assertEquals(Token.Type.TIME, myToken.type);
        assertEquals("2/", myToken.text);
    }   
    
    @Test
    public void accidentalTest() {
        Token myToken = new Token(Token.Type.ACCIDENTAL, "^^");
        assertEquals(Token.Type.ACCIDENTAL, myToken.type);
        assertEquals("^^", myToken.text);
    }   
    
    @Test
    public void octaveTest() {
        Token myToken = new Token(Token.Type.OCTAVE, "''");
        assertEquals(Token.Type.OCTAVE, myToken.type);
        assertEquals("''", myToken.text);
    }   
    
    @Test
    public void measureBarTest() {
        Token myToken = new Token(Token.Type.MEASURE_BAR, "|");
        assertEquals(Token.Type.MEASURE_BAR, myToken.type);
        assertEquals("|", myToken.text);
    }   
    
    @Test
    public void startRepeatTest() {
        Token myToken = new Token(Token.Type.START_REPEAT_BAR, "|:");
        assertEquals(Token.Type.START_REPEAT_BAR, myToken.type);
        assertEquals("|:", myToken.text);
    }   
    
    @Test
    public void endRepeatTest() {
        Token myToken = new Token(Token.Type.END_REPEAT_BAR, ":|");
        assertEquals(Token.Type.END_REPEAT_BAR, myToken.type);
        assertEquals(":|", myToken.text);
    } 
    
    @Test
    public void startBarTest() {
        Token myToken = new Token(Token.Type.START_BAR, "[|");
        assertEquals(Token.Type.START_BAR, myToken.type);
        assertEquals("[|", myToken.text);
    }   
    
    @Test
    public void endBarTest() {
        Token myToken = new Token(Token.Type.END_BAR, "|]");
        assertEquals(Token.Type.END_BAR, myToken.type);
        assertEquals("|]", myToken.text);
    }   
    
    @Test
    public void doubleBarTest() {
        Token myToken = new Token(Token.Type.DOUBLE_BAR, "||");
        assertEquals(Token.Type.DOUBLE_BAR, myToken.type);
        assertEquals("||", myToken.text);
    }   
    
    @Test
    public void nthRepeat1Test() {
        Token myToken = new Token(Token.Type.NTHREPEAT1, "[1");
        assertEquals(Token.Type.NTHREPEAT1, myToken.type);
        assertEquals("[1", myToken.text);
    }   
    
    @Test
    public void nthRepeat2Test() {
        Token myToken = new Token(Token.Type.NTHREPEAT2, "[2");
        assertEquals(Token.Type.NTHREPEAT2, myToken.type);
        assertEquals("[2", myToken.text);
    } 
    
    @Test
    public void openChordTest() {
        Token myToken = new Token(Token.Type.OPEN_CHORD, "[");
        assertEquals(Token.Type.OPEN_CHORD, myToken.type);
        assertEquals("[", myToken.text);
    } 
    
    @Test
    public void closeChordTest() {
        Token myToken = new Token(Token.Type.CLOSE_CHORD, "]");
        assertEquals(Token.Type.CLOSE_CHORD, myToken.type);
        assertEquals("]", myToken.text);
    } 
    
    @Test
    public void tupletTest() {
        Token myToken = new Token(Token.Type.TUPLET_SPEC, "(4");
        assertEquals(Token.Type.TUPLET_SPEC, myToken.type);
        assertEquals("(4", myToken.text);
    } 
    
    @Test
    public void voiceTest() {
        Token myToken = new Token(Token.Type.VOICE, "katieSings");
        assertEquals(Token.Type.VOICE, myToken.type);
        assertEquals("katieSings", myToken.text);
    } 
    
    @Test
    public void nullTest() {
        Token myToken = new Token(Token.Type.NULL, "<");
        assertEquals(Token.Type.NULL, myToken.type);
        assertEquals("<", myToken.text);
    } 
}