package testClasses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.SequencePlayer;
import sound.Token;
import sound.Token.Type;

import abstractSyntaxTree.Element;
import abstractSyntaxTree.MultiNote;
import abstractSyntaxTree.Note;
import abstractSyntaxTree.Rest;
import abstractSyntaxTree.Tuplet;
import abstractSyntaxTree.Voice;


/**
 * test all eval() methods to make sure the right notes are played by SequencePlayer
 * @category no_didit
 * @author meena
 *
 */


public class EvalTest {
    @Test
    //tests notes of different lengths and rests
    //tests the combination of two voice parts
    public void testBasicNotes(){
        Note note1 = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 0, 6);
        Note note2 = new Note(new Token(Token.Type.BASENOTE, "D"), 0, 0, 6);
        Note note3 = new Note(new Token(Token.Type.BASENOTE, "E"), 0, 0, 6);
        Note note4 = new Note(new Token(Token.Type.BASENOTE, "F"), 0, 0, 12);
        Note note5 = new Note(new Token(Token.Type.BASENOTE, "G"), 0, 0, 12);
        
        List<Note> chordNotes = new ArrayList<Note>();
        chordNotes.add(note1);
        chordNotes.add(note3);
        chordNotes.add(note5);
        
        MultiNote chord = new MultiNote(chordNotes, 24);
        Voice first = new Voice("first");
        Voice second = new Voice("second");
        Voice third = new Voice("third");
        //first voice "C D E F G [CEG]}
        first.addElement(note1);
        first.addElement(note2);
        first.addElement(note3);
        first.addElement(new Rest(6));
        first.addElement(note4);
        first.addElement(new Rest(12));
        first.addElement(note5);
        first.addElement(chord);
        
        Note cOctave = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 1, 12);
        //second voice a higher "c rest c rest"
        second.addElement(cOctave);
        second.addElement(new Rest(12));
        second.addElement(cOctave);
        second.addElement(new Rest(12));
        
        //third voice, a bass line (test lower octave when playing note)
        Note bass = new Note(new Token(Token.Type.BASENOTE, "G"), 0, -2, 6);

        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
        third.addElement(bass);
       


        SequencePlayer player1;
        try{
            player1 = new SequencePlayer(140, 12);
            first.eval(0, player1);
            second.eval(0, player1);
            third.eval(0, player1);
            player1.play();
        }
        catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }       
    }
   
    @Test
    public void testTuplet(){
        /*
         * Voice one: c (3BAG F E D z C
         */
        Note note1 = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 1, 12);
        Note note2 = new Note(new Token(Token.Type.BASENOTE, "B"), 0, 0, 4);
        Note note3 = new Note(new Token(Token.Type.BASENOTE, "A"), 0, 0, 4);
        Note note4 = new Note(new Token(Token.Type.BASENOTE, "G"), 0, 0, 4);
        Note note5 = new Note(new Token(Token.Type.BASENOTE, "F"), 0, 0, 12);
        Note note6 = new Note(new Token(Token.Type.BASENOTE, "E"), 0, 0, 12);
        Note note7 = new Note(new Token(Token.Type.BASENOTE, "D"), 0, 0, 12);
        Rest rest1 = new Rest(12);
        Note note8 = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 0, 12);


        
        Voice one = new Voice("one");
        one.addElement(note1);
        List<Element> tuplet = new ArrayList<Element>();
        tuplet.add(note2);
        tuplet.add(note3);
        tuplet.add(note4);
        one.addElement(new Tuplet(tuplet, 12));
        one.addElement(note5);
        one.addElement(note6);
        one.addElement(note7);
        one.addElement(rest1);
        one.addElement(note8);

        
        SequencePlayer player1;
        try{
            player1 = new SequencePlayer(140, 12);
            one.eval(0, player1);
          
            player1.play();
        }
        catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }      
        
    }
    
    @Test
    public void testChords(){
        //voice chord: [GBd] z [Ace] z [Bd^f] z 
        //voice upper: z g/2 g/2 z g/2 g/2 z g/2 g/2
        //voice lower: z d/2 c/2 z B/2 A/2 z G
        
        Note note1 = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 1, 12);
        Note note2 = new Note(new Token(Token.Type.BASENOTE, "B"), 0, 0, 12);
        Note note3 = new Note(new Token(Token.Type.BASENOTE, "A"), 0, 0, 12);
        Note note4 = new Note(new Token(Token.Type.BASENOTE, "G"), 0, 0, 12);
        Note note5 = new Note(new Token(Token.Type.BASENOTE, "F"), 1, 1, 12);
        Note note6 = new Note(new Token(Token.Type.BASENOTE, "E"), 0, 1, 12);
        Note note7 = new Note(new Token(Token.Type.BASENOTE, "D"), 0, 1, 12);
        Rest rest1 = new Rest(12);
        Note note8 = new Note(new Token(Token.Type.BASENOTE, "G"), 0, 1, 6);
        Note dHalf = new Note(new Token(Token.Type.BASENOTE, "D"), 0, 1, 6);
        Note cHalf = new Note(new Token(Token.Type.BASENOTE, "C"), 0, 1, 6);
        Note bHalf = new Note(new Token(Token.Type.BASENOTE, "B"), 0, 0, 6);
        Note aHalf = new Note(new Token(Token.Type.BASENOTE, "A"), 0, 0, 6);

        

        
        Voice chord = new Voice("chord");
        List<Note> c1 = Arrays.asList(note4, note2, note7);
        chord.addElement(new MultiNote(c1, 12));
        chord.addElement(rest1);
        chord.addElement(new MultiNote(Arrays.asList(note3, note1, note6), 12));
        chord.addElement(rest1);
        chord.addElement(new MultiNote(Arrays.asList(note2, note7, note5), 12));
        chord.addElement(rest1);

        Voice upper = new Voice("upper");
        upper.addElement(rest1);
        upper.addElement(note8);
        upper.addElement(note8);
        upper.addElement(rest1);
        upper.addElement(note8);
        upper.addElement(note8);
        upper.addElement(rest1);
        upper.addElement(note8);
        upper.addElement(note8);
        
        Voice lower = new Voice("lower");
        lower.addElement(rest1);
        lower.addElement(dHalf);
        lower.addElement(cHalf);
        lower.addElement(rest1);
        lower.addElement(bHalf);
        lower.addElement(aHalf);
        lower.addElement(rest1);
        lower.addElement(note4);

        SequencePlayer player1;
        try{
            player1 = new SequencePlayer(140, 12);
            chord.eval(0, player1);
            upper.eval(0, player1);
            lower.eval(0, player1);
            player1.play();
        }
        catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
       
        
    }
 

}
