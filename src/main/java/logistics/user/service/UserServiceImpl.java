package logistics.user.service;

import logistics.user.dto.UserDTO;

public interface UserServiceImpl {

    int idCheck(String username);
    UserDTO registerUser(UserDTO userDTO);

}
