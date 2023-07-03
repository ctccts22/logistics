package logistics.user.service;

public interface EmailService {

    void sendEmailProtocol(String to, String subject, String body);
    String generateRandomPassword();
    void sendTemporaryPassword(String username, String email);
}
