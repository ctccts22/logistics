package logistics.user.service;

import logistics.user.dto.UserDTO;
import logistics.user.entity.Role;
import logistics.user.entity.User;
import logistics.user.repository.RoleRepository;
import logistics.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int idCheck(String username) {
        boolean exists = userRepository.existsByUsername(username);
        return exists ? 1 : 0; // username이 존재하면 return 1
    }

    @Override
    public void registerUser(UserDTO userDTO) {

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
    }

    @Override
    @Transactional
    public void updateUserView(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Role role = roleRepository.findByRoleName(Role.RoleName.valueOf(userDTO.getRoleName()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);
        user.setIsDeleted(userDTO.getIsDeleted());
        user.setDeleteDate(userDTO.getDeleteDate());

        userRepository.save(user);
    }

}
