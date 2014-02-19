package abstractSyntaxTree;

import sound.SequencePlayer;

/**
 * the interface implemented by Note, MultiNote, Tuplet, and Rest
 * 
 * @author meena
 *
 */
public interface Element {

    /**
     * @return the number of ticks that the note/rest is held for
     * in the case of a Tuplet, it would be the total length of the whole Tuplet
     */
    public int getNumTicks();
    
    /**
     * uses information from the Element to create a Pitch and then add that Pitch to the given SequencePlayer
     * mutates player (by adding a note to it)
     * @param totalTicks
     * @param player the SequencePlayer that will hold all of our notes
     */
    public void eval(int totalTicks, SequencePlayer player);
    
    
    public String toString();
}

