package logistics.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String roleName;
    private String email;
    private String phone;
    private String isDeleted;
    private LocalDateTime deleteDate;
    private LocalDateTime creationDate;

}
