package sound;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * Lexer Class
 * -Lexer Constructor
 * -public getTicksPerDefaultNote method
 * -private getTokens method
 * -public getTokenList method
 * -private typeFinder method
 * 
 * Helper Functions:
 * -lcm
 * -typeFinder
 * -fractionChange
 * 
 * @author Katie
 */


public class Lexer {
    private final String fileName;
    private ArrayList<Token> tokenList;
    private int myLCM=4;
    private int myTuplet=0;
    private int counter = -1;
    
    /**
     * Lexer Constructor
     * 
     * Creates the lexer over the passed string.
     * 
     * @param string The path of the file to tokenize.  Must have key signature definition.
     */    
    public Lexer(String fileName) {
        this.fileName = fileName;
        this.tokenList = getTokens();
        
    }
    
    
    /**
     * Method getTicksPerDefaultNote
     * 
     * Finds number of ticks per note length.
     * Iterates through thing, finds LCM of all numbers.
     * 
     * @return number of ticks per note length, in int
     */
    public int getTicksPerDefaultNote() {
        return this.myLCM; 
    }
    
    /**
     * Method Get Token List
     * 
     * @return token list
     */
    public ArrayList<Token> getTokenList() {
        return this.tokenList; 
    }
    
    /**
     * 
     * @return the next Token in tokenList
     * returns null if the end of the list is reached
     */
    public Token next(){
        counter ++;
        if(counter >= this.tokenList.size()){
            return null;
        }
        return this.tokenList.get(counter);
    }
    
    /**
     * 
     * @return the previous Token in tokenList
     * returns null if the front of the list is reached
     */
    public Token prev(){
        counter --;
        if(counter == -1){
            return null;
        }
        return this.tokenList.get(counter);
    }

    
    /** 
     * getTokens
     * 
     * Iterates through the string to tokenize string
     * correspond to different Token types.
     * 
     * Comments are ignored during the tokenizing.
     * Fractions are converted into decmials.
     * 
     * @returns an ArrayList of tokens
     * @throws IllegalArgumentException if file path is not valid
     */
    private ArrayList<Token> getTokens() {
        // create output
        ArrayList<Token> tokenList = new ArrayList<Token>();
        
        try {
            //Defines file to read
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String sCurrentLine=br.readLine();
            boolean hitK=false;
            
            //Finds line containing "K:" (Other headers handled in HeaerReader)
            while ((sCurrentLine != null) && (hitK==false)) {
                if (sCurrentLine.startsWith("K:")) {
                    hitK=true;
                }
                sCurrentLine=br.readLine();
            }
            //Time to tokenize the rest of the following strings!
            while ((sCurrentLine != null) && (hitK==true)) {
                //lex each line, add to token list
                tokenList.addAll(lineLexer(sCurrentLine));
                sCurrentLine=br.readLine();
            }
            
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Could not read path.");
        }
        return tokenList;
    }
    
    


    /**
     * lineLexer: Lexes inputted string
     * 
     * @param String to be lexed
     * @return ArrayList of Tokens
     * @throws IllegalArgumentException if not a valid type of token
     */
    private ArrayList<Token> lineLexer(String currentLine) {
        // create output
        ArrayList<Token> tokenList = new ArrayList<Token>();
        // current token
        StringBuilder currentString = new StringBuilder();
        
        // iterate through string for patterns
        for (Character c : currentLine.toCharArray()) {
            //Comment, then break.
            if (c=='%') {
                break;
            }
            
            if (c=='V') {
                if (currentLine.charAt(1)==':') {
                    //save everything following as voice token. 
                    tokenList.add(new Token(Token.Type.VOICE, currentLine.substring(2).trim()));
                    break;
                }
            }
            
            StringBuilder appendedString = new StringBuilder(currentString);
            appendedString.append(c);
            
            // if currentString + c is valid, store it as currentString.
            if (!typeFinder(appendedString).equals(Token.Type.NULL)) {
                currentString = appendedString;
            }
            // else, append currentString as token, reset
            else {
                // if currentString is not blank or whitespace
                if (! currentString.toString().matches("\\s*")) {
                    //check for illegal add
                    if (typeFinder(currentString).equals(Token.Type.NULL)) {
                        throw new IllegalArgumentException ("Illegal input: "+currentString+", cannot tokenize.  Could not add as: "+appendedString);
                    }
                    //update time.
                    if (typeFinder(currentString).equals(Token.Type.TUPLET_SPEC)) {
                        this.myTuplet=Integer.parseInt(currentString.substring(1));
                        //update LCM in case we don't have a time token later...
                        //slightly awkward, but will fix if we have time
                        this.myLCM = lcm(this.myTuplet, this.myLCM);
                        //we don't set it back to 0
                        //again, slightly awkward but it works!
                        

                    }
                    //add tokens
                    if (typeFinder(currentString).equals(Token.Type.TIME)) {
                        updateLCM(currentString);
                        tokenList.add( new Token(Token.Type.TIME, fractionChange(currentString)) );
                    }
                    else {
                        tokenList.add( new Token(typeFinder(currentString), currentString.toString()) );
                    }
                    // reset currentString
                    currentString.delete(0, currentString.length());  
                }
                // start off new character
                if (!Character.isWhitespace(c)) {
                    currentString.append(c);
                }
                
            }
        }
        
        // if currentString remains at end of for loop, add.
        if (!currentString.toString().isEmpty()) {
            if (!Lexer.typeFinder(currentString).equals(Token.Type.NULL)) {
                //update time.
                if (typeFinder(currentString).equals(Token.Type.TUPLET_SPEC)) {
                    this.myTuplet=Integer.parseInt(currentString.substring(1));
                }
                //add tokens
                if (typeFinder(currentString).equals(Token.Type.TIME)) {
                    updateLCM(currentString);
                    tokenList.add( new Token(Token.Type.TIME, fractionChange(currentString)) );
                }
                else {
                    tokenList.add( new Token(typeFinder(currentString), currentString.toString()) );
                }
            }
            else {
                throw new IllegalArgumentException ("Invalid numeric input: "+currentString+".");
            }
        }
        return tokenList;
    }
    
    /**
     * Changes fraction into a decimal
     * 
     * Calls private fractionChangeCall method.
     * 
     * @param fraction (in valid fraction format) or number, as a String
     *        (denominators must be integers greater than 0)
     * @return decimal, as a String
     */
    public static String fractionChange(StringBuilder myFracInput) {
        return fractionChangeCall(myFracInput);
    }
    
    /**
     * Changes fraction into a decimal
     * 
     * @param fraction (in valid fraction format) or number, as a String
     *        (denominators must be integers greater than 0)
     * @return decimal, as a String
     */
    private static String fractionChangeCall(StringBuilder myFracInput) {
        StringBuilder myNum = new StringBuilder();
        String myFrac = myFracInput.toString();
        int numer=1;
        int denomer=2;
        for (int n=0; n < myFrac.length(); n++) {
            // if hit divide, then hold.
            if (myFrac.charAt(n)=='/') {
                //myNum is numerator
                if(n < myFrac.length() -1){
                    String denom = myFrac.substring(n+1);
                    denomer = Integer.parseInt(denom);
                }
                if(!myNum.toString().isEmpty()){
                    numer = Integer.parseInt(myNum.toString());
                }
                return (""+(double)numer/denomer);
            }
            if (Character.isDigit(myFrac.charAt(n))) {
                myNum.append(myFrac.charAt(n));
            }
        }
        return myFracInput.toString();
    }


    /**
     * Helper method for updateLCM to track number of ticks.
     * 
     * Updates the number of ticks to fit globalTicks.
     * @param Time string
     */
    private void updateLCM(StringBuilder timeString) {
        //check if tuplet
        int t=1;
        if (this.myTuplet!=0) {
            t=this.myTuplet;
            
        }
        //iterate through timeString.
        for (int i=0; i<timeString.length(); i++) {
            if (timeString.charAt(i)=='/') {
                if (i < timeString.length()-1) {
                    int denom=Integer.parseInt(timeString.substring(i+1));
                    this.myLCM=lcm(this.myLCM,denom*t);
                }
                else {
                    this.myLCM=lcm(this.myLCM,2*t);
                }
            }
        }
        //set myTuplet back to 0 for next tuplet token
        this.myTuplet=0;  
    }


    /** 
     * Computes the type of the string.  
     * 
     * Uses the Matcher to find if it's a match to the given pattern. 
     * 
     * @param StringBuilder, which we want to convert to a Token.Type
     * @returns Token.Type of the inputted string.
     */
    private static Token.Type typeFinder(StringBuilder inpString) {
        String s = inpString.toString();
        
        if (s.matches("[a-gA-G]")) {
            return Token.Type.BASENOTE;
        }
        if (s.matches("z")) {
            return Token.Type.REST;
        }
        //different times
        if (s.matches("(/|(\\d*/\\d*))|[0-9]+")) {
            return Token.Type.TIME;
        }
        // Returns true if accidental
        if (s.matches("\\^|\\^\\^|\\_|\\_\\_|\\=")) {
            return Token.Type.ACCIDENTAL;
        }
        // Returns true if octave
        if (s.matches("\\'+|\\,+")) {
            return Token.Type.OCTAVE;
        }
        // tuplet spec
        if (s.matches("\\([234]")) {
            return Token.Type.TUPLET_SPEC;
        }
        // Barline Sets
        if (s.matches("\\|")) {
            return Token.Type.MEASURE_BAR;
        }
        if (s.matches("\\|\\|")) {
            return Token.Type.DOUBLE_BAR;
        }
        if (s.matches("\\|\\:")) {
            return Token.Type.START_REPEAT_BAR;
        }
        if (s.matches("\\:\\|")) {
            return Token.Type.END_REPEAT_BAR;
        }
        
        if (s.matches("\\[\\|")) {
            return Token.Type.START_BAR;
        }
        if (s.matches("\\|\\]")) {
            return Token.Type.END_BAR;
        }
        if (s.matches("\\[1")) {
            return Token.Type.NTHREPEAT1;
        }
        if (s.matches("\\[2")) {
            return Token.Type.NTHREPEAT2;
        }
        if (s.matches("\\[")) {
            return Token.Type.OPEN_CHORD;
        }
        if (s.matches("\\]")) {
            return Token.Type.CLOSE_CHORD;
        }
        return Token.Type.NULL;
    }
    
    /**
     * LCM Finder
     * 
     * Computes LCM of two integers
     * Doesn't need to handle numbers higher than 100
     * since we do not expect our music to go any faster
     * than that.
     * 
     * @param int x
     * @param int y
     * @return LCM of x and y
     */
    public static int lcm(int x,int y){
        int i=1;
        if (y==0) {
            return 0;
        }
        while(true){
            if (((x*i)%y)==0) {
                 return x*i;
            }
            i++;
        }
    }

}
