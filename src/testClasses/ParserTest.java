package testClasses;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import sound.Lexer;
import sound.Parser;

import abstractSyntaxTree.Voice;

/** 
 * Test Cases:
 * 
 * All files in sample_abc, which cover:
 * -Valid one-voice piece (ex. scale.abc)
 * -Valid two-voice piece (ex. fur_elise.abc)
 * -Valid multiple-voice piece (ex. prelude.abc)
 * 
 * -Basic note (ex. scale.abc)
 * -Basic rest (ex. prelude.abc)
 * -Note with accidental, octave, and time (ex. little_night_music.abc) 
 * -Note with accidental and octave  (ex. little_night_music.abc)
 * -Note with accidental and time (ex. little_night_music.abc)
 * -Note with octave and time (ex. little_night_music.abc)
 * -Basic chord (ex. little_night_music.abc)
 * 
 * -2 Tuplet (ex. valid_onevoice.abc)
 * -3 Tuplet (ex. valid_onevoice.abc)
 * -4 Tuplet (ex. valid_onevoice.abc)
 * -Tuplet with cut time (ex. valid_onevoice.abc)
 * 
 * -Whole piece repeat (ex. valid_onevoice.abc)
 * -Start bar piece repeat (ex. fur_elise.abc)
 * -Alternate ending test (ex. paddy.abc)
 *
 * -Up 1 octave (uppercase, lowercase) (ex. octave_up1_lower.abc, octave_up1_upper.abc)
 * -Up 4 octave(uppercase, lowercase) (ex. octave_up4_lower.abc, octave_up4_upper.abc)
 * -Down 1 octave(uppercase, lowercase) (ex. octave_down1_lower.abc, octave_down1_upper.abc)
 * -Down 4 octave (uppercase, lowercase) (ex. octave_down4_lower.abc, octave_down4_upper.abc)
 * 
 * -Accidental in default key (ex. valid_onevoice.abc)
 * -Accidental in different key (ex. valid_onevoice.abc)
 * -Accidental reset per different line (ex. valid_onevoice.abc)
 * -Accidental handling for notes in different octaves (ex. valid_onevoice.abc)
 *
 * -All different bars (seen across many examples) 
 * 
 * -Different Tempos (seen across many examples)
 * 
 * Invalid Tests
 * -rest+accidental (ex. invalid_restaccidental.abc)
 * -rest+octave (ex. invalid_restoctave.abc)
 * -Unequal repeats (ex. unequalrepeats.abc)
 * 
 */
public class ParserTest {
    //sample_abc files
    
    /**
     * Tests piece 1
     */
    @Test
    public void piece1ParserTest() {
        String fileName="piece1";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:12 ticks\nC:12 ticks\nC:9 ticks\nD:3 ticks\nE:12 ticks\nE:9 ticks\nD:3 ticks\nE:9 ticks\nF:3 ticks\nG:24 ticks\n(3C':4 ticks C':4 ticks C':4 ticks \n(3G:4 ticks G:4 ticks G:4 ticks \n(3E:4 ticks E:4 ticks E:4 ticks \n(3C:4 ticks C:4 ticks C:4 ticks \nG:9 ticks\nF:3 ticks\nE:9 ticks\nD:3 ticks\nC:24 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests piece 2
     */
    @Test
    public void piece2ParserTest() {
        String fileName="piece2";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "[#F:6 ticks E':6 ticks ]\n[#F:6 ticks E':6 ticks ]\nRest:6 ticks\n[#F:6 ticks E':6 ticks ]\nRest:6 ticks\n[#F:6 ticks C':6 ticks ]\n[#F:12 ticks E':12 ticks ]\n[G:12 ticks B:12 ticks G':12 ticks ]\nRest:12 ticks\nG:12 ticks\nRest:12 ticks\nC':18 ticks\nG:6 ticks\nRest:12 ticks\nE:12 ticks\nE:6 ticks\nA:12 ticks\nB:12 ticks\nbB:6 ticks\nA:12 ticks\n(3G:8 ticks E':8 ticks G':8 ticks \nA':12 ticks\nF':6 ticks\nG':6 ticks\nRest:6 ticks\nE':12 ticks\nC':6 ticks\nD':6 ticks\nB:9 ticks\nRest:9 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests fur elise
     */
    @Test
    public void furEliseParserTest() {
        String fileName="fur_elise";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        
        String toMatch = "E':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:24 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\nB:12 ticks\nC':12 ticks\nD':12 ticks\nE':36 ticks\nG:12 ticks\nF':12 ticks\nE':12 ticks\nD':36 ticks\nF:12 ticks\nE':12 ticks\nD':12 ticks\nC':36 ticks\nE:12 ticks\nD':12 ticks\nC':12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\nE':12 ticks\nE'':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\nB:12 ticks\nC':12 ticks\nD':12 ticks\nE':36 ticks\nG:12 ticks\nF':12 ticks\nE':12 ticks\nD':36 ticks\nF:12 ticks\nE':12 ticks\nD':12 ticks\nC':36 ticks\nE:12 ticks\nD':12 ticks\nC':12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\nE':12 ticks\nE'':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\nB:12 ticks\nC':12 ticks\nD':12 ticks\nE':36 ticks\nG:12 ticks\nF':12 ticks\nE':12 ticks\nD':36 ticks\nF:12 ticks\nE':12 ticks\nD':12 ticks\nC':36 ticks\nE:12 ticks\nD':12 ticks\nC':12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\nE':12 ticks\nE'':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\n[E:12 ticks C':12 ticks ]\n[F:12 ticks C':12 ticks ]\n[E:12 ticks G:12 ticks C':12 ticks ]\nC':48 ticks\nF':12 ticks\nE':12 ticks\nE':24 ticks\nD':24 ticks\nbB':12 ticks\nA':12 ticks\nA':12 ticks\nG':12 ticks\nF':12 ticks\nE':12 ticks\nD':12 ticks\nC':12 ticks\nbB:24 ticks\nA:24 ticks\nA:6 ticks\nG:6 ticks\nA:6 ticks\nbB:6 ticks\nC':48 ticks\nD':12 ticks\n#D':12 ticks\nE':36 ticks\nE':12 ticks\nF':12 ticks\nA:12 ticks\nC':48 ticks\nD':12 ticks\nB:12 ticks\nC':6 ticks\nG':6 ticks\nG:6 ticks\nG':6 ticks\nA:6 ticks\nG':6 ticks\nB:6 ticks\nG':6 ticks\nC':6 ticks\nG':6 ticks\nD':6 ticks\nG':6 ticks\nE':6 ticks\nG':6 ticks\nC'':6 ticks\nB':6 ticks\nA':6 ticks\nG':6 ticks\nF':6 ticks\nE':6 ticks\nD':6 ticks\nG':6 ticks\nF':6 ticks\nD':6 ticks\nC':6 ticks\nG':6 ticks\nG:6 ticks\nG':6 ticks\nA:6 ticks\nG':6 ticks\nB:6 ticks\nG':6 ticks\nC':6 ticks\nG':6 ticks\nD':6 ticks\nG':6 ticks\nE':6 ticks\nG':6 ticks\nC'':6 ticks\nB':6 ticks\nA':6 ticks\nG':6 ticks\nF':6 ticks\nE':6 ticks\nD':6 ticks\nG':6 ticks\nF':6 ticks\nD':6 ticks\nE':6 ticks\nF':6 ticks\nE':6 ticks\n#D':6 ticks\nE':6 ticks\nB:6 ticks\nE':6 ticks\n#D':6 ticks\nE':6 ticks\nB:6 ticks\nE':6 ticks\n#D':6 ticks\nE':36 ticks\nB:12 ticks\nE':12 ticks\n#D':12 ticks\nE':36 ticks\nB:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\nB:12 ticks\nC':12 ticks\nD':12 ticks\nE':36 ticks\nG:12 ticks\nF':12 ticks\nE':12 ticks\nD':36 ticks\nF:12 ticks\nE':12 ticks\nD':12 ticks\nC':36 ticks\nE:12 ticks\nD':12 ticks\nC':12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\nE':12 ticks\nE'':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:24 ticks\nRest:24 ticks\n[E:72 ticks G:72 ticks bB:72 ticks #C':72 ticks ]\n[F:48 ticks A:48 ticks D':48 ticks ]\n[#C':12 ticks E':12 ticks ]\n[D':12 ticks F':12 ticks ]\n[#G:48 ticks D':48 ticks F':48 ticks ]\n[#G:24 ticks D':24 ticks F':24 ticks ]\n[A:72 ticks C':72 ticks E':72 ticks ]\n[F:48 ticks D':48 ticks ]\n[E:12 ticks C':12 ticks ]\n[D:12 ticks B:12 ticks ]\n[C:48 ticks #F:48 ticks A:48 ticks ]\n[C:24 ticks A:24 ticks ]\n[C:24 ticks A:24 ticks ]\n[E:24 ticks C':24 ticks ]\n[D:24 ticks B:24 ticks ]\n[C:72 ticks A:72 ticks ]\n[E:72 ticks G:72 ticks bB:72 ticks #C':72 ticks ]\n[F:48 ticks A:48 ticks D':48 ticks ]\n[#C':12 ticks E':12 ticks ]\n[D':12 ticks F':12 ticks ]\n[D':48 ticks F':48 ticks ]\n[D':24 ticks F':24 ticks ]\n[D':72 ticks F':72 ticks ]\n[G:48 ticks bE':48 ticks ]\n[F:12 ticks D':12 ticks ]\n[bE:12 ticks C':12 ticks ]\n[D:48 ticks F:48 ticks bB:48 ticks ]\n[D:24 ticks F:24 ticks A:24 ticks ]\n[D:48 ticks F:48 ticks #G:48 ticks ]\n[D:24 ticks F:24 ticks #G:24 ticks ]\n[C:24 ticks E:24 ticks A:24 ticks ]\nRest:24 ticks\nRest:24 ticks\n[E:24 ticks B:24 ticks ]\nRest:24 ticks\nRest:24 ticks\n(3A,:8 ticks C:8 ticks E:8 ticks \n(3A:8 ticks C':8 ticks E':8 ticks \n(3D':8 ticks C':8 ticks B:8 ticks \n(3A:8 ticks C':8 ticks E':8 ticks \n(3A':8 ticks C'':8 ticks E'':8 ticks \n(3D'':8 ticks C'':8 ticks B':8 ticks \n(3A:8 ticks C':8 ticks E':8 ticks \n(3A':8 ticks C'':8 ticks E'':8 ticks \n(3D'':8 ticks C'':8 ticks B':8 ticks \n(3bB':8 ticks A':8 ticks bA':8 ticks \n(3G':8 ticks bG':8 ticks F':8 ticks \n(3E':8 ticks bE':8 ticks D':8 ticks \n(3bD'':8 ticks C'':8 ticks B':8 ticks \n(3bB':8 ticks A':8 ticks bB':8 ticks \n(3G':8 ticks bG':8 ticks F':8 ticks \nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nC':12 ticks\nB:12 ticks\nA:24 ticks\nRest:12 ticks\nB:12 ticks\nC':12 ticks\nD':12 ticks\nE':36 ticks\nG:12 ticks\nF':12 ticks\nE':12 ticks\nD':36 ticks\nF:12 ticks\nE':12 ticks\nD':12 ticks\nC':36 ticks\nE:12 ticks\nD':12 ticks\nC':12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\nE':12 ticks\nE'':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nE:12 ticks\n#G:12 ticks\nB:12 ticks\nC':24 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\n#D':12 ticks\nE':12 ticks\nB:12 ticks\nD':12 ticks\nC':12 ticks\nA:24 ticks\nRest:12 ticks\nC:12 ticks\nE:12 ticks\nA:12 ticks\nB:24 ticks\nRest:12 ticks\nD:12 ticks\nC':12 ticks\nB:12 ticks\n[C:48 ticks A:48 ticks ]\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());
        
        String toMatch2 = "Rest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nC,:12 ticks\nE,:12 ticks\nC:12 ticks\nRest:12 ticks\nRest:24 ticks\nG,,:12 ticks\nG,:12 ticks\nB,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\nE:12 ticks\nRest:12 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nC,:12 ticks\nE,:12 ticks\nC:12 ticks\nRest:12 ticks\nRest:24 ticks\nG,,:12 ticks\nG,:12 ticks\nB,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\nE:12 ticks\nRest:12 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nC,:12 ticks\nE,:12 ticks\nC:12 ticks\nRest:12 ticks\nRest:24 ticks\nG,,:12 ticks\nG,:12 ticks\nB,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\nE:12 ticks\nRest:12 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\n[bB,:12 ticks C:12 ticks ]\n[A,:12 ticks C:12 ticks ]\n[G,:12 ticks bB,:12 ticks C:12 ticks ]\nF,:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nF,:12 ticks\nbB,:12 ticks\nD:12 ticks\nbB,:12 ticks\nD:12 ticks\nbB,:12 ticks\nF,:12 ticks\nE:12 ticks\n[F,:12 ticks G,:12 ticks bB,:12 ticks ]\nE:12 ticks\n[F,:12 ticks G,:12 ticks bB,:12 ticks ]\nE:12 ticks\nF,:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nF,:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\nE,:12 ticks\nA,:12 ticks\nC:12 ticks\nA,:12 ticks\n[D,:12 ticks D:12 ticks ]\nF,:12 ticks\nG,:12 ticks\nE:12 ticks\nG,:12 ticks\nE:12 ticks\nG,:12 ticks\nF:12 ticks\n[C:24 ticks E:24 ticks ]\nRest:12 ticks\n[F:12 ticks G:12 ticks ]\n[E:12 ticks G:12 ticks ]\n[D:12 ticks F:12 ticks G:12 ticks ]\n[C:24 ticks E:24 ticks G:24 ticks ]\n[F,:24 ticks A,:24 ticks ]\n[F,:24 ticks A,:24 ticks ]\nC:24 ticks\nRest:12 ticks\n[F:12 ticks G:12 ticks ]\n[E:12 ticks G:12 ticks ]\n[D:12 ticks F:12 ticks G:12 ticks ]\n[C:24 ticks E:24 ticks G:24 ticks ]\n[F,:24 ticks A,:24 ticks ]\n[G,:24 ticks B,:24 ticks ]\n[#G,:24 ticks B,:24 ticks ]\nRest:24 ticks\nRest:24 ticks\nRest:72 ticks\nRest:48 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nC,:12 ticks\nE,:12 ticks\nC:12 ticks\nRest:12 ticks\nRest:24 ticks\nG,,:12 ticks\nG,:12 ticks\nB,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\nE:12 ticks\nRest:12 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\n[D,,:12 ticks A,,:12 ticks ]\n[D,,:12 ticks A,,:12 ticks ]\n[D,,:12 ticks A,,:12 ticks ]\n[D,,:12 ticks A,,:12 ticks ]\n[D,,:12 ticks A,,:12 ticks ]\n[D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[#D,,:12 ticks A,,:12 ticks ]\n[E,,:12 ticks A,,:12 ticks ]\n[E,,:12 ticks A,,:12 ticks ]\n[E,,:12 ticks A,,:12 ticks ]\n[E,,:12 ticks A,,:12 ticks ]\n[E,,:12 ticks #G,,:12 ticks ]\n[E,,:12 ticks #G,,:12 ticks ]\n[A,,,:12 ticks A,,:12 ticks ]\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nA,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nbB,,:12 ticks\nB,,:12 ticks\nB,,:12 ticks\nB,,:12 ticks\nB,,:12 ticks\nB,,:12 ticks\nB,,:12 ticks\nC,:24 ticks\nRest:24 ticks\nRest:24 ticks\n[E,:24 ticks #G,:24 ticks ]\nRest:24 ticks\nRest:24 ticks\nA,,,:24 ticks\nRest:24 ticks\n[A,:24 ticks C:24 ticks E:24 ticks ]\n[A,:24 ticks C:24 ticks E:24 ticks ]\nRest:24 ticks\n[A,:24 ticks C:24 ticks E:24 ticks ]\n[A,:24 ticks C:24 ticks E:24 ticks ]\nRest:24 ticks\n[A,:24 ticks C:24 ticks E:24 ticks ]\n[A,:24 ticks C:24 ticks E:24 ticks ]\nRest:24 ticks\nRest:24 ticks\nRest:72 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nC,:12 ticks\nE,:12 ticks\nC:12 ticks\nRest:12 ticks\nRest:24 ticks\nG,,:12 ticks\nG,:12 ticks\nB,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\nE:12 ticks\nRest:12 ticks\nRest:12 ticks\nE:12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:12 ticks\n#D':12 ticks\nE':12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nRest:72 ticks\nA,,:12 ticks\nE,:12 ticks\nA,:12 ticks\nRest:12 ticks\nRest:24 ticks\nE,,:12 ticks\nE,:12 ticks\n#G,:12 ticks\nRest:12 ticks\nRest:24 ticks\n[A,,,:48 ticks A,,:48 ticks ]\n";
        assertEquals(toMatch2, voices.get(1).getFullVoiceString());     

    }
    
    /**
     * Tests invention
     */
    @Test
    public void inventionParserTest() {
        String fileName="invention";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
       
        String toMatch = "Rest:2 ticks\nC:2 ticks\nD:2 ticks\nE:2 ticks\nF:2 ticks\nD:2 ticks\nE:2 ticks\nC:2 ticks\nG:4 ticks\nC':4 ticks\nB:4 ticks\nC':4 ticks\nD':2 ticks\nG:2 ticks\nA:2 ticks\nB:2 ticks\nC':2 ticks\nA:2 ticks\nB:2 ticks\nG:2 ticks\nD':4 ticks\nG':4 ticks\nF':4 ticks\nG':4 ticks\nE':2 ticks\nA':2 ticks\nG':2 ticks\nF':2 ticks\nE':2 ticks\nG':2 ticks\nF':2 ticks\nA':2 ticks\nG':2 ticks\nF':2 ticks\nE':2 ticks\nD':2 ticks\nC':2 ticks\nE':2 ticks\nD':2 ticks\nF':2 ticks\nE':2 ticks\nD':2 ticks\nC':2 ticks\nB:2 ticks\nA:2 ticks\nC':2 ticks\nB:2 ticks\nD':2 ticks\nC':2 ticks\nB:2 ticks\nA:2 ticks\nG:2 ticks\n#F:2 ticks\nA:2 ticks\nG:2 ticks\nB:2 ticks\nA:4 ticks\nD:4 ticks\nC':6 ticks\nD':2 ticks\nB:2 ticks\nA:2 ticks\nG:2 ticks\n#F:2 ticks\nE:2 ticks\nG:2 ticks\n#F:2 ticks\nA:2 ticks\nG:2 ticks\nB:2 ticks\nA:2 ticks\nC':2 ticks\nB:2 ticks\nD':2 ticks\nC':2 ticks\nE':2 ticks\nD':2 ticks\nB:1 ticks\nC':1 ticks\nD':2 ticks\nG':2 ticks\nB:4 ticks\nA:2 ticks\nG:2 ticks\nG:4 ticks\nRest:4 ticks\nRest:8 ticks\nRest:2 ticks\nG:2 ticks\nA:2 ticks\nB:2 ticks\nC':2 ticks\nA:2 ticks\nB:2 ticks\nG:2 ticks\n#F:4 ticks\nRest:12 ticks\nRest:2 ticks\nA:2 ticks\nB:2 ticks\nC':2 ticks\nD':2 ticks\nB:2 ticks\nC':2 ticks\nA:2 ticks\nB:4 ticks\nRest:4 ticks\nRest:8 ticks\nRest:2 ticks\nD':2 ticks\nC':2 ticks\nB:2 ticks\nA:2 ticks\nC':2 ticks\nB:2 ticks\nD':2 ticks\nC':4 ticks\nRest:4 ticks\nRest:8 ticks\nRest:2 ticks\nE':2 ticks\nD':2 ticks\nC':2 ticks\nB:2 ticks\nD':2 ticks\n#C':2 ticks\nD':2 ticks\nD':4 ticks\n#C':4 ticks\nD':4 ticks\nE':4 ticks\nF':4 ticks\nA:4 ticks\nB:4 ticks\n#C':4 ticks\nD':4 ticks\n#F:4 ticks\n#G:4 ticks\nA:4 ticks\nB:4 ticks\nC':4 ticks\nD':8 ticks\nD':2 ticks\nE:2 ticks\n#F:2 ticks\n#G:2 ticks\nA:2 ticks\n#F:2 ticks\n#G:2 ticks\nE:2 ticks\nE':2 ticks\nD':2 ticks\nC':2 ticks\nE':2 ticks\nD':2 ticks\nC':2 ticks\nB:2 ticks\nD':2 ticks\nC':2 ticks\nA':2 ticks\n#G':2 ticks\nB':2 ticks\nA':2 ticks\nE':2 ticks\nF':2 ticks\nD':2 ticks\n#G:2 ticks\nF':2 ticks\nE':2 ticks\nD':2 ticks\nC':4 ticks\nB:2 ticks\nA:2 ticks\nA:2 ticks\nA':2 ticks\nG':2 ticks\nF':2 ticks\nE':2 ticks\nG':2 ticks\nF':2 ticks\nA':2 ticks\nG':16 ticks\nG':2 ticks\nE':2 ticks\nF':2 ticks\nG':2 ticks\nA':2 ticks\nF':2 ticks\nG':2 ticks\nE':2 ticks\nF':16 ticks\nF':2 ticks\nG':2 ticks\nF':2 ticks\nE':2 ticks\nD':2 ticks\nF':2 ticks\nE':2 ticks\nG':2 ticks\nF':16 ticks\nF':2 ticks\nD':2 ticks\nE':2 ticks\nF':2 ticks\nG':2 ticks\nE':2 ticks\nF':2 ticks\nD':2 ticks\nE':16 ticks\nE':2 ticks\nC':2 ticks\nD':2 ticks\nE':2 ticks\nF':2 ticks\nD':2 ticks\nE':2 ticks\nC':2 ticks\nD':2 ticks\nE':2 ticks\nF':2 ticks\nG':2 ticks\nA':2 ticks\nF':2 ticks\nG':2 ticks\nE':2 ticks\nF':2 ticks\nG':2 ticks\nA':2 ticks\nB':2 ticks\nC'':2 ticks\nA':2 ticks\nB':2 ticks\nG':2 ticks\nC'':4 ticks\nG':4 ticks\nE':4 ticks\nD':2 ticks\nC':2 ticks\nC':2 ticks\nbB:2 ticks\nA:2 ticks\nG:2 ticks\nF:2 ticks\nA:2 ticks\nG:2 ticks\nbB:2 ticks\nA:2 ticks\nbB:2 ticks\nC':2 ticks\nE:2 ticks\nD:2 ticks\nC':2 ticks\nF:2 ticks\nbB:2 ticks\n[C':32 ticks G:32 ticks E:32 ticks ]\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());
       
        String toMatch2 = "Rest:16 ticks\nRest:2 ticks\nC,:2 ticks\nD,:2 ticks\nE,:2 ticks\nF,:2 ticks\nD,:2 ticks\nE,:2 ticks\nC,:2 ticks\nG,:4 ticks\nG,,:4 ticks\nRest:8 ticks\nRest:2 ticks\nG,:2 ticks\nA,:2 ticks\nB,:2 ticks\nC:2 ticks\nA,:2 ticks\nB,:2 ticks\nG,:2 ticks\nC:4 ticks\nB,:4 ticks\nC:4 ticks\nD:4 ticks\nE:4 ticks\nG,:4 ticks\nA,:4 ticks\nB,:4 ticks\nC:4 ticks\nE,:4 ticks\n#F,:4 ticks\nG,:4 ticks\nA,:4 ticks\nB,:4 ticks\nC:8 ticks\nC:2 ticks\nD,:2 ticks\nE,:2 ticks\n#F,:2 ticks\nG,:2 ticks\nE,:2 ticks\n#F,:2 ticks\nD,:2 ticks\nG,:4 ticks\nB,,:4 ticks\nC,:4 ticks\nD,:4 ticks\nE,:4 ticks\n#F,:4 ticks\nG,:4 ticks\nE,:4 ticks\nB,,:4 ticks\nC,:4 ticks\nD,:4 ticks\nD,,:4 ticks\nRest:2 ticks\nG,,:2 ticks\nA,,:2 ticks\nB,,:2 ticks\nC,:2 ticks\nA,,:2 ticks\nB,,:2 ticks\nG,,:2 ticks\nD,:4 ticks\nG,:4 ticks\n#F,:4 ticks\nG,:4 ticks\nA,:2 ticks\nD,:2 ticks\nE,:2 ticks\n#F,:2 ticks\nG,:2 ticks\nE,:2 ticks\n#F,:2 ticks\nD,:2 ticks\nA,:4 ticks\nD:4 ticks\nC:4 ticks\nD:4 ticks\nG,:2 ticks\nG:2 ticks\nF:2 ticks\nE:2 ticks\nD:2 ticks\nF:2 ticks\nE:2 ticks\nG:2 ticks\nF:4 ticks\nE:4 ticks\nF:4 ticks\nD:4 ticks\nE:2 ticks\nA:2 ticks\nG:2 ticks\nF:2 ticks\nE:2 ticks\nG:2 ticks\nF:2 ticks\nA:2 ticks\nG:4 ticks\nF:4 ticks\nG:4 ticks\nE:4 ticks\nF:2 ticks\nbB:2 ticks\nA:2 ticks\nG:2 ticks\nF:2 ticks\nA:2 ticks\nG:2 ticks\nbB:2 ticks\nA:2 ticks\nG:2 ticks\nF:2 ticks\nE:2 ticks\nD:2 ticks\nF:2 ticks\nE:2 ticks\nG:2 ticks\nF:2 ticks\nE:2 ticks\nD:2 ticks\nC:2 ticks\nB,:2 ticks\nD:2 ticks\nC:2 ticks\nE:2 ticks\nD:2 ticks\nC:2 ticks\nB,:2 ticks\nA,:2 ticks\n#G,:2 ticks\nB,:2 ticks\nA,:2 ticks\nC:2 ticks\nB,:4 ticks\nE,:4 ticks\nD:6 ticks\nE:2 ticks\nC:2 ticks\nB,:2 ticks\nA,:2 ticks\nG,:2 ticks\n#F,:2 ticks\nA,:2 ticks\n#G,:2 ticks\nB,:2 ticks\nA,:2 ticks\nC:2 ticks\nB,:2 ticks\nD:2 ticks\nC:2 ticks\nE:2 ticks\nD:2 ticks\nF:2 ticks\nE:4 ticks\nA,:4 ticks\nE:4 ticks\nE,:4 ticks\nA,:4 ticks\nA,,:4 ticks\nRest:8 ticks\nRest:2 ticks\nE:2 ticks\nD:2 ticks\nC:2 ticks\nB,:2 ticks\nD:2 ticks\n#C:2 ticks\nE:2 ticks\nD:16 ticks\nD:2 ticks\nA,:2 ticks\nB,:2 ticks\nC:2 ticks\nD:2 ticks\nB,:2 ticks\nC:2 ticks\nA,:2 ticks\nB,:16 ticks\nB,:2 ticks\nD:2 ticks\nC:2 ticks\nB,:2 ticks\nA,:2 ticks\nC:2 ticks\nB,:2 ticks\nD:2 ticks\nC:16 ticks\nC:2 ticks\nG,:2 ticks\nA,:2 ticks\nbB,:2 ticks\nC:2 ticks\nA,:2 ticks\n#A,:2 ticks\nG,:2 ticks\nA,:4 ticks\nbB,:4 ticks\nA,:4 ticks\nG,:4 ticks\nF,:4 ticks\nD:4 ticks\nC:4 ticks\nbB,:4 ticks\nA,:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nE:2 ticks\nD,:2 ticks\nE,:2 ticks\nF,:2 ticks\nG,:2 ticks\nE,:2 ticks\nF,:2 ticks\nD,:2 ticks\nE,:4 ticks\nC,:4 ticks\nD,:4 ticks\nE,:4 ticks\nF,:2 ticks\nD,:2 ticks\nE,:2 ticks\nF,:2 ticks\nG,:4 ticks\nG,,:4 ticks\n[C,:32 ticks C,,:32 ticks ]\n";
        assertEquals(toMatch2, voices.get(1).getFullVoiceString()); 
    }
    
    /**
     * Tests little night music
     */
    @Test
    public void littleNightMusicParserTest() {
        String fileName="little_night_music";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "[D:24 ticks B:24 ticks G':24 ticks ]\nRest:12 ticks\nD':12 ticks\nG':24 ticks\nRest:12 ticks\nD':12 ticks\nG':12 ticks\nD':12 ticks\nG':12 ticks\nB':12 ticks\nD'':24 ticks\nRest:24 ticks\nC'':24 ticks\nRest:12 ticks\nA':12 ticks\nC'':24 ticks\nRest:12 ticks\nA':12 ticks\nC'':12 ticks\nA':12 ticks\n#F':12 ticks\nA':12 ticks\nD':24 ticks\nRest:24 ticks\n[D:12 ticks B:12 ticks G':12 ticks ]\nRest:12 ticks\nG':36 ticks\nB':12 ticks\nA':12 ticks\nG':12 ticks\nG':12 ticks\n#F':12 ticks\n#F':36 ticks\nA':12 ticks\nC'':12 ticks\n#F':12 ticks\nA':12 ticks\nG':12 ticks\nG':36 ticks\nB':12 ticks\nA':12 ticks\nG':12 ticks\nG':12 ticks\n#F':12 ticks\n#F':36 ticks\nA':12 ticks\nC'':12 ticks\n#F':12 ticks\nG':12 ticks\nG':12 ticks\n#F':12 ticks\nE':6 ticks\n#F':6 ticks\nG':12 ticks\nG':12 ticks\nA':12 ticks\nG':6 ticks\nA':6 ticks\nB':12 ticks\nB':12 ticks\nC'':12 ticks\nB':6 ticks\nC'':6 ticks\nD'':24 ticks\nRest:24 ticks\nD':48 ticks\nE':48 ticks\nC':24 ticks\nC':24 ticks\nB:24 ticks\nB:24 ticks\nA:24 ticks\nA:24 ticks\nG:12 ticks\n#F:12 ticks\nE:12 ticks\n#F:12 ticks\nG:12 ticks\nRest:12 ticks\nA:12 ticks\nRest:12 ticks\nB:12 ticks\nRest:12 ticks\nRest:24 ticks\nD':48 ticks\nE':48 ticks\nD':12 ticks\nC':12 ticks\nC':12 ticks\nC':12 ticks\nC':12 ticks\nB:12 ticks\nB:12 ticks\nB:12 ticks\nB:12 ticks\nA:12 ticks\nA:12 ticks\nA:12 ticks\nG:12 ticks\n#F:12 ticks\nE:12 ticks\n#F:12 ticks\n[G:48 ticks G,:48 ticks ]\n[G:12 ticks G,:12 ticks ]\nG:4 ticks\n#F:4 ticks\nG:4 ticks\nA:12 ticks\n#F:12 ticks\nB:48 ticks\nB:12 ticks\nB:4 ticks\nA:4 ticks\nB:4 ticks\nC':12 ticks\nA:12 ticks\nD':48 ticks\nE':24 ticks\n#F':24 ticks\nG':24 ticks\nA':24 ticks\nB':24 ticks\n#C'':24 ticks\nD'':36 ticks\nA':12 ticks\n#C'':18 ticks\nA':6 ticks\n#C'':18 ticks\nA':6 ticks\nD'':36 ticks\nA':12 ticks\n#C'':18 ticks\nA':6 ticks\n#C'':18 ticks\nA':6 ticks\nD'':12 ticks\n[D'':24 ticks #F':24 ticks ]\n[D'':24 ticks #F':24 ticks ]\n[D'':24 ticks #F':24 ticks ]\n[D'':12 ticks #F':12 ticks ]\nD'':12 ticks\n[D'':24 ticks E':24 ticks ]\n[D'':24 ticks E':24 ticks ]\n[D'':24 ticks E':24 ticks ]\n[D'':12 ticks E':12 ticks ]\n[#C'':12 ticks E':12 ticks ]\nA':12 ticks\nD'':12 ticks\nA':12 ticks\n#C'':12 ticks\nA':12 ticks\nD'':12 ticks\nA':12 ticks\n#C'':12 ticks\nA:12 ticks\nA:12 ticks\nA:12 ticks\nA:24 ticks\nRest:24 ticks\nA':36 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':12 ticks\nRest:12 ticks\nB':12 ticks\nRest:12 ticks\nG':12 ticks\nRest:12 ticks\nE':12 ticks\nRest:12 ticks\nA':12 ticks\nRest:12 ticks\nRest:24 ticks\n#F':36 ticks\nE':4 ticks\nD':4 ticks\n#C':4 ticks\nB:12 ticks\nRest:12 ticks\nG':12 ticks\nRest:12 ticks\n#F':48 ticks\nE':24 ticks\nRest:24 ticks\nRest:12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nB':12 ticks\n#C'':12 ticks\n#C'':12 ticks\nD'':12 ticks\nRest:12 ticks\nB':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\n#C':12 ticks\nD':24 ticks\nRest:12 ticks\nA':12 ticks\nD'':12 ticks\n#C'':12 ticks\nB':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nD'':12 ticks\n#C'':12 ticks\nB':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:24 ticks\n[B':36 ticks B:36 ticks ]\nA':4 ticks\nG':4 ticks\n#F':4 ticks\nG':24 ticks\nRest:24 ticks\n[A':36 ticks A:36 ticks ]\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':24 ticks\nRest:24 ticks\nB':12 ticks\n#C'':6 ticks\nD'':6 ticks\n#C'':12 ticks\nB':12 ticks\nB':12 ticks\nA':12 ticks\n#F':12 ticks\nA':12 ticks\nA':12 ticks\nG':12 ticks\n#F':12 ticks\nE':12 ticks\nD':24 ticks\nRest:12 ticks\nA':12 ticks\nD'':12 ticks\n#C'':12 ticks\nB':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nD'':12 ticks\n#C'':12 ticks\nB':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nA':12 ticks\nB':12 ticks\nA':12 ticks\nRest:24 ticks\n[B':36 ticks B:36 ticks ]\nA':4 ticks\nG':4 ticks\n#F':4 ticks\nG':24 ticks\nRest:24 ticks\n[A':36 ticks A:36 ticks ]\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':24 ticks\nRest:24 ticks\nB':12 ticks\n#C'':6 ticks\nD'':6 ticks\n#C'':12 ticks\nB':12 ticks\nB':12 ticks\nA':12 ticks\n#F':12 ticks\nA':12 ticks\nA':12 ticks\nG':12 ticks\n#F':12 ticks\nE':12 ticks\nD':12 ticks\nA:12 ticks\nB:12 ticks\n#C':12 ticks\nD':12 ticks\nD':12 ticks\nE':12 ticks\nD':6 ticks\nE':6 ticks\n#F':12 ticks\n#C':12 ticks\nD':12 ticks\nE':12 ticks\n#F':12 ticks\n#F':12 ticks\nG':12 ticks\n#F':6 ticks\nG':6 ticks\nA':12 ticks\nA':12 ticks\n#A':12 ticks\n#G':6 ticks\n#A':6 ticks\nB':24 ticks\nRest:24 ticks\nB:36 ticks\nE':12 ticks\nD':12 ticks\n#C':12 ticks\nB:12 ticks\nA:12 ticks\nD':12 ticks\nRest:12 ticks\n#F':12 ticks\nRest:12 ticks\nD':12 ticks\nRest:12 ticks\nRest:24 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests paddy
     */
    @Test
    public void paddyParserTest() {
        String fileName="paddy";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "D':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\nD':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\nD':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\nD':4 ticks\n#F':4 ticks\n#F':4 ticks\n#C':4 ticks\nE':4 ticks\nE':4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#C':4 ticks\nB:4 ticks\nA:12 ticks\nB:12 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F:4 ticks\nA:4 ticks\nB:8 ticks\n#C':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#C':4 ticks\nB:4 ticks\nA:12 ticks\nB:12 ticks\nE':4 ticks\n#F':4 ticks\nE':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#C':4 ticks\nB:4 ticks\nA:12 ticks\nB:12 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F:4 ticks\nA:4 ticks\nB:8 ticks\n#C':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#C':4 ticks\nB:4 ticks\nA:12 ticks\nB:12 ticks\nE':4 ticks\n#F':4 ticks\nE':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n#F':4 ticks\nA:4 ticks\nA:4 ticks\nE':4 ticks\nA:4 ticks\nA:4 ticks\nD':4 ticks\nE':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\n#F':4 ticks\nA':4 ticks\n#F':4 ticks\nG':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\n#F':4 ticks\nE':4 ticks\nD':4 ticks\nB:4 ticks\nA:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests prelude
     */
    @Test
    public void preludeParserTest() {
        String fileName="prelude";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        
        String toMatch = "Rest:8 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nRest:8 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nRest:8 ticks\nA:4 ticks\nD':4 ticks\nF':4 ticks\nA:4 ticks\nD':4 ticks\nF':4 ticks\nRest:8 ticks\nA:4 ticks\nD':4 ticks\nF':4 ticks\nA:4 ticks\nD':4 ticks\nF':4 ticks\nRest:8 ticks\nG:4 ticks\nD':4 ticks\nF':4 ticks\nG:4 ticks\nD':4 ticks\nF':4 ticks\nRest:8 ticks\nG:4 ticks\nD':4 ticks\nF':4 ticks\nG:4 ticks\nD':4 ticks\nF':4 ticks\nRest:8 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nRest:8 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nG:4 ticks\nC':4 ticks\nE':4 ticks\nRest:8 ticks\nA:4 ticks\nE':4 ticks\nA':4 ticks\nA:4 ticks\nE':4 ticks\nA':4 ticks\nRest:8 ticks\nA:4 ticks\nE':4 ticks\nA':4 ticks\nA:4 ticks\nE':4 ticks\nA':4 ticks\nRest:8 ticks\n#F:4 ticks\nA:4 ticks\nD':4 ticks\n#F:4 ticks\nA:4 ticks\nD':4 ticks\nRest:8 ticks\n#F:4 ticks\nA:4 ticks\nD':4 ticks\n#F:4 ticks\nA:4 ticks\nD':4 ticks\nRest:8 ticks\nG:4 ticks\nD':4 ticks\nG':4 ticks\nG:4 ticks\nD':4 ticks\nG':4 ticks\nRest:8 ticks\nG:4 ticks\nD':4 ticks\nG':4 ticks\nG:4 ticks\nD':4 ticks\nG':4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nE:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nD:4 ticks\n#F:4 ticks\nC':4 ticks\nD:4 ticks\n#F:4 ticks\nC':4 ticks\nRest:8 ticks\nD:4 ticks\n#F:4 ticks\nC':4 ticks\nD:4 ticks\n#F:4 ticks\nC':4 ticks\nRest:8 ticks\nD:4 ticks\nG:4 ticks\nB:4 ticks\nD:4 ticks\nG:4 ticks\nB:4 ticks\nRest:8 ticks\nD:4 ticks\nG:4 ticks\nB:4 ticks\nD:4 ticks\nG:4 ticks\nB:4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\n#C':4 ticks\nE:4 ticks\nG:4 ticks\n#C':4 ticks\nRest:8 ticks\nE:4 ticks\nG:4 ticks\n#C':4 ticks\nE:4 ticks\nG:4 ticks\n#C':4 ticks\nRest:8 ticks\nD:4 ticks\nA:4 ticks\nD':4 ticks\nD:4 ticks\nA:4 ticks\nD':4 ticks\nRest:8 ticks\nD:4 ticks\nA:4 ticks\nD':4 ticks\nD:4 ticks\nA:4 ticks\nD':4 ticks\nRest:8 ticks\nD:4 ticks\nF:4 ticks\nB:4 ticks\nD:4 ticks\nF:4 ticks\nB:4 ticks\nRest:8 ticks\nD:4 ticks\nF:4 ticks\nB:4 ticks\nD:4 ticks\nF:4 ticks\nB:4 ticks\nRest:8 ticks\nC:4 ticks\nG:4 ticks\nC':4 ticks\nC:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nC:4 ticks\nG:4 ticks\nC':4 ticks\nC:4 ticks\nG:4 ticks\nC':4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nbB,:4 ticks\nC:4 ticks\nE:4 ticks\nbB,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nbB,:4 ticks\nC:4 ticks\nE:4 ticks\nbB,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nE:4 ticks\nA,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nE:4 ticks\nA,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nbE:4 ticks\nA,:4 ticks\nC:4 ticks\nbE:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\nbE:4 ticks\nA,:4 ticks\nC:4 ticks\nbE:4 ticks\nRest:8 ticks\nB,:4 ticks\nC:4 ticks\nD:4 ticks\nB,:4 ticks\nC:4 ticks\nD:4 ticks\nRest:8 ticks\nB,:4 ticks\nC:4 ticks\nD:4 ticks\nB,:4 ticks\nC:4 ticks\nD:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nD:4 ticks\nG,:4 ticks\nB,:4 ticks\nD:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nD:4 ticks\nG,:4 ticks\nB,:4 ticks\nD:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nG,:4 ticks\nC:4 ticks\nE:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\n#F:4 ticks\nA,:4 ticks\nC:4 ticks\n#F:4 ticks\nRest:8 ticks\nA,:4 ticks\nC:4 ticks\n#F:4 ticks\nA,:4 ticks\nC:4 ticks\n#F:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nG:4 ticks\nG,:4 ticks\nC:4 ticks\nG:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nG:4 ticks\nG,:4 ticks\nC:4 ticks\nG:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nG,:4 ticks\nC:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nG,:4 ticks\nB,:4 ticks\nF:4 ticks\nRest:8 ticks\nG,:4 ticks\nbB,:4 ticks\nE:4 ticks\nG,:4 ticks\nbB,:4 ticks\nE:4 ticks\nRest:8 ticks\nG,:4 ticks\nbB,:4 ticks\nE:4 ticks\nG,:4 ticks\nbB,:4 ticks\nE:4 ticks\nRest:8 ticks\nF,:4 ticks\nA,:4 ticks\nC:4 ticks\nF:4 ticks\nC:4 ticks\nA,:4 ticks\nC:4 ticks\nA,:4 ticks\nF,:4 ticks\nA,:4 ticks\nF,:4 ticks\nD,:4 ticks\nF,:4 ticks\nD,:4 ticks\nRest:8 ticks\nG:4 ticks\nB:4 ticks\nD':4 ticks\nF':4 ticks\nD':4 ticks\nB:4 ticks\nD':4 ticks\nB:4 ticks\nG:4 ticks\nB:4 ticks\nD:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\n[E:64 ticks G:64 ticks C':64 ticks ]\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());
        
        String toMatch2 = "Rest:4 ticks\nE:28 ticks\nRest:4 ticks\nE:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nE:28 ticks\nRest:4 ticks\nE:28 ticks\nRest:4 ticks\nE:28 ticks\nRest:4 ticks\nE:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nD:28 ticks\nRest:4 ticks\nC:28 ticks\nRest:4 ticks\nC:28 ticks\nRest:4 ticks\nC:28 ticks\nRest:4 ticks\nC:28 ticks\nRest:4 ticks\nA,:28 ticks\nRest:4 ticks\nA,:28 ticks\nRest:4 ticks\nB,:28 ticks\nRest:4 ticks\nB,:28 ticks\nRest:4 ticks\nbB,:28 ticks\nRest:4 ticks\nbB,:28 ticks\nRest:4 ticks\nA,:28 ticks\nRest:4 ticks\nA,:28 ticks\nRest:4 ticks\nbA,:28 ticks\nRest:4 ticks\nbA,:28 ticks\nRest:4 ticks\nG,:28 ticks\nRest:4 ticks\nG,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nG,:28 ticks\nRest:4 ticks\nG,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nC,:28 ticks\nRest:4 ticks\nC,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nF,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nbE,:28 ticks\nRest:4 ticks\nbE,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nE,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nD,:28 ticks\nRest:4 ticks\nC,:28 ticks\nRest:4 ticks\nC,:28 ticks\nRest:4 ticks\nC,:60 ticks\nRest:4 ticks\nB,,:60 ticks\nC,:64 ticks\n";
        assertEquals(toMatch2, voices.get(1).getFullVoiceString()); 
        

        String toMatch3 = "C:32 ticks\nC:32 ticks\nC:32 ticks\nC:32 ticks\nB,:32 ticks\nB,:32 ticks\nC:32 ticks\nC:32 ticks\nC:32 ticks\nC:32 ticks\nC:32 ticks\nC:32 ticks\nB,:32 ticks\nB,:32 ticks\nB,:32 ticks\nB,:32 ticks\nA,:32 ticks\nA,:32 ticks\nD,:32 ticks\nD,:32 ticks\nG,:32 ticks\nG,:32 ticks\nG,:32 ticks\nG,:32 ticks\nF,:32 ticks\nF,:32 ticks\nF,:32 ticks\nF,:32 ticks\nE,:32 ticks\nE,:32 ticks\nE,:32 ticks\nE,:32 ticks\nD,:32 ticks\nD,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nC,:32 ticks\nC,:32 ticks\nC,:32 ticks\nC,:32 ticks\nF,,:32 ticks\nF,,:32 ticks\n#F,,:32 ticks\n#F,,:32 ticks\nbA,,:32 ticks\nbA,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nG,,:32 ticks\nC,,:32 ticks\nC,,:32 ticks\nC,,:64 ticks\nC,,:64 ticks\nC,,:64 ticks\n";
        assertEquals(toMatch3, voices.get(2).getFullVoiceString()); 
    }
    
    /**
     * Tests scale
     */
    @Test
    public void scaleParserTest() {
        String fileName="scale";
        String path = "sample_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nD:4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nA:4 ticks\nB:4 ticks\nC':4 ticks\nC':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    
    //Other tests
    
    /**
     * Tests valid_onevoice.abc
     */
    @Test
    public void validOneVoiceTest() {
        //Checks for notes, accidentals
        String fileName="valid_onevoice";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:12 ticks\nbD:12 ticks\nbE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\nC':12 ticks\n#C:12 ticks\nbD':12 ticks\nE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\n#C:12 ticks\nC:12 ticks\nbD:12 ticks\nbE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\nC':12 ticks\nC:12 ticks\n#C:12 ticks\nC:12 ticks\nbC:12 ticks\nC:12 ticks\nbD:12 ticks\n#D:12 ticks\nD:12 ticks\nbD:12 ticks\nD:12 ticks\nbE:12 ticks\n#E:12 ticks\nE:12 ticks\nbE:12 ticks\nE:12 ticks\nF:12 ticks\n#F:12 ticks\nF:12 ticks\nbF:12 ticks\nF:12 ticks\nG:12 ticks\n#G:12 ticks\nG:12 ticks\nbG:12 ticks\nG:12 ticks\nbA:12 ticks\n#A:12 ticks\nA:12 ticks\nbA:12 ticks\nA:12 ticks\nbB:12 ticks\n#B:12 ticks\nB:12 ticks\nbB:12 ticks\nB:12 ticks\nC':12 ticks\n#C':12 ticks\nC':12 ticks\nbC':12 ticks\nC':12 ticks\nC:12 ticks\nbD:6 ticks\nbE:12 ticks\nbF:6 ticks\nG:12 ticks\nbA:3 ticks\nbA:3 ticks\nbB:12 ticks\n#C':6 ticks\nC'':12 ticks\nbD'':6 ticks\nbE'':24 ticks\nF'':12 ticks\nG'':6 ticks\nbA'':12 ticks\nbB'':3 ticks\n#C''':12 ticks\nC:12 ticks\nC:12 ticks\nbB,:24 ticks\nbA,:12 ticks\nbG,:6 ticks\nbE,:12 ticks\n#F,:6 ticks\nbD,:12 ticks\nC,:6 ticks\n[C':12 ticks C:12 ticks ]\nRest:12 ticks\n[bE':12 ticks bE:12 ticks ]\nRest:12 ticks\n[G':12 ticks G:12 ticks ]\nRest:12 ticks\n[bE':12 ticks bE:12 ticks ]\nRest:12 ticks\n[C':12 ticks C:12 ticks ]\n(2bA:18 ticks bB:18 ticks \n(2bA:18 ticks bB:18 ticks \n(2bA:18 ticks bB:18 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \nC:12 ticks\nbD:12 ticks\nbE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\nC':12 ticks\n#C:12 ticks\nbD':12 ticks\nE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\n#C:12 ticks\nC:12 ticks\nbD:12 ticks\nbE:12 ticks\nF:12 ticks\nG:12 ticks\nbA:12 ticks\nbB:12 ticks\nC':12 ticks\nC:12 ticks\n#C:12 ticks\nC:12 ticks\nbC:12 ticks\nC:12 ticks\nbD:12 ticks\n#D:12 ticks\nD:12 ticks\nbD:12 ticks\nD:12 ticks\nbE:12 ticks\n#E:12 ticks\nE:12 ticks\nbE:12 ticks\nE:12 ticks\nF:12 ticks\n#F:12 ticks\nF:12 ticks\nbF:12 ticks\nF:12 ticks\nG:12 ticks\n#G:12 ticks\nG:12 ticks\nbG:12 ticks\nG:12 ticks\nbA:12 ticks\n#A:12 ticks\nA:12 ticks\nbA:12 ticks\nA:12 ticks\nbB:12 ticks\n#B:12 ticks\nB:12 ticks\nbB:12 ticks\nB:12 ticks\nC':12 ticks\n#C':12 ticks\nC':12 ticks\nbC':12 ticks\nC':12 ticks\nC:12 ticks\nbD:6 ticks\nbE:12 ticks\nbF:6 ticks\nG:12 ticks\nbA:3 ticks\nbA:3 ticks\nbB:12 ticks\n#C':6 ticks\nC'':12 ticks\nbD'':6 ticks\nbE'':24 ticks\nF'':12 ticks\nG'':6 ticks\nbA'':12 ticks\nbB'':3 ticks\n#C''':12 ticks\nC:12 ticks\nC:12 ticks\nbB,:24 ticks\nbA,:12 ticks\nbG,:6 ticks\nbE,:12 ticks\n#F,:6 ticks\nbD,:12 ticks\nC,:6 ticks\n[C':12 ticks C:12 ticks ]\nRest:12 ticks\n[bE':12 ticks bE:12 ticks ]\nRest:12 ticks\n[G':12 ticks G:12 ticks ]\nRest:12 ticks\n[bE':12 ticks bE:12 ticks ]\nRest:12 ticks\n[C':12 ticks C:12 ticks ]\n(2bA:18 ticks bB:18 ticks \n(2bA:18 ticks bB:18 ticks \n(2bA:18 ticks bB:18 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:8 ticks bB:8 ticks C:8 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(3bA:4 ticks bB:4 ticks C:4 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \n(4bA:9 ticks bB:9 ticks C:9 ticks bD:9 ticks \n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    //Octave Test Classes
    @Test
    public void octave_down1_lower() {
        //also checks basic notes
        String fileName="octave_down1_lower";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "B':4 ticks\nA':4 ticks\nG':4 ticks\nF':4 ticks\nE':4 ticks\nD':4 ticks\nC':4 ticks\nC':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_down4_lower() {
        String fileName="octave_down4_lower";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\nC:4 ticks\nB,:4 ticks\nA,:4 ticks\nG,:4 ticks\nF,:4 ticks\nE,:4 ticks\nD,:4 ticks\nC,:4 ticks\nC,:4 ticks\nB,,:4 ticks\nA,,:4 ticks\nG,,:4 ticks\nF,,:4 ticks\nE,,:4 ticks\nD,,:4 ticks\nC,,,:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_down1_upper() {
        String fileName="octave_down1_upper";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "B:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\nC:4 ticks\nB,:4 ticks\nA,:4 ticks\nG,:4 ticks\nF,:4 ticks\nE,:4 ticks\nD,:4 ticks\nC:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_down4_upper() {
        String fileName="octave_down4_upper";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nB,:4 ticks\nA,:4 ticks\nG,:4 ticks\nF,:4 ticks\nE,:4 ticks\nD,:4 ticks\nC,:4 ticks\nC,:4 ticks\nB,,:4 ticks\nA,,:4 ticks\nG,,:4 ticks\nF,,:4 ticks\nE,,:4 ticks\nD,,:4 ticks\nC,,:4 ticks\nC,,:4 ticks\nB,,,:4 ticks\nA,,,:4 ticks\nG,,,:4 ticks\nF,,,:4 ticks\nE,,,:4 ticks\nD,,,:4 ticks\nC,,,:4 ticks\nC,,,:4 ticks\nB,,,,:4 ticks\nA,,,,:4 ticks\nG,,,,:4 ticks\nF,,,,:4 ticks\nE,,,,:4 ticks\nD,,,,:4 ticks\nC,,,,:4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_up1_lower() {
        String fileName="octave_up1_lower";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C':4 ticks\nD':4 ticks\nE':4 ticks\nF':4 ticks\nG':4 ticks\nA':4 ticks\nB':4 ticks\nC'':4 ticks\nC'':4 ticks\nD'':4 ticks\nE'':4 ticks\nF'':4 ticks\nG'':4 ticks\nA'':4 ticks\nB'':4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_up4_lower() {
        String fileName="octave_up4_lower";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C':4 ticks\nD':4 ticks\nE':4 ticks\nF':4 ticks\nG':4 ticks\nA':4 ticks\nB':4 ticks\nC'':4 ticks\nC'':4 ticks\nD'':4 ticks\nE'':4 ticks\nF'':4 ticks\nG'':4 ticks\nA'':4 ticks\nB'':4 ticks\nC''':4 ticks\nC''':4 ticks\nD''':4 ticks\nE''':4 ticks\nF''':4 ticks\nG''':4 ticks\nA''':4 ticks\nB''':4 ticks\nC'''':4 ticks\nC'''':4 ticks\nD'''':4 ticks\nE'''':4 ticks\nF'''':4 ticks\nG'''':4 ticks\nA'''':4 ticks\nB'''':4 ticks\nC''''':4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_up1_upper() {
        String fileName="octave_up1_upper";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nD:4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nA:4 ticks\nB:4 ticks\nC':4 ticks\nC':4 ticks\nD':4 ticks\nE':4 ticks\nF':4 ticks\nG':4 ticks\nA':4 ticks\nB':4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    @Test
    public void octave_up4_upper() {

        String fileName="octave_up4_upper";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nD:4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nA:4 ticks\nB:4 ticks\nC':4 ticks\nC':4 ticks\nD':4 ticks\nE':4 ticks\nF':4 ticks\nG':4 ticks\nA':4 ticks\nB':4 ticks\nC'':4 ticks\nC'':4 ticks\nD'':4 ticks\nE'':4 ticks\nF'':4 ticks\nG'':4 ticks\nA'':4 ticks\nB'':4 ticks\nC''':4 ticks\nC''':4 ticks\nD''':4 ticks\nE''':4 ticks\nF''':4 ticks\nG''':4 ticks\nA''':4 ticks\nB''':4 ticks\nC'''':4 ticks\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }

    //Invalid pieces
    
    /**
     * Tests invalid_restaccidental.abc
     * 
     * @throws IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void invalidRestAccidentalTest() {
        //Checks for notes, accidentals
        String fileName="invalid_restaccidental";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\n#C:4 ticks\nbD':4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\n#C:4 ticks\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\nC:4 ticks\nbD:2 ticks\nbE:4 ticks\nbF:2 ticks\nG:4 ticks\nbA:1 ticks\nbA:1 ticks\nbB:4 ticks\n#C':2 ticks\nC'':4 ticks\nbD'':2 ticks\nbE'':8 ticks\nF'':4 ticks\nG'':2 ticks\nbA'':4 ticks\nbB'':1 ticks\n#C''':4 ticks\nC:4 ticks\nC:4 ticks\nbB,:8 ticks\nbA,:4 ticks\nbG,:2 ticks\nbE,:4 ticks\n#F,:2 ticks\nbD,:4 ticks\nC,:2 ticks\n[C':4 ticks C:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[G':4 ticks G:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[C':4 ticks C:4 ticks ]\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\n#C:4 ticks\nbD':4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\n#C:4 ticks\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\nC:4 ticks\nbD:2 ticks\nbE:4 ticks\nbF:2 ticks\nG:4 ticks\nbA:1 ticks\nbA:1 ticks\nbB:4 ticks\n#C':2 ticks\nC'':4 ticks\nbD'':2 ticks\nbE'':8 ticks\nF'':4 ticks\nG'':2 ticks\nbA'':4 ticks\nbB'':1 ticks\n#C''':4 ticks\nC:4 ticks\nC:4 ticks\nbB,:8 ticks\nbA,:4 ticks\nbG,:2 ticks\nbE,:4 ticks\n#F,:2 ticks\nbD,:4 ticks\nC,:2 ticks\n[C':4 ticks C:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[G':4 ticks G:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[C':4 ticks C:4 ticks ]\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests invalid_restoctave.abc
     * 
     * @throws RuntimeException
     */
    @Test (expected = RuntimeException.class)
    public void invalidRestOctaveTest() {
        //Checks for notes, accidentals
        String fileName="invalid_restoctave";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\n#C:4 ticks\nbD':4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\n#C:4 ticks\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\nC:4 ticks\nbD:2 ticks\nbE:4 ticks\nbF:2 ticks\nG:4 ticks\nbA:1 ticks\nbA:1 ticks\nbB:4 ticks\n#C':2 ticks\nC'':4 ticks\nbD'':2 ticks\nbE'':8 ticks\nF'':4 ticks\nG'':2 ticks\nbA'':4 ticks\nbB'':1 ticks\n#C''':4 ticks\nC:4 ticks\nC:4 ticks\nbB,:8 ticks\nbA,:4 ticks\nbG,:2 ticks\nbE,:4 ticks\n#F,:2 ticks\nbD,:4 ticks\nC,:2 ticks\n[C':4 ticks C:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[G':4 ticks G:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[C':4 ticks C:4 ticks ]\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\n#C:4 ticks\nbD':4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\n#C:4 ticks\nC:4 ticks\nbD:4 ticks\nbE:4 ticks\nF:4 ticks\nG:4 ticks\nbA:4 ticks\nbB:4 ticks\nC':4 ticks\nC:4 ticks\nbD:2 ticks\nbE:4 ticks\nbF:2 ticks\nG:4 ticks\nbA:1 ticks\nbA:1 ticks\nbB:4 ticks\n#C':2 ticks\nC'':4 ticks\nbD'':2 ticks\nbE'':8 ticks\nF'':4 ticks\nG'':2 ticks\nbA'':4 ticks\nbB'':1 ticks\n#C''':4 ticks\nC:4 ticks\nC:4 ticks\nbB,:8 ticks\nbA,:4 ticks\nbG,:2 ticks\nbE,:4 ticks\n#F,:2 ticks\nbD,:4 ticks\nC,:2 ticks\n[C':4 ticks C:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[G':4 ticks G:4 ticks ]\nRest:4 ticks\n[bE':4 ticks bE:4 ticks ]\nRest:4 ticks\n[C':4 ticks C:4 ticks ]\n";
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
    
    /**
     * Tests unequalrepeats.abc
     * Should run, just won't repeat.
     * 
     */
    @Test (expected = RuntimeException.class)
    public void unequalRepeatsTest() throws RuntimeException {
        //Checks for notes, accidentals
        String fileName="unequalrepeats";
        String path = "sample_abc/test_abc/"+fileName+".abc";
        Lexer myLexer = new Lexer(path);
        Parser myParser = new Parser(myLexer, path);
        List<Voice> voices = myParser.parse();
        String toMatch = "C:4 ticks\nD:4 ticks\nE:4 ticks\nF:4 ticks\nG:4 ticks\nA:4 ticks\nB:4 ticks\nC':4 ticks\nC':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\nC':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\nC':4 ticks\nB:4 ticks\nA:4 ticks\nG:4 ticks\nF:4 ticks\nE:4 ticks\nD:4 ticks\nC:4 ticks\n"; 
        assertEquals(toMatch, voices.get(0).getFullVoiceString());     
    }
}
