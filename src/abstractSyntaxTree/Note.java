package abstractSyntaxTree;


import sound.Pitch;
import sound.SequencePlayer;
import sound.Token;

/**
 * represents a single Note in the music
 * @author meena
 *
 */
public class Note implements Element{

    private int length;
    private char pitch; //ex: "C", "D", etc.
    private int accidental;
    private int octave = 0; //number of octaves away from the note (ex: c token --> octave = 1, ,c token --> octave = -1)
    
    /**
     * initialize a Note by inputting baseNote Token and various note attributes
     * @param baseNote Token
     * @param accidentalVal the value as determined by KeySigMap (-2, -1, 0, 1, or 2)
     * @param extraOctaves the number of octaves determined by octave Token (",," = -2 extra octaves for example)
     * @param numTicks either 1*Lexer.getTicksPerDefaultNote() if there is no time token 
     * or timeTokenValue*Lexer.getTicksPerDefaultNote() if there is a time token
     */
    
    public Note(Token baseNote, int accidentalVal, int extraOctaves, int numTicks){
      
        if(baseNote.text.matches("[a-g]")){
            this.octave += 1;
        }
        else if(!baseNote.text.matches("[A-G]")){
            throw new IllegalArgumentException("Couldn't create note- Invalid Basenote Token");
        }
        this.pitch = baseNote.text.toUpperCase().toCharArray()[0];
        this.length = numTicks;
        this.accidental = accidentalVal;
        this.octave += extraOctaves;
      
    }
    

    /**
     * creates a pitch based on the attributes specified in the Note class
     * i.e. basenote, octave, and accidental
     * @return a Pitch with the correct note attributes
     */
    
    public Pitch evalNote(){
        Pitch note = new Pitch(this.pitch);
        Pitch augmented = note.accidentalTranspose(this.accidental).octaveTranspose(this.octave);
        return augmented;
    }
    
    @Override
    public int getNumTicks() {
        return this.length;
    }
    
    @Override
    public String toString(){
        String fullNote = "";
        if(this.accidental == -2){
            fullNote += "bb";
        }
        if(this.accidental == -1){
            fullNote += "b";
        }
        if(this.accidental == 1){
            fullNote += "#";
        }
        if(this.accidental == 2){
            fullNote += "##";
        }
        fullNote += this.pitch;
        if(this.octave < 0){
            for(int i = 0; i < Math.abs(octave); i++){
                fullNote += ",";
            }            
        }
        if(this.octave > 0){
            for(int i = 0; i < Math.abs(octave); i++){
                fullNote += "'";
            }   
        }
        fullNote += ":";
        fullNote += this.length + " ticks";
        
        return fullNote;
    }
    
    @Override
    /**
     * @param totalTicks: the number of total ticks in the voice part so far
     * @param player: the sequence player to which we are adding MIDI notes
     * mutates player by adding the note
     */
    
    public void eval(int totalTicks, SequencePlayer player) {
        Pitch finalPitch = evalNote();
        player.addNote(finalPitch.toMidiNote(), totalTicks, this.length);
    }
    


}
