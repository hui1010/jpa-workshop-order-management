package se.lexicon.huiyi.jpaworkshopordermanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.AppUser;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    AppUser testObject;

    @Autowired
    AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
        testObject = appUserRepository.save(new AppUser("test", "Tester", "test@123.com"));
        appUserRepository.save(new AppUser("tom", "Tom", "tom@123.com"));
        appUserRepository.save(new AppUser("jerry", "Jerry", "jerry@123.com"));

    }

    @Test
    void successfully_created() {
        assertEquals(3, appUserRepository.findAll().size());//go to the AppUserRepository and override the findAll()
        assertFalse(appUserRepository.findAll().isEmpty());
        assertTrue(appUserRepository.findAll().contains(testObject));
    }

    @Test
    void successful_findByEmailIgnoreCase() {
        //Arrange
        String searchEmail = testObject.getEmail().toUpperCase();

        //Act
        Optional<AppUser> result = appUserRepository.findByEmailIgnoreCase(searchEmail);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(testObject.getFirstName(), result.get().getFirstName());
        assertEquals(testObject, result.get());
    }

    @Test
    void failed_findByEmailIgnoreCase() {
        //Arrange
        String email = "test@123.se";//was .com before

        //Act
        Optional<AppUser> result = appUserRepository.findByEmailIgnoreCase(email);

        //Assert
        assertTrue(result.isEmpty());
        assertEquals(Optional.empty(), result);
    }
}