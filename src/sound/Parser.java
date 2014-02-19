package sound;

import java.util.ArrayList;
import java.util.List;

import abstractSyntaxTree.*;

//Assumptions:
//No nested repeats
//Does not handle a chord of tuplets

public class Parser {
    private Lexer lexer;
    private final HeaderReader myHeader;
    //List of voices to return
    private List<Voice> voices = new ArrayList<Voice>();
    //Index to keep track of current voice
    private int currVoice = 0;
    //Key Signature map for piece
    private KeySigMap keySigMap;
    //Default number of ticks, set by lexer
    private int defNumTicks;
    private Token tok;
    
    /**
     * Parser Constructor
     * 
     * Creates the parser from lexer and path 
     * Finds key signature map, number of voices, default number of ticks, and first token
     * 
     * @param Lexer lexer 
     * @param String path: path to pass into HeaderReader
     */ 
    public Parser(Lexer lexer, String path) {
        this.lexer = lexer;
        myHeader = new HeaderReader(path);
        keySigMap = myHeader.findKeySignatureMap();
        this.voices = myHeader.getVoices();
        defNumTicks = lexer.getTicksPerDefaultNote();
        this.tok = lexer.next();
    }
    
    /**
     * Main Parse Method, called by Evaluator
     * 
     * Parses through the tokens created by Lexer, constructing a list of Elements in a voice
     * 
     * @return List of voices with list of elements inside them
     */ 
    public List<Voice> parse(){
        List <Element> elmInTup= new ArrayList<Element>();
        Integer noAccidental = null;
        while(this.tok != null){
            switch(this.tok.type){
            
            case VOICE:   
                //Looks for voice in voice list, assigns currVoice to index in list
                for (int i = 0; i < voices.size(); i++){
                    if (this.tok.text.equals(voices.get(i).toString())){
                        //System.out.println("switch voice from "+ voices.get(currVoice).toString() + " to " + voices.get(i).toString());
                        currVoice = i;
                        break;
                    }
                }
                this.tok  = this.lexer.next();
                break;
                
            case START_BAR:
                voices.get(currVoice).setOpenRepeat();
                this.tok = this.lexer.next();
                break;
                
            case START_REPEAT_BAR:
                voices.get(currVoice).setBooleanOpen();
                voices.get(currVoice).setOpenRepeat();
                this.tok = this.lexer.next();
                break;
            
            //clears accidentals
            case MEASURE_BAR:
                keySigMap.clearAccidental();
                this.tok = this.lexer.next();
                break;
                
            case NTHREPEAT1:
                voices.get(currVoice).setNthRepeatOne();
                this.tok = this.lexer.next();
                break;
                
            case ACCIDENTAL:
                Note newNote = makeNoteWithAccidental(defNumTicks);
                voices.get(currVoice).addElement(newNote);
                this.tok = this.lexer.next();
                break;
                
            case BASENOTE:
                Note baseNote = makeNote(noAccidental, defNumTicks);
                voices.get(currVoice).addElement(baseNote);
                this.tok = this.lexer.next();
                break;

            case REST:
                voices.get(currVoice).addElement(makeRest(defNumTicks));
                this.tok = lexer.next();
                break;
                
            case OPEN_CHORD:
                this.tok = lexer.next();
                voices.get(currVoice).addElement(makeChord(defNumTicks));
                this.tok = lexer.next();  
                break;
                
                
            case TUPLET_SPEC:
                //Cycles through as many notes as specified by tuplet
                char[] tup = tok.text.toCharArray();
                int numNotes = Character.getNumericValue(tup[1]);
                int tupTicks = defNumTicks;
                if(numNotes == 2){
                    tupTicks = 3*defNumTicks;
                }
                if(numNotes == 3){
                    tupTicks = 2*defNumTicks;
                }
                if(numNotes == 4){
                    tupTicks = 3*defNumTicks;
                }
                int numTicksInTup = 0;
                this.tok = this.lexer.next();
                for(int i= 0; i < numNotes; i++){
                    if(tok.type == Token.Type.ACCIDENTAL){
                        Note n = makeNoteWithAccidental(tupTicks/numNotes);
                        elmInTup.add(n);
                        numTicksInTup += n.getNumTicks();
                        this.tok = this.lexer.next();
                    }
                    else if(tok.type == Token.Type.BASENOTE){
                        Note m = makeNote(noAccidental, tupTicks/numNotes);
                        elmInTup.add(m);
                        numTicksInTup += m.getNumTicks();
                        this.tok = this.lexer.next();
                    }
                    else if(tok.type == Token.Type.REST){
                        Rest r = makeRest(tupTicks/numNotes);
                        elmInTup.add(r);
                        numTicksInTup += r.getNumTicks();
                        this.tok = this.lexer.next();
                    }
                    else if(tok.type == Token.Type.OPEN_CHORD){
                        tok = lexer.next();
                        MultiNote c = makeChord(tupTicks/numNotes);
                        elmInTup.add(c);
                        numTicksInTup += c.getNumTicks();
                    }
                    else{
                        throw new IllegalArgumentException("Tuplet does not contain valid input");
                    }
                }
                voices.get(currVoice).addElement(new Tuplet(elmInTup, numTicksInTup));
                elmInTup.clear();
                break;
                
            case END_REPEAT_BAR:
                
                tok = lexer.next();
                if (tok == null){
                    voices.get(currVoice).repeat();
                    break;
                }
                if(tok.type != Token.Type.NTHREPEAT2){
                    voices.get(currVoice).repeat();
                }
                //this.tok = this.lexer.next();
                break;                                
   
            case DOUBLE_BAR:
            case END_BAR:
                //System.out.println(voices.get(currVoice).getFullVoiceString());
                voices.get(currVoice).setOpenRepeat();
                this.tok = this.lexer.next();
                break;
                
            case NTHREPEAT2:
                //checks to see if nth repeat1 has been set before
                if (voices.get(currVoice).getNthRepeatOne() != -1){
                    voices.get(currVoice).alternateEnding2();
                    this.tok = this.lexer.next();
                    break;
                }
                else
                    throw new RuntimeException("Nth Repeat 1 has not been set. ");
           
            //Can't start an Element with any of these tokens
            case OCTAVE:
                throw new RuntimeException("Invalid octave token");
            case NULL:
                throw new RuntimeException("Invalid null token");
            case TIME:
                throw new RuntimeException("Invalid time token");
            case CLOSE_CHORD:
                throw new RuntimeException("Invalid close chord token");
            default:
                throw new RuntimeException("Malformed Input");
            }
        }
        return voices;

    }
    
    /**
     * makeNoteWithAccidental Method 
     * 
     * Parses through the accidental token, then calls makeNote method with value from accidental
     * 
     * @param Default number of ticks
     * @return Note element
     */ 
    private Note makeNoteWithAccidental (int defaultNumTicks) throws IllegalArgumentException {
        int accidental = 0;
        switch(this.tok.type){
        case ACCIDENTAL:
            //Finds value of accidental
            if (this.tok.text.equals("^"))
                accidental = 1;
            else if (this.tok.text.equals("^^"))
                accidental = 2;
            else if (this.tok.text.equals("_"))
                accidental = -1;
            else if (this.tok.text.equals("__"))
                accidental = -2;
            else if(this.tok.text.equals("=")){
                accidental = 0;
            }
            //Moves on to next token
            this.tok = this.lexer.next();

            if(this.tok == null){
                throw new IllegalArgumentException("Abrupt end of piece");
            }
            if (this.tok.type != Token.Type.BASENOTE){
                throw new IllegalArgumentException("No basenote after accidental");
            }
            break;
        default:
            throw new RuntimeException ("Trying to make a note with an accidental without an accidental");
        }
        
        //Calls the makeNote method to finish
        return makeNote(accidental, defaultNumTicks);
    }
    
    /**
     * makeNote Method 
     * 
     * Parses through the note, finds note, octave and time
     * 
     * @param Accidental value, null if none found
     * @param Default number of ticks
     * @return Note element
     */ 
    private Note makeNote(Integer acc, int defaultNumTicks) throws IllegalArgumentException{
        //Uses accidental provided, null if no accidental is specified for a note
        Integer accidental = acc;
        String noteAndOctave = "";
        Token baseNote;
        int octaves = 0;
        double time = defaultNumTicks;
        switch(this.tok.type){
            case BASENOTE:
            //Creates note token           
            noteAndOctave += this.tok.text;
            if(noteAndOctave.matches("[a-g]")){
                noteAndOctave = noteAndOctave.toUpperCase();
                octaves += 1;
            }
            else{
                if(!noteAndOctave.matches("[A-G]")){
                    //lexer should catch this, but just in case it doesn't...
                    throw new IllegalArgumentException("Invalid basenote token");
                }
            }
            baseNote = new Token(this.tok.type, this.tok.text.toUpperCase());
            this.tok = this.lexer.next();
            if(this.tok == null){
                if(accidental != null){
                    this.keySigMap.addAccidental(noteAndOctave, accidental);
                } 
                accidental = this.keySigMap.getNoteValue(noteAndOctave);
                return new Note(baseNote, accidental, octaves, (int) time);   
            }
            if(this.tok.type == Token.Type.OCTAVE){
                if(this.tok.text.matches("[']+")){
                    octaves += this.tok.text.length();
                }
                else if(this.tok.text.matches("[,]+")){
                    octaves -= this.tok.text.length();
                }
                else{
                    throw new IllegalArgumentException("Shouldn't get here");
                }
                if(octaves > 0){
                    for(int i = 0; i < octaves; i++){
                        noteAndOctave += "'";
                    }
                }
                if(octaves < 0){
                    for(int i = 0 ; i < Math.abs(octaves); i++){
                        noteAndOctave += ",";
                    }
                }
                this.tok = this.lexer.next();
                if(this.tok == null){
                    accidental = this.keySigMap.getNoteValue(noteAndOctave);
                    return new Note(baseNote, accidental, octaves, (int) time);   
                }
            }
            if(accidental != null){
                this.keySigMap.addAccidental(noteAndOctave, accidental);
            }   
            
            if(this.tok.type == Token.Type.TIME){
                double frac = Double.parseDouble(this.tok.text);
                time = time*frac;
                this.tok = this.lexer.next();
            }
            
            if (this.tok.type != Token.Type.OCTAVE || this.tok.type != Token.Type.TIME){
                this.tok = this.lexer.prev();
            }
            accidental = this.keySigMap.getNoteValue(noteAndOctave);
            return new Note(baseNote, accidental, octaves, (int) time);   
        default:
            //has to start w/ accidental or basenote
            throw new IllegalArgumentException("Shouldn't get here");
        }
        
    }
   
    /**
     * makeRest Method 
     * 
     * Parses through the rest, finds time
     * 
     * @param Default number of ticks
     * @return Rest element
     */ 
    private Rest makeRest(int defaultNumTicks){
        this.tok = this.lexer.next();
        if(this.tok.type == Token.Type.TIME){
            double time = Double.parseDouble(this.tok.text);
            int total = (int) (time *defaultNumTicks);
            return new Rest(total);
        }
        else{
            this.tok = this.lexer.prev();
            return new Rest(defaultNumTicks);
        }
    }
    
    /**
     * makeChord Method 
     * 
     * Parses through the chord, calls one of the make methods
     * 
     * @param Default number of ticks
     * @return Note element
     */ 
    private MultiNote makeChord(int defaultNumTicks){
        List<Note> elmInChord=  new ArrayList<Note>();
        Integer noAccidental = null;
        int defChordSize = defaultNumTicks;
        while(true){
            if(this.tok.type == Token.Type.ACCIDENTAL){
                Note nextNote = makeNoteWithAccidental(defaultNumTicks);
                elmInChord.add(nextNote);
                if(elmInChord.size() == 1){
                    defChordSize = nextNote.getNumTicks();
                }
               this.tok = this.lexer.next();
            }
            else if(this.tok.type == Token.Type.BASENOTE){
                Note nextNote = makeNote(noAccidental, defaultNumTicks);
                elmInChord.add(nextNote);
                if(elmInChord.size() == 1){
                    defChordSize = nextNote.getNumTicks();
                }
               this.tok = this.lexer.next();
            }  
            else if(this.tok.type == Token.Type.CLOSE_CHORD){
                return new MultiNote(elmInChord, defChordSize);
            }
            else{
                throw new IllegalArgumentException("Invalid input within a chord");
            }
        }
    }
}
