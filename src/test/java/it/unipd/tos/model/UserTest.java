////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

    @Test
    public void getId_positive_Test() {
        User u=new User(1,"ciao","lol",2);
        assertEquals(1, u.getId());
    }
    
    @Test
    public void getId_negative_Test() {
        User u=new User(-1,"ciao","lol",2);
        assertEquals(0, u.getId());
    }
    
    @Test
    public void getName_Test() {
        User u=new User(1,"ciao","lol",2);
        assertEquals("ciao", u.getName());
    }
    @Test
    public void getSurname_Test() {
        User u=new User(1,"ciao","lol",2);
        assertEquals("lol", u.getSurname());
    }
    @Test
    public void getAge_positive_Test() {
        User u=new User(1,"ciao","lol",2);
        assertEquals(2, u.getAge());
    }
    @Test
    public void getAge_negative_Test() {
        User u=new User(1,"ciao","lol",-2);
        assertEquals(0, u.getAge());
    }
}
