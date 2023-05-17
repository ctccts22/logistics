package logistics.user.service;

import logistics.user.dto.UserDTO;
import logistics.user.entity.Role;
import logistics.user.entity.User;
import logistics.user.repository.RoleRepository;
import logistics.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int idCheck(String username) {
        boolean exists = userRepository.existsByUsername(username);
        return exists ? 1 : 0; // username이 존재하면 return 1
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        Role role = roleRepository.findByRoleName(Role.RoleName.valueOf(userDTO.getRoleName()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        User user = new User();
        // DTO에서 사용자 엔티티로 필드 매핑
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);
        user.setIsDeleted(userDTO.getIsDeleted());
        user.setDeleteDate(userDTO.getDeleteDate());
        user.setCreationDate(userDTO.getCreationDate());

        userRepository.save(user);
        return userDTO;
    }

}
