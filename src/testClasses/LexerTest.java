package testClasses;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import sound.Lexer;
import sound.Token;
import sound.Token.Type;

/**  
 * File Check:
 * -Whitespace handling
 * -Comments handling
 * -Incorrect tokens handling
 * 
 * Method Check:
 *  -Lexer Constructor
 *  -getTicksPerDefaultNote method
 *  -getTokenList method
 *  
 * Helper Methods Check:
 *  -fractionChange
 *  -LCM
 *  
 */
public class LexerTest {

    @Test
    public void simpleLexerTest() {
        String fileName="piece2";
        String path = "sample_abc/"+fileName+".abc";
        
        //constructor
        Lexer myLexer = new Lexer(path);
        //getTokenList method
        List<Token> allTokens = myLexer.getTokenList();
        //getTicksPerDefaultNote method
        assertEquals(12, myLexer.getTicksPerDefaultNote());
        
        assertEquals(12, myLexer.getTicksPerDefaultNote());
        //checks that beginning of line is handled appropriately
        assertEquals(allTokens.get(0), new Token(Token.Type.OPEN_CHORD, "["));
        
        //checks that accidental token is handled
        assertEquals(allTokens.get(1), new Token(Token.Type.ACCIDENTAL, "^"));
        
        //checks that uppercase and lowercase basenotes are correctly initialized
        assertEquals(allTokens.get(2), new Token(Token.Type.BASENOTE, "F"));
        assertEquals(allTokens.get(3), new Token(Token.Type.TIME, "0.5"));
        
        //checks that time token is handled and initialized correctly
        assertEquals(allTokens.get(5), new Token(Token.Type.TIME, "0.5"));
        
        //checks that whitespace is handled (in the .abc file there is whitespace before "F")
        assertEquals(allTokens.get(7), new Token(Token.Type.OPEN_CHORD, "["));
        
        //check for rest token
        assertEquals(allTokens.get(13), new Token(Token.Type.REST, "z"));
        
        //check for correct bar type initialized
        assertEquals(allTokens.get(33), new Token(Token.Type.MEASURE_BAR, "|"));
        
        //check tuplet handling
        assertEquals(allTokens.get(59), new Token(Token.Type.TUPLET_SPEC, "(3"));
       
        assertEquals(allTokens.get(allTokens.size()-2), new Token(Token.Type.TIME, "0.75"));
        
        //checks end bar handling for end of piece!
        assertEquals(allTokens.get(allTokens.size()-1), new Token(Token.Type.END_BAR, "|]"));
      
    }
    
    
    @Test
    public void complicatedLexerTest(){
        String fileName = "fur_elise";
        String path = "sample_abc/"+fileName+".abc";
        
        Lexer furElise = new Lexer(path);
        List<Token> allTokens = furElise.getTokenList();
        
        //check numTicksperDefaultNote
        assertEquals(12, furElise.getTicksPerDefaultNote());
        
        //check to make sure first token is a voice token
        assertEquals(allTokens.get(0), new Token(Token.Type.VOICE, "1"));
        
        //should move into next line of .abc file
        assertEquals(allTokens.get(1), new Token(Token.Type.BASENOTE, "e"));
        
        //check accidental
        assertEquals(allTokens.get(10), new Token(Token.Type.ACCIDENTAL, "="));
        
        //check for correct time token
        assertEquals(allTokens.get(15), new Token(Token.Type.TIME, "2"));
        
        //check for next voice part
        assertEquals(allTokens.get(37), new Token(Token.Type.VOICE, "2"));
        
        //check that octave symbols are grouped, ",,"
        assertEquals(allTokens.get(45), new Token(Token.Type.OCTAVE, ",,"));
        
        //check that the comment symbol is skipped, "%" does not become a token
        assertEquals(allTokens.get(74), new Token(Token.Type.MEASURE_BAR, "|"));
        assertEquals(allTokens.get(75), new Token(Token.Type.VOICE, "1"));
        
    }
    
    @Test
    public void warmUpLexerTest() {
        String fileName="piece1";
        String path = "sample_abc/" + fileName+ ".abc";
        Lexer myLexer = new Lexer(path);
        
        assertEquals(12, myLexer.getTicksPerDefaultNote());
        //assertEquals(list, myLexer.getTokenList());
        //[Token<BASENOTE:C>, Token<BASENOTE:C>, Token<BASENOTE:C>, Token<TIME:0.75>, Token<BASENOTE:D>, Token<TIME:0.25>, Token<BASENOTE:E>, Token<MEASURE_BAR:|>, Token<BASENOTE:E>, Token<TIME:0.75>, Token<BASENOTE:D>, Token<TIME:0.25>, Token<BASENOTE:E>, Token<TIME:0.75>, Token<BASENOTE:F>, Token<TIME:0.25>, Token<BASENOTE:G>, Token<TIME:2>, Token<MEASURE_BAR:|>, Token<TUPLET_SPEC:(3>, Token<BASENOTE:c>, Token<BASENOTE:c>, Token<BASENOTE:c>, Token<TIME:0.5>, Token<TUPLET_SPEC:(3>, Token<BASENOTE:G>, Token<BASENOTE:G>, Token<BASENOTE:G>, Token<TIME:0.5>, Token<TUPLET_SPEC:(3>, Token<BASENOTE:E>, Token<BASENOTE:E>, Token<BASENOTE:E>, Token<TIME:0.5>, Token<TUPLET_SPEC:(3>, Token<BASENOTE:C>, Token<BASENOTE:C>, Token<BASENOTE:C>, Token<TIME:0.5>, Token<MEASURE_BAR:|>, Token<BASENOTE:G>, Token<TIME:0.75>, Token<BASENOTE:F>, Token<TIME:0.25>, Token<BASENOTE:E>, Token<TIME:0.75>, Token<BASENOTE:D>, Token<TIME:0.25>, Token<BASENOTE:C>, Token<TIME:2>, Token<END_BAR:|]>]
        
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void illegalTokenTest() {
        String fileName="basicHeader";
        String path = "sample_abc/" + fileName+ ".abc";
        new Lexer(path);
    }
    
    @Test
    public void fractionChangeTest() {
        StringBuilder myString = new StringBuilder("/");
        assertEquals(new String("0.5"),Lexer.fractionChange(myString));
        myString = new StringBuilder("3/");
        assertEquals(new String("1.5"),Lexer.fractionChange(myString));
        myString = new StringBuilder("10/");
        assertEquals(new String("5.0"),Lexer.fractionChange(myString));
        myString = new StringBuilder("/2");
        assertEquals(new String("0.5"),Lexer.fractionChange(myString));
        myString = new StringBuilder("/4");
        assertEquals(new String("0.25"),Lexer.fractionChange(myString));
        myString = new StringBuilder("3/4");
        assertEquals(new String("0.75"),Lexer.fractionChange(myString));
        myString = new StringBuilder("12/48");
        assertEquals(new String("0.25"),Lexer.fractionChange(myString));
        myString = new StringBuilder("/1");
        assertEquals(new String("1.0"),Lexer.fractionChange(myString));
        
    }
    
    @Test
    public void lcmTest() {
        //check basic
        assertEquals(150, Lexer.lcm(50,30));
        assertEquals(297, Lexer.lcm(27,99));
        //check zero handling
        assertEquals(0, Lexer.lcm(0,10));
        assertEquals(0, Lexer.lcm(14,0));
        //larger number handling not necessary, as defined in specs 
    }

}
