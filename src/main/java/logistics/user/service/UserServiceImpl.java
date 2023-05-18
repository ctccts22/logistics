package logistics.user.service;

import logistics.user.dto.UserDTO;

public interface UserServiceImpl {

    int idCheck(String username);
    void registerUser(UserDTO userDTO);
    void updateUserView(UserDTO userDTO);

}
