package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import abstractSyntaxTree.Voice;

import sound.*;

/**
 * Main entry point of your application.
 */
public class Main {

    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * (Your code should not exit the application abnormally using
     * System.exit().)
     * 
     * @param file the name of input abc file
     */
    public static void play(String file) {
        // YOUR CODE HERE
        HeaderReader reader = new HeaderReader(file);
        int tempo = reader.getTempo();
        /*@IMPTNOT: For SequencePlayer, the default ticks should be set as 
        *                  (Lexer.getTicksPerDefaultNote() * HeaderReader.getDefaultNoteLength / 4)
        */
        Lexer lexer = new Lexer(file);

        double defaultPerQuarterNote = lexer.getTicksPerDefaultNote();
        Parser parser = new Parser(lexer, file);
        List<Voice> voices = parser.parse();
        try{
        SequencePlayer player = new SequencePlayer(reader.getTempo(), (int) defaultPerQuarterNote);
            for(Voice v : voices){
                v.eval(0, player);
            }
 
            player.play(); 
        }
        catch (MidiUnavailableException e) {
            e.printStackTrace();
        }catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }  
    }

    public static void main(String[] args) throws IOException {
        // CALL play() HERE
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String fullPath;
        do{
            System.out.print("$Enter the full path of an .abc file to play it: ");
            fullPath = in.readLine();
            if (!fullPath.equals("")) {
                try {
                    play(fullPath);
                    
                } catch (RuntimeException re) {
                    System.err.println("Error: " + re.getMessage());
                }
            }
            System.exit(0);
        }
        while(!fullPath.equals(""));
    }

}
