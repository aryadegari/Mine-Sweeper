package test;

import org.testng.annotations.Test;
import ui.GUI;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class GUITest {

    @Test
    public void GUITest(){
        new Thread(new GUI()).start();
    }
}
