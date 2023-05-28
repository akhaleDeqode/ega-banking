package com.ega.banking.service;

import com.ega.banking.constants.TestConstants;
import com.ega.banking.entity.User;
import com.ega.banking.error.InvalidUserIdException;
import com.ega.banking.repository.AccountRepository;
import com.ega.banking.repository.UserRepository;
import com.ega.banking.util.AccountUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountUtil accountUtil;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidUser_ShouldReturnRegisteredUser() {
        // Arrange
        User user = User.builder()
                .password(TestConstants.USER_PASSWORD)
                .build();

        when(accountRepository.getAllAccountId()).thenReturn(new ArrayList<>());
        when(accountUtil.generateAccountNumber(any())).thenReturn(TestConstants.ACCOUNT_NUMBER);
        when(bCryptPasswordEncoder.encode(any())).thenReturn(TestConstants.ENCRYPTED_USER_PASSWORD);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.registerUser(user);

        verify(accountRepository, times(1)).getAllAccountId();
        verify(accountUtil, times(1)).generateAccountNumber(any());
        verify(userRepository, times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder, times(1)).encode(any());
    }

    @Test
    void getAllUsers_NoUsersPresent_ShouldReturnEmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertNotNull(users);
        assertTrue(users.isEmpty());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ExistingUserId_ShouldReturnUser() {
        // Arrange
        User user = User.builder()
                .build();
        when(userRepository.findById(TestConstants.ACCOUNT_ID)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.getUserById(TestConstants.ACCOUNT_ID);

        // Assert
        assertNotNull(foundUser);
        assertEquals(user, foundUser);

        verify(userRepository, times(1)).findById(TestConstants.ACCOUNT_ID);
    }

    @Test
    void getUserById_NonExistingUserId_ShouldThrowInvalidUserIdException() {
        // Arrange
        when(userRepository.findById(TestConstants.ACCOUNT_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidUserIdException.class, () -> userService.getUserById(TestConstants.ACCOUNT_ID));

        verify(userRepository, times(1)).findById(TestConstants.ACCOUNT_ID);
    }
}
