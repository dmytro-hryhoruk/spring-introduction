package spring.intro.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public void inject() {
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setLogin(String.format("userId%d@ma.ua", i + 1));
            user.setPassword("123");
            userService.add(user);
        }
    }

    @GetMapping("/get/{user_id}")
    public UserResponseDto get(@PathVariable(name = "user_id") Long id) {
        User user = userService.get(id);
        return assembleUserResponseDto(user);
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::assembleUserResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto assembleUserResponseDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
