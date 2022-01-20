package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import com.userapi.support.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenTryingToGetANonExistentUserId() throws UserNotFoundException {
        int userId = 1;

        Mockito.when(userRepository.findById(userId)).thenReturn(null);

        userService.findById(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenTryingToDeleteANonExistentUserId() throws UserNotFoundException {
        int userId = 1;

        Mockito.when(userRepository.findById(userId)).thenReturn(null);

        userService.delete(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenTryingToGetAnUserByLastNameAndResultIsAnEmptySet() throws UserNotFoundException {
        String lastName = "example";

        Mockito.when(userRepository.findByLastNameIgnoreCase(lastName)).thenReturn(new HashSet<>());

        userService.findByLastName(lastName);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenTryingToGetAnUserByFirstNameAndResultIsAnEmptySet() throws UserNotFoundException {
        String firstName = "example";

        Mockito.when(userRepository.findByFirstNameIgnoreCase(firstName)).thenReturn(new HashSet<>());

        userService.findByFirstName(firstName);
    }

    @Test
    public void shouldReturnAnUserWhenTryToGetAnExistentUserById() throws UserNotFoundException {
        int userId = 1;

        User user = new User();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");

        Mockito.when(userRepository.findById(userId)).thenReturn(user);

        User existingUser = userService.findById(userId);

        Assert.assertEquals(user.getId(), existingUser.getId());
        Assert.assertEquals(user.getFirstName(), existingUser.getFirstName());
        Assert.assertEquals(user.getLastName(), existingUser.getLastName());
    }

    @Test
    public void shouldReturnAnUserWhenTryToGetAnExistentUserByLastName() throws UserNotFoundException {

        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstName("firstName");
        user.setLastName("lastName");

        Set<User> userSet = new HashSet<>();
        userSet.add(user);

        Mockito.when(userRepository.findByLastNameIgnoreCase(user.getLastName())).thenReturn(userSet);

        Set<User> existingUsersSet = userService.findByLastName(user.getLastName());

        Assert.assertEquals(user.getId(), existingUsersSet.iterator().next().getId());
        Assert.assertEquals(user.getFirstName(), existingUsersSet.iterator().next().getFirstName());
        Assert.assertEquals(user.getLastName(), existingUsersSet.iterator().next().getLastName());
    }

    @Test
    public void shouldReturnAnUserWhenTryToGetAnExistentUserByFirstName() throws UserNotFoundException {

        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstName("firstName");
        user.setLastName("lastName");

        Set<User> userSet = new HashSet<>();
        userSet.add(user);

        Mockito.when(userRepository.findByFirstNameIgnoreCase(user.getFirstName())).thenReturn(userSet);

        Set<User> existingUsersSet = userService.findByFirstName(user.getFirstName());

        Assert.assertEquals(user.getId(), existingUsersSet.iterator().next().getId());
        Assert.assertEquals(user.getFirstName(), existingUsersSet.iterator().next().getFirstName());
        Assert.assertEquals(user.getLastName(), existingUsersSet.iterator().next().getLastName());
    }
}
