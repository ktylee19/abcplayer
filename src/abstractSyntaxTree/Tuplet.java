package abstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

import sound.SequencePlayer;


/**
 * represents a tuplet in the musical score
 * @author meena
 *
 */
public class Tuplet implements Element{
    private List<Element> notes;
    private int totalLength;
   
    
   /**
    *  
    * @param tupletNotes a List of all of the Notes/MultiNotes/Rests within a tuplet 
    * must be of length 2, 3, or 4
    * @param totalNumTicks the total number of ticks needed in total for the tuplet
    */
    public Tuplet(List<Element> tupletNotes, int totalNumTicks){
        this.notes = new ArrayList<Element>(tupletNotes);
        this.totalLength = totalNumTicks;
    }


@Override
public int getNumTicks() {
    return this.totalLength;
}

@Override
public String toString(){
    String tuplet = "(" + this.notes.size();
    for(Element n : this.notes){
        tuplet += n.toString();
        tuplet += " ";
    }
    return tuplet;
}
@Override
/**
 * @param totalTicks
 * @param SequencePlayer player
 * both are mutated as notes are added to the sequence player
 * and global number of ticks is updated
 */
public void eval(int totalTicks, SequencePlayer player) {
    for(int i = 0; i < this.notes.size(); i++){
        this.notes.get(i).eval(totalTicks, player);
        totalTicks += this.notes.get(i).getNumTicks();
    }
    
}

}
