package sound;
/**
 * A token is a lexical item that the parser uses.
 * 
 * @author Katie
 */
public class Token {
    /**
     * All the types of tokens that can be made.
     * 
     * Comments are not included in Token Types since they will be 
     * disregarded in the lexer. 
     * 
     */
    public static enum Type {
        BASENOTE, //basic note
        REST, // basic rest
        TIME, // time of note cluster
        
        ACCIDENTAL, //sharp,flat,natural
        OCTAVE, //octave , ' 
        
        MEASURE_BAR, //measure divider |
        START_REPEAT_BAR, //measure repeat |: 
        END_REPEAT_BAR, //measure repeat :|
        DOUBLE_BAR, //not sure. ||  
        END_BAR, //ultimate end |]
        START_BAR, //ultimate start [|
        
        NTHREPEAT1, //[1 or 
        NTHREPEAT2, //[2
        
        TUPLET_SPEC, //defined as "(" [234]
        OPEN_CHORD, // [
        CLOSE_CHORD, // ]
        
        VOICE, //Voice Token
        
        NULL
    }
    
    public final Type type;
    public final String text;
    
    /**
     * Constructs a Token. 
     * The correct type input must be given for the corresponding text input 
     * 
     * @param typeInput  Token type associated with the token 
     * @param textInput  String value associated with the token
     */
    public Token (Type typeInput, String textInput) {
        this.type = typeInput;
        this.text = textInput;
    }
    
    /**
     * String output for a Token
     * 
     * @returns String stating token's type and text values
     */
    public String toString() {
        return "Token<" + type + ":" + text + ">";
    }
    
    /**
     * Equals method for Tokens
     * 
     * @returns equals 
     */
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Token) {
            Token that = (Token) o;
            if (this.type.equals(that.type) && this.text.equals(that.text)) {
                return true;
            }
        }
        return false;
    }

}
