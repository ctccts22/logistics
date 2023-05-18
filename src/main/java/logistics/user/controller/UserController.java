package logistics.user.controller;

import logistics.user.dto.UserDTO;
import logistics.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "users/login";
    }

    //아이디 중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("username") String username) {
        log.info("username 확인 : {}", username);
        int cnt = userService.idCheck(username);
        return cnt;
    }
    /**
     * 회원가입
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return "redirect:/users/login";
        } catch (RuntimeException ex) {
            return "error";
        }
    }


}
