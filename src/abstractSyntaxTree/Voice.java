package abstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

import sound.SequencePlayer;

/**
 * the object that takes in Elements associated with each voice part, and stores it as a List
 * mutable
 * can call .repeat() or .alternateEndings() in the Parser.
 * @author meena
 *
 */
public class Voice {
    final String voiceString;
    private List<Element> voiceList = new ArrayList<Element>();
    
    //these are set to negative one if they are not seen at all
    private int openRepeat = -1;
    private int nthRepeatOne = -1;
    private boolean isOpen;
    
    //this is updated every time a new Element is added
    private int globalTicks = 0;
    
    /**
     * initializes a Voice based on its String representation 
     * @param voiceRep
     */
    public Voice(String voiceRep){
        this.voiceString = voiceRep;
        this.isOpen = false;
    }
    
    /**
     * Adds Element to the Voice list, updates global tick
     * @param e
     */
    public void addElement(Element e){
        this.voiceList.add(e);
        this.globalTicks += e.getNumTicks();
    }
    
    public void setBooleanOpen() throws IllegalArgumentException{
        if(this.isOpen){
            //System.out.println(this.getFullVoiceString());
            throw new IllegalArgumentException("no closed repeat after open repeat");
        }
        this.isOpen = true;
    }
    /**
     * getOpenRepeat() returns value of openRepeat
     */
    public int getOpenRepeat(){
      return this.openRepeat;
    }
    
    /**
     * setOpenRepeat() is called when an open bar token is seen
     * throws a exception if an open repeat is seen twice in a row 
     */
    public void setOpenRepeat(){
        /*if(this.isOpen){
            throw new IllegalArgumentException("no closed repeat after open repeat");
        }
        this.isOpen = true;*/
      this.openRepeat = this.voiceList.size();
    }
    
    /**
     * returns value of nthrepeatone
     */
    
    public int getNthRepeatOne(){
        return this.nthRepeatOne;
    }
    
    /**
     * called when an NthRepeat1 token is seen
     */
    
    public void setNthRepeatOne(){
        this.nthRepeatOne = this.voiceList.size();
    } 
    
    
    /**
     * this is called when an NthRepeat2 token is seen, after setNthRepeatTwo() is called
     * voiceList adds the repeated segment until nthRepeat1 index.
     */
    
    public void alternateEnding2(){
        this.isOpen = false;
        if(this.openRepeat == -1){
            this.openRepeat = 0;
        }
        List<Element> repeated = this.voiceList.subList(this.openRepeat, this.nthRepeatOne);
        for(Element e: repeated){
            this.globalTicks += e.getNumTicks();
        }
        this.voiceList.addAll(repeated);

        
        
    }
    
    /**
     * repeat() is called when a closed bar token is seen
     * basically copies the repeated elements to the end of the list, preserving their order
     */
    public void repeat(){
        this.isOpen = false;
        if(this.openRepeat == -1){
            this.openRepeat = 0;
        }
        
        List<Element> copy = this.voiceList.subList(this.openRepeat, this.voiceList.size());
        int sum = 0;
        for(Element e : copy){
            sum += e.getNumTicks();
        }
        this.voiceList.addAll(copy);
        this.globalTicks += sum;
        
      
        
        }
    
    public List<Element> getElements(){
        return this.voiceList;
    }
    
   
    
    /**
     * this used to make sure each voice part has the same number of total ticks
     * @return int total number of ticks in a voice part
     */
    public int getGlobalTicks(){
        return this.globalTicks;
    }
    
    /**
     * method returns a string representation of all of the Elements in the voice piece
     * useful for debugging / writing test cases
     * @return String with all Elements, separated by "\n"
     */
    
    public String getFullVoiceString(){
        String fullVoice = "";
        for(Element e : this.voiceList){
            fullVoice += e.toString();
            fullVoice += "\n";
        }
        return fullVoice;
    }
    
    /** 
     * toString method
     * @return the name of the voice part that it was initialized with
     */
    @Override
    public String toString(){
        return this.voiceString;
    }
    
    /**
     * evaluates a voice part by evaluating each of the notes in the voice part individually and 
     * updating the total numTicks
     * @param totalTicks
     * @param player
     */
    public void eval(int totalTicks, SequencePlayer player){
        for(int i = 0; i < this.voiceList.size(); i++){
            this.voiceList.get(i).eval(totalTicks, player);
            totalTicks += this.voiceList.get(i).getNumTicks();
        }
    }    
    
}
