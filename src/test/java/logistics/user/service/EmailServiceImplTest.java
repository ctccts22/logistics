package logistics.user.service;

import logistics.user.entity.User;
import logistics.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JavaMailSenderImpl mailSender;

    @Test
    public void testSendTemporaryPassword() {
        String email = "test@example.com";
        String username = "test";
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setIsDeleted("N");

        when(userRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(user));
        when(passwordEncoder.encode(anyString())).thenReturn("encryptedPassword");

        emailService.sendTemporaryPassword(username, email);

        ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(argumentCaptor.capture());

        SimpleMailMessage messageSent = argumentCaptor.getValue();

        assertEquals("Temporary Password", messageSent.getSubject());
        assertEquals(email, Objects.requireNonNull(messageSent.getTo())[0]);
    }
}
