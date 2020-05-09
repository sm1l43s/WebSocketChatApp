package by.brausov.Controler;

import by.brausov.model.entities.Role;
import by.brausov.model.entities.User;
import by.brausov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        user.setRole(Role.USER);
        userService.add(user);
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        @RequestParam(name = "logout", required = false) Boolean logout,
                        Model model) {

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }

        if (Boolean.TRUE.equals(logout)) {
            model.addAttribute("logout", true);
        }

        return "/login";
    }

    @RequestMapping(value="/customLogout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            userService.setStatusOffline(userService.getByEmail(getEmail(auth)));
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/logout";
    }

    private String getEmail(Authentication authentication) {

        int posStart = authentication.getName().indexOf("email='");
        int posFinish = authentication.getName().indexOf("name='");
        // email=' -> 7 symbol
        // ...(', ) name=' -> in (...) 3 symbol
        return authentication.getName().substring(posStart + 7, posFinish - 3).trim();
    }

}
