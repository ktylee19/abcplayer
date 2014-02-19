package player;

import java.util.List;

import org.junit.Test;

import sound.Lexer;
import sound.Parser;
import abstractSyntaxTree.Voice;

/**
 * test all parts of the program to make sure the right notes are played by SequencePlayer
 * @category no_didit
 * @author meena
 *
 */
public class IntegrationTest {
    @Test
    public void testValidOneVoice(){
        Main.play("sample_abc/test_abc/valid_onevoice.abc");
        System.exit(0);
    }
    @Test
    public void testNyanCat(){
        Main.play("sample_abc/nyan_cat_twovoices.abc");
        System.exit(0);
    }
    @Test
    public void testToyStory(){
        Main.play("sample_abc/toy_story.abc");
        System.exit(0);
        
    }
    
    @Test
    public void testPrelude(){
        Main.play("sample_abc/prelude.abc");
        System.exit(0);
    }
    
    @Test
    public void testHatSong(){
        Main.play("sample_abc/hat_song.abc");
        System.exit(0);
    }
    
    @Test
    public void testFurElise(){
        Main.play("sample_abc/fur_elise.abc");
        System.exit(0);
    }
    
    @Test
    public void testInvention(){
        Main.play("sample_abc/invention.abc");
        System.exit(0);
    }
    
}
