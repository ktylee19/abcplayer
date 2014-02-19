package abstractSyntaxTree;

import sound.SequencePlayer;

/**
 * represents a rest in the music
 * initialized if you reach a rest Token
 * only attribute is numTicks
 * no note is played during a rest
 * 
 * @author meena
 *
 */
public class Rest implements Element{
    private int length;
    /**
     * initialized based solely on numTicks
     * numTicks = timeTokenValue*Lexer.getTicksPerDefaultNote()
     * or 1*Lexer.getTicksPerDefaultNote() if there is no time token
     * @param numTicks
     */
    public Rest(int numTicks){
        this.length = numTicks;

}
    @Override
    public int getNumTicks() {
        return this.length;
    }
    
    @Override
    public String toString(){
        return "Rest:" + this.length + " ticks"; 
    }
    

    @Override
    /**
     * evaluates a rest by adding the appropriate number of ticks
     * to totalTicks
     * @param totalTicks global number of ticks in the voice part
     * @param player the sequenceplayer to which we are adding MIDI notes
     * mutates totalTicks
     */
    public void eval(int totalTicks, SequencePlayer player) {
        //nothing happens during a rest
    }
}
