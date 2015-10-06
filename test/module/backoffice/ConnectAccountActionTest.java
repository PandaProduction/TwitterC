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
public class ConnectAccountActionTest {

    public ConnectAccountActionTest() {
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
     * Test of execute method, of class ConnectAccountAction.
     */
    @Test
    public void testExecute() {
        System.out.println("execute ConnectAccountAction");

        ConnectAccountAction instance = new ConnectAccountAction();
        boolean expResult = true;
        boolean result = instance.execute("panda.roux.corp@gmail.com", "pandaProd");
        assertEquals(expResult, result);

        /*System.out.println("wrong login");
         expResult = false;
         result = instance.execute("panda.corp@gmail.com", "pandaProd");
         assertEquals(expResult, result);
        
         System.out.println("wrong password");
         expResult = false;
         result = instance.execute("panda.roux.corp@gmail.com", "panda");
         assertEquals(expResult, result);
         */
    }

}
