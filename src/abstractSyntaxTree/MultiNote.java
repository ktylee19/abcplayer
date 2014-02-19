package abstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

import sound.Pitch;
import sound.SequencePlayer;


/**
 * represents a chord in the musical score
 * 
 * @author meena
 *
 */

public class MultiNote implements Element {
    private int length;
    private List<Note> chordNotes; // will be used in .eval()
    
    /**
     * initialize based on a List of Notes that are
     * in that chord
     * all Notes should have same numTicksPerNote
     * @param defaultTicksInChord
     * @param numTicksPerNote
     */
    public MultiNote(List<Note> allNotesInChord,int defaultTicksInChord){
        this.length = defaultTicksInChord;
        this.chordNotes = new ArrayList<Note>(allNotesInChord);
    }
    
    @Override
    public int getNumTicks() {
        return this.length;
    }
    
    @Override
    public String toString(){
        String allNotes = "[";
        for(Note n : this.chordNotes){
            allNotes += n.toString();
            allNotes += " ";
        }
        
        allNotes += "]";
        return allNotes;
    }
    
    @Override
    
    /**
     * @param totalTicks updated at the end with the length of a note in the chord
     * @param player the SequencePlayer to which MIDI notes are being added
     * totalTicks and player are mutated
     */
    public void eval(int totalTicks, SequencePlayer player) {
        for(Note n : chordNotes){
            Pitch p = n.evalNote();
            player.addNote(p.toMidiNote(), totalTicks, n.getNumTicks());
        }
    }
    

}
