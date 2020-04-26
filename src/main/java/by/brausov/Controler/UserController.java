package by.brausov.Controler;

import by.brausov.coders.Encryptor;
import by.brausov.model.entities.Role;
import by.brausov.model.entities.User;
import by.brausov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private String alert = "";

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        user.setPassword(Encryptor.encrypt(user.getPassword()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Role role = new Role();
        role.setId(1);
        user.setRole(role);
        userService.add(user);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView checkUsers(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkUser(user)){
            ChatControler.setUser(userService.getByLoginAndPassword(user));
            modelAndView.setViewName("redirect:/chat");
        } else {
            alert = "Вы вели неверный логин или пороль!";
            modelAndView.setViewName("redirect:/");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("alert", alert);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
