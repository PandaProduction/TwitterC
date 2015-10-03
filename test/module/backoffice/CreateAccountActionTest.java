/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class CreateAccountActionTest {

    public CreateAccountActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class CreateAccountAction.
     */
    @Test
    public void testExecute() {
        System.out.println("execute CreateCSAccountAction valide mail");

        CreateAccountAction instance = new CreateAccountAction();
         boolean expResult = true;
        boolean result = instance.execute("panda.roux.corp@gmail.com", "girardin.lucas@gmail.com", "pandaProd");
        assertEquals(expResult, result);

        System.out.println("execute CreateCSAccountAction unvalide mail 1");

        expResult = false;
        result = instance.execute("pandarouxcorp@gmail", "g@gmailcom", "pandaProd");
        assertEquals(expResult, result);
        result = instance.execute("girardin.lucas@gmail.com", "gmail.com", "pandaProd");
        assertEquals(expResult, result);
        result = instance.execute("girardin.lucas@gmail.com", "girardinlucas@gmailcom", "pandaProd");
        assertEquals(expResult, result);
        result = instance.execute("girardin.lucas@gmail.com", "girardin.lucasgmail.com", "pandaProd");
        assertEquals(expResult, result);
        

        
    }

}
