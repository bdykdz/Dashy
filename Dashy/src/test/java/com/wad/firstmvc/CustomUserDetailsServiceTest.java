package com.wad.firstmvc;

import com.wad.firstmvc.repositories.UserRepository;
import com.wad.firstmvc.services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    public void loadUserByUsername_UserNotFound_ThrowsException() {
        // Given
        String nonexistentUsername = "doesNotExist";
        when(userRepository.findByUsername(nonexistentUsername)).thenReturn(null);

        // When + Then
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(nonexistentUsername),
                "Expected loadUserByUsername to throw, but it didn't");

        // Verify
        verify(userRepository).findByUsername(nonexistentUsername);
    }
}
