package sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * KeySig map creates a key representation for a given key signature (based on info in the header)
 * While parsing: every time a measure bar token is seen, call KeySig.clearAccidental()
 * every time an accidental token is seen, call KeySig.addAccidental(note, value)
 * every time a basenote token is seen, call KeySig.getNoteValue(token.text)
 * @author meena
 *
 */

public class KeySigMap{
    private HashMap<String, Integer > accidental = new HashMap<String, Integer>();
    private HashMap<String, Integer> keySig = new HashMap<String, Integer>();
    private HashMap<String, int[]> allKeys = new HashMap<String, int[]>();
   
    
    /**
     * Initialize KeySigMap based on the key provided by the header reader 
     * @param note
     */
    public KeySigMap(String note){
        getAllKeys();
        if(allKeys.containsKey(note)){
            int[] noteKey = allKeys.get(note);
            String[] notes = {"C", "D", "E", "F", "G", "A", "B"};
            for(int i = 0; i < noteKey.length; i++){
                this.keySig.put(notes[i], noteKey[i]);
            }
        }
        
        else{
            throw new IllegalArgumentException("Did not provide a valid key signature");
        }
        
    }
    
    
    /**
     * called every time an accidental token is seen 
     * adds an accidental note to the accidentalKey, overrides the note if its already in accidentalKey
     * @param noteAndOctave string representation of a note + octave if any, example: "c'" or ",,e"
     * @param value -2, -1, 0, 1, or 2 (2 and -2 b/c we can have double sharps or double flats)
     */
    public void addAccidental(String noteAndOctave, int value){
        this.accidental.put(noteAndOctave, value);     
        
    }
    
    /**
     * method is called every time a measure bar token is seen, to clear the accidental keymap
     */
    public void clearAccidental(){
        this.accidental.clear();
    }
    
    /**
     * checks the accidental key and then the regular key signature to get the accidental value of the note
     * @param note a string representation of the note with octave info, example: "c'" or ",,e" 
     * @return -2, -1, 0, 1, or 2 depending on sharp/flat/natural value of note
     */
    public int getNoteValue(String note){
        if(this.accidental.containsKey(note)){
            return this.accidental.get(note);
        }
        String letterNote = "";
        for(int i = 0; i < note.length(); i++){
            if(note.charAt(i) != ',' && note.charAt(i) != '\''){
                letterNote += note.charAt(i);
            }
        }
        try{
            int accidental = this.keySig.get(letterNote.toUpperCase());
            return accidental;
        }
        catch(Exception NullPointerException){
            throw new IllegalArgumentException("Character '"+ letterNote+"' is invalid");
        }
    }
    
    private void getAllKeys(){
        List<int[]> total = new ArrayList<int[]>();
        //each array lists out accidental value for C, D, E...., A, B
        int[] cMajor = {0, 0, 0, 0, 0, 0, 0 };
        total.add(cMajor);
        int[] gMajor = {0, 0, 0, 1, 0, 0, 0};
        total.add(gMajor);
        int[] dMajor = {1, 0, 0, 1, 0, 0, 0};
        total.add(dMajor);
        int[] aMajor = {1, 0, 0, 1, 1, 0, 0};
        total.add(aMajor);
        int[] eMajor = {1, 1, 0, 1, 1, 0, 0};
        total.add(eMajor);
        int[] bMajor = {1, 1, 0, 1, 1, 1, 0};
        total.add(bMajor);
        int[] fSharpMajor = {1, 1, 1, 1, 1, 1, 0};
        total.add(fSharpMajor);
        int[] cSharpMajor = {1, 1, 1, 1, 1, 1, 0};
        total.add(cSharpMajor);
        
        String[] totalKeys = {"C", "G", "D", "A", "E", "B", "F#", "C#"};
        for(int i  = 0 ; i < total.size(); i++){
            this.allKeys.put(totalKeys[i], total.get(i));
        }
    
        //get flat keys
        //C D E F G A B
        int[] fMajor = {0, 0, 0, 0, 0, 0, -1};
        this.allKeys.put("F", fMajor);
        int[] bFlatMajor = {0, 0, -1, 0, 0, 0, -1};
        this.allKeys.put("Bb", bFlatMajor);
        int[] eFlatMajor = {0, 0, -1, 0, 0, -1, -1};
        this.allKeys.put("Eb", eFlatMajor);
        int[] aFlatMajor = {0, -1, -1, 0, 0, -1, -1};
        this.allKeys.put("Ab", aFlatMajor);
        int[] dFlatMajor = {0, -1, -1, 0, -1, -1, -1};
        this.allKeys.put("Db", dFlatMajor);
        int[] gFlatMajor = {-1, -1, -1, 0, -1, -1, -1};
        this.allKeys.put("Gb", gFlatMajor);
        int[] cFlatMajor = {-1, -1, -1, -1, -1, -1, -1};
        
        //put in minor keys; have same sharp/flat makeup as other major keys
        this.allKeys.put("Am", cMajor);
        this.allKeys.put("Em",gMajor);
        this.allKeys.put("Bm", dMajor);
        this.allKeys.put("F#m", aMajor);
        this.allKeys.put("C#m", eMajor);
        this.allKeys.put("G#m", bMajor);
        this.allKeys.put("D#m", fSharpMajor);
        this.allKeys.put("A#m", cSharpMajor);
        this.allKeys.put("Dm", fMajor);
        this.allKeys.put("Gm", bFlatMajor);
        this.allKeys.put("Cm", eFlatMajor);
        this.allKeys.put("Fm", aFlatMajor);
        this.allKeys.put("Bbm", dFlatMajor);
        this.allKeys.put("Ebm", gFlatMajor);
        this.allKeys.put("Abm", cFlatMajor);
        
        }
        
  
}
