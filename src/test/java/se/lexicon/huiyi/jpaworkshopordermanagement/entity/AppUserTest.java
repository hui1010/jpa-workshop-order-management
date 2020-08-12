package se.lexicon.huiyi.jpaworkshopordermanagement.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    AppUser testObject;

    @BeforeEach
    void setUp() {
        testObject = new AppUser("test", "Tester", "test@123.com");
    }

    @Test
    void successfully_created() {
        assertNotNull(testObject);
        assertTrue(testObject.getId()==0);
        assertEquals("test", testObject.getFirstName());
        assertEquals("Tester", testObject.getLastName());
        assertEquals("test@123.com", testObject.getEmail());
    }

    @Test
    void testEquals() {
        AppUser copyObject = new AppUser("test", "Tester", "test@123.com");
        assertEquals(testObject, copyObject);
        assertTrue(testObject.equals(copyObject));
    }

    @Test
    void testToString() {
        String string = testObject.toString();
        assertTrue(string.contains(Integer.toString(testObject.getId())));
        assertTrue(string.contains(testObject.getFirstName()));
        assertTrue(string.contains(testObject.getLastName()));
        assertTrue(string.contains(testObject.getEmail()));

    }
}