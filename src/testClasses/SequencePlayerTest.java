package testClasses;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.Pitch;
import sound.SequencePlayer;

/* Test warmup exercises
* @category no_didit
*/
public class SequencePlayerTest {
   
    @Test
    public void pieceOneTest(){
        SequencePlayer player;
        try {

            // Create a new player, with 140 beats (i.e., quarter note) per
            // minute, with 12 ticks per quarter note.
            player = new SequencePlayer(140, 12);
            
            //measure 1
            player.addNote(new Pitch('C').toMidiNote(), 0, 12);
            player.addNote(new Pitch('C').toMidiNote(), 12, 12);
            player.addNote(new Pitch('C').toMidiNote(), 24, 9);
            player.addNote(new Pitch('D').toMidiNote(), 33, 3);
            player.addNote(new Pitch('E').toMidiNote(), 36, 12);
            
            //measure 2
            player.addNote(new Pitch('E').toMidiNote(), 48, 9);
            player.addNote(new Pitch('D').toMidiNote(), 57, 3);
            player.addNote(new Pitch('E').toMidiNote(), 60, 9);
            player.addNote(new Pitch('F').toMidiNote(), 69, 3);
            player.addNote(new Pitch('G').toMidiNote(), 72, 24);
            
            //measure 3
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
            player.addNote(new Pitch('G').toMidiNote(), 108, 4);
            player.addNote(new Pitch('G').toMidiNote(), 112, 4);
            player.addNote(new Pitch('G').toMidiNote(), 116, 4);
            player.addNote(new Pitch('E').toMidiNote(), 120, 4);
            player.addNote(new Pitch('E').toMidiNote(), 124, 4);
            player.addNote(new Pitch('E').toMidiNote(), 128, 4);
            player.addNote(new Pitch('C').toMidiNote(), 132, 4);
            player.addNote(new Pitch('C').toMidiNote(), 136, 4);
            player.addNote(new Pitch('C').toMidiNote(), 140, 4);
            
            //measure 4
            player.addNote(new Pitch('G').toMidiNote(), 144, 9);
            player.addNote(new Pitch('F').toMidiNote(), 153, 3);
            player.addNote(new Pitch('E').toMidiNote(), 156, 9);
            player.addNote(new Pitch('D').toMidiNote(), 165, 3);
            player.addNote(new Pitch('C').toMidiNote(), 168, 24);

            // play!
            player.play();

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void pieceTwoTest(){
        SequencePlayer player;
        try {

            // Create a new player, with 200 beats (i.e., quarter note) per
            // minute, with 12 ticks per quarter note.
            player = new SequencePlayer(200, 12);

            
            //measure 1
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 0, 6);
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 0, 6);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 6, 6);
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 6, 6);
            //rest (12 to 18)
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 18, 6);
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 18, 6);
            //rest (24 to 30)
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 30, 6);
            player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 30, 6);
            player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 36, 12);
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 36, 12);

            //measure 2
            player.addNote(new Pitch('G').toMidiNote(), 48, 12);
            player.addNote(new Pitch('B').toMidiNote(), 48, 12);
            player.addNote(new Pitch('G').octaveTranspose(1).toMidiNote(), 48, 12);
            //rest (60 to 72)
            player.addNote(new Pitch('G').toMidiNote(), 72, 12);
            //rest (84 to 96)
     
            //measure 3
            player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 96, 18);
            player.addNote(new Pitch('G').toMidiNote(), 114, 6);
            //rest (120 to 132)
            player.addNote(new Pitch('E').toMidiNote(), 132, 12);

    
            //measure 4
            player.addNote(new Pitch('E').toMidiNote(),144 , 6); 
            player.addNote(new Pitch('A').toMidiNote(),150, 12);
            player.addNote(new Pitch('B').toMidiNote(), 162, 12);
            player.addNote(new Pitch('B').accidentalTranspose(-1).toMidiNote(), 174, 6);
            player.addNote(new Pitch('A').toMidiNote(), 180, 12);

            //measure 5
            player.addNote(new Pitch('G').toMidiNote(), 192, 8);
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 200, 8);
            player.addNote(new Pitch('G').octaveTranspose(1).toMidiNote(), 208, 8);
            player.addNote(new Pitch('A').octaveTranspose(1).toMidiNote(), 216, 12);
            player.addNote(new Pitch('F').octaveTranspose(1).toMidiNote(), 228, 6);
            player.addNote(new Pitch('G').octaveTranspose(1).toMidiNote(), 234, 6);

            //measure 6
            //rest (240 to 246)
            player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 246, 12);
            player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 258, 6);
            player.addNote(new Pitch('D').octaveTranspose(1).toMidiNote(), 264, 6);
            player.addNote(new Pitch('B').toMidiNote(), 270, 9);
            //rest (279 to 288)

            // play!
            player.play();

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

}
