package sound;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import abstractSyntaxTree.Voice;

/**
 * HeaderReader Class
 * -HeaderReader constructor
 * -findKeySignatureMap method - completed! (@author meena)
 * -fieldDefiner method
 * -getComposer method
 * -getMeter method
 * -getDefaultNoteLength method
 * -getTempo method
 * -getVoices method
 * 
 * meter, defaultNoteLength, and tempo will be used later to compute ticks.
 * 
 * For SequencePlayer, the default ticks should be set as 
 *      (Lexer.getTicksPerDefaultNote() * HeaderReader.getDefaultNoteLength / 4)
 * 
 * @author Katie
 * @author meena - added in the voiceList attribute and modified fieldDefiner to add Voices to the attribute
 * 
 */
public class HeaderReader {
    
    private String fileName;
    private int X; //index number
    private String T; //title
    private String C; //composer
    private String M; //meter
    private String L; //default length or duration of a note
    private String K; //determines key signature
    private int Q; //tempo
    private List<Voice> voices = new ArrayList<Voice>(); 
    
    
    /**
     * HeaderReader constructor
     * 
     * Creates the HeaderReader with the given file name.
     * If something is defined in the header twice (if not index number, title, or key signature),
     * then the last value will be the one considered. 
     * 
     * @param string The name of the .abc file.
     */    
    public HeaderReader(String fileName) {
        this.fileName = fileName; 
        String[] Vals = fieldDefiner(); //defines the XTCMLKQ things. 
        try {
            this.X=Integer.parseInt(Vals[0].trim());
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("X: defines a piece number. "+this.X+" is not a number.");
            
        }
        this.T=Vals[1];
        //C
        if (Vals[2] == null) {
            this.C="Unknown";
        }
        else {
            this.C=Vals[2];
        }
        //M
        if (Vals[3] == null) {
            this.M="4/4";
        }
        else {
            this.M=Vals[3];
        }
        //L
        if (Vals[4]==null) {
            this.L="1/8";
        }
        else {
            this.L=Vals[4];
        }
        //K
        this.K=Vals[5].trim();
        
        /*
         * there should only be one voice, "Default" if no voice is specified
         * in the header
         */
        if(this.voices.isEmpty()){
            this.voices.add(new Voice("Default"));
        }
        if (Vals[6]==null) {
            this.Q=100;
        }
        else {
            this.Q=Integer.parseInt(Vals[6].trim());
        }
    }
    
    /**
     * KeySignatureMap HashMap.
     * 
     * Determines key signature using given key signature.
     * 
     * @returns HashMap, where key=note, value=accidental
     * 
     */
    public KeySigMap findKeySignatureMap() {
        return new KeySigMap(this.K);
    }
    
    /**
     * Finds tempo
     * 
     * @return tempo
     */
    public int getTempo() {
        return this.Q;
    }
    
    /**
     * Finds composer
     * 
     * @return composer, "Unknown" if not defined
     */
    public String getComposer() {
        return this.C;
    }
    
    /**
     * Finds denominator of default note length
     * @note we dont need numerator because we don't care about correct measures
     * 
     * @param default note length must be given as a fractional value (with \ divider)
     * @return denominator of default note length, in decimal form, returns 8 otherwise
     */
    public double getDefaultNoteLength() {
        //make sure we have a valid input for defaultNoteLength
        if (!this.L.matches("(\\d)+(/\\d+)*")) {
            return 8;
        }
        //find default note length
        //check for fraction
        String myNoteLen=this.L;
        for (int n=0; n < myNoteLen.length(); n++) {
            // if hit divide, then hold.
            if (myNoteLen.charAt(n)=='/') {
                return Integer.parseInt(myNoteLen.substring(n+1));
            }
        }
        return Integer.parseInt(myNoteLen.substring(myNoteLen.length()-1));
    }
    
    /**
     * Finds meter
     * 
     * @param meter must be given as a fractional value (with \ divider)
     * @return integer meter, in decimal form, returns .125 otherwise
     */
    public double getMeter() {
        
        //make sure we have a valid input for meter
        //System.out.println("i want "+this.M);
        if (!this.M.matches("(\\d)+/\\d+")) {
            return 1;
        }
        
        //find meter
        if (this.M != null) {
            //check for fraction
            String myNoteLen=this.M;
            StringBuilder myNum = new StringBuilder();
            for (int n=0; n < myNoteLen.length(); n++) {
                // if hit divide, then hold.
                if (myNoteLen.charAt(n)=='/') {
                    //myNum is numerator
                    Integer numer = Integer.parseInt(myNum.toString());
                    Integer denomer = Integer.parseInt(myNoteLen.substring(n+1));
                    return (double) numer/denomer;
                }
                if (Character.isDigit(myNoteLen.charAt(n))) {
                    myNum.append(myNoteLen.charAt(n));
                }
            }
        }
        return 1;
    }
    
    public List<Voice> getVoices(){
        return this.voices;
    }
    
    
    /** 
     * Reader
     * 
     * Defines fields for X,T,C,L,M,Q,V,K as defined in ABC Subset Description: 
     *   X: Piece Number(digit)
     *   T: title(text)
     *   C: composer(text)
     *   M: meter(d/d)
     *   L: default note length(d/d)
     *   V: voice(text)
     *   K: key (letter with major/minor)
     *   Q: tempo(d/d)
     *   
     * @param the first field in the header must be the index number ('X')
     * @param the second field in the header must be the title('T')
     * @param the last field in the header must be they key ('K')
     * @param each header field must start on a new line
     * @param same header field must stay on one line (i.e. no spaces within a header definition)
     * @param if there are multiple voices in the piece, they must be defined in the headerReader
     * 
     * @author meena fieldDefiner also adds Voice parts to this.voices 
     * 
     * @throws IllegalArgumentError if something in header area is not a valid header
     * 
     */
    
    private String[] fieldDefiner() {
        boolean hitX = false;
        boolean hitT = false;
        boolean hitK = false;
        String[] myFields = new String[7]; // in the order XTCLMKQ
        
        //can try to redefine this as three while loops for all the cases.
        //new method to do 
        
        try {
            //Defines file to read
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String sCurrentLine=br.readLine();
            
            
            //Reads through lines
            while ((sCurrentLine!= null) && (hitK==false)) {
                //First line should be X:
                if (hitX==false) {
                    //check match!
                    if (sCurrentLine.startsWith("X:")) {
                        //this.X = int following X:
                        hitX=true;
                        
                        //default to whole string
                        myFields[0] = sCurrentLine.substring(2);
                        //unless there is a comment.
                        for (int i=2; i<sCurrentLine.length(); i++) {
                            if (sCurrentLine.charAt(i)=='%') {
                                myFields[0] = sCurrentLine.substring(2, i);
                                break;
                            }   
                        }
                        
                    }
                    //if blank line, just check next one.
                    else if (sCurrentLine.matches("\\n")) {
                        continue;
                    }
                    else { //illegal input
                        throw new IllegalArgumentException("First field in header must define index number.");
                    }
                    //if it's not a blank line, we're going to throw an error.
                }
                
                else {
                    //Second line should be T:
                    if (hitT==false) {
                        //check match!
                        if (sCurrentLine.startsWith("T:")) {
                            //this.T = string following T:
                            hitT=true;
                           //default to all unless comment
                            myFields[1] = sCurrentLine.substring(2);
                            for (int i=2; i<sCurrentLine.length(); i++) {
                                if (sCurrentLine.charAt(i)=='%') {
                                    myFields[1] = sCurrentLine.substring(2, i);
                                    break;
                                }   
                            }
                        }
                        //if blank line, just check next one.
                        else if (sCurrentLine.matches("\\n")) {
                            continue;
                        }
                        else { //illegal input
                            throw new IllegalArgumentException("Second field in header must define title.");
                        }
                    }
                    //Check for valid headers, save their values:
                    else {
                        if (sCurrentLine.startsWith("K:")) {
                            //this.K = string following K:
                            hitK=true;
                            //store K value.
                          //default whole string unless comment
                            myFields[5] = sCurrentLine.substring(2);
                            for (int i=2; i<sCurrentLine.length(); i++) {
                                if (sCurrentLine.charAt(i)=='%') {
                                    myFields[5] = sCurrentLine.substring(2, i);
                                    break;
                                }   
                            }
                        }
                        else {
                            if (sCurrentLine.startsWith("C:")) {
                                myFields[2] = sCurrentLine.substring(2);
                                for (int i=2; i<sCurrentLine.length(); i++) {
                                    if (sCurrentLine.charAt(i)=='%') {
                                        myFields[2] = sCurrentLine.substring(2, i);
                                        break;
                                    }   
                                }
                            }
                            if (sCurrentLine.startsWith("M:")) {
                                myFields[3] = sCurrentLine.substring(2);
                                for (int i=2; i<sCurrentLine.length(); i++) {
                                    if (sCurrentLine.charAt(i)=='%') {
                                        myFields[3] = sCurrentLine.substring(2, i);
                                        break;
                                    }   
                                }
                            }
                            if (sCurrentLine.startsWith("L:")) {
                                myFields[4] = sCurrentLine.substring(2);
                                for (int i=2; i<sCurrentLine.length(); i++) {
                                    if (sCurrentLine.charAt(i)=='%') {
                                        myFields[4] = sCurrentLine.substring(2, i);
                                        break;
                                    }   
                                }
                            }
                            if (sCurrentLine.startsWith("Q:")) {
                                myFields[6] = sCurrentLine.substring(2);
                                for (int i=2; i<sCurrentLine.length(); i++) {
                                    if (sCurrentLine.charAt(i)=='%') {
                                        myFields[6] = sCurrentLine.substring(2, i);
                                        break;
                                    }   
                                }
                            }
                            if (sCurrentLine.startsWith("V:")){
                                String myVoice=sCurrentLine.substring(2);
                                for (int i=2; i<sCurrentLine.length(); i++) {
                                    if (sCurrentLine.charAt(i)=='%') {
                                        myVoice = sCurrentLine.substring(2, i).toString();
                                        break;
                                    }   
                                }
                                this.voices.add(new Voice(myVoice));
                                
                            }
                        }
                        
                    }
                }
                sCurrentLine=br.readLine();
            }
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Could not read file.");
        } 
        if (!hitX && !hitT && !hitK) {
            throw new IllegalArgumentException("Necessary header information is not present or is not in proper order.");
        }
        return myFields;
    }

}
