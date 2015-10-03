/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import errorMessage.CodeError;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import panda.prod.application.PandaProdApplication;

/**
 *
 * @author Lucas
 */
public class MySQLQueryTest {

    public MySQLQueryTest() {
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
     * Test of createUser method, of class MySQLQuery.
     */
    /*@Test
    public void testCreateUser() {
        System.out.println("create user");
        User user = new User("panda.roux.corp@gmail.com", "panda.developpeur@gmail.com", "pandaProd");
        int expResult = CodeError.SUCESS;
        int result = MySQLQuery.createUser(user);
        assertEquals(expResult, result);
        System.out.println("create user sucess");
        
        System.out.println("create same user");
        expResult = CodeError.STATEMENT_EXECUTE_FAIL;
        result = MySQLQuery.createUser(user);
        assertEquals(expResult, result);
        System.out.println("create same user sucess");
    
    }*/
    
/*    @Test   
    public void testCreateSameUser(){
        System.out.println("create same user");
        User user = new User("panda.roux.corp@gmail.com", "panda.developpeur@gmail.com", "pandaProd");
        int expResult = CodeError.STATEMENT_INTEGRITY_CONSTRAINT_VIOLATION;
        MySQLQuery.createUser(user);
        int result = MySQLQuery.createUser(user);
        assertEquals(expResult, result);
    
    }*/

    /**
     * Test of connectUser method, of class MySQLQuery.
     */
    @Test
    public void testConnectUser() {
        System.out.println("connectUser");
        User user = PandaProdApplication.getApplication().getUser();
        user.setLoginAdressMail("panda.roux.corp@gmail.com");
        user.setPassword("pandaProd");
        int expResult = CodeError.SUCESS;
        int result = MySQLQuery.connectUser(user);
        assertEquals(expResult, result);
        
        System.out.println("wrong password");
        user = PandaProdApplication.getApplication().getUser();
        user.setLoginAdressMail("panda.roux.corp@gmail.com");
        user.setPassword("panda");
        expResult = CodeError.FAILLURE;
        result = MySQLQuery.connectUser(user);
        assertEquals(expResult, result);
        
        System.out.println("wrong login");
        user = PandaProdApplication.getApplication().getUser();
        user.setLoginAdressMail("panda.corp@gmail.com");
        user.setPassword("pandaProd");
        expResult = CodeError.FAILLURE;
        result = MySQLQuery.connectUser(user);
        assertEquals(expResult, result);
        
        

    }
    
}
