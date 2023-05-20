package logistics.user.controller;

import logistics.user.dto.UserDTO;
import logistics.user.entity.User;
import logistics.user.repository.UserRepository;
import logistics.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/userView")
    public String showUserViewForm(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("user", users);
        model.addAttribute("title", "회원관리");
        return "admin/users_view";
    }

    @PostMapping("/updateUserView")
    public String updateUser(@ModelAttribute("user") UserDTO userDTO) {
        try {
            userService.updateUserView(userDTO);
            return "redirect:/admin/userView";
        } catch (RuntimeException ex) {
            return "error";
        }
    }
}
