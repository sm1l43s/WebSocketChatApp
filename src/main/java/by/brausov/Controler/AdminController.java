package by.brausov.Controler;

import by.brausov.model.entities.Role;
import by.brausov.model.entities.User;
import by.brausov.service.HttpSessionsService;
import by.brausov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AdminController {
    private int page;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public HttpSessionsService httpSessionsService;

    @RequestMapping(value = "/admin_panel", method = RequestMethod.GET)
    public ModelAndView usersList(@RequestParam(defaultValue = "1", required = false) int page,
                                  @RequestParam(required = false, defaultValue = "") String filter,
                                  @RequestParam(required = false, defaultValue = "") String search,
                                  @AuthenticationPrincipal User user) {

        this.page = page;
        List<User> users = userService.allUsers(page);

        if (filter.equals("ADMIN") || filter.equals("USER") || filter.equals("MODERATOR")) {
            switch (filter) {
                case "ADMIN": users = userService.allUserByRole(Role.ADMIN); break;
                case "MODERATOR": users = userService.allUserByRole(Role.MODERATOR); break;
                case "USER": users = userService.allUserByRole(Role.USER); break;
            }
        } else if (filter.equals("online") || filter.equals("offline")) {
            users = userService.allUserByStatus(filter);
        } else if (filter.equals("true") || filter.equals("false")) {
            users = userService.allUserByBlocked(Boolean.valueOf(filter));
        }

        if (!search.equals("")) {
            users = userService.search(search);
        }

        int usersCount = userService.usersCount();
        int pagesCount = (usersCount + 9) / 10;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin_panel");

        modelAndView.addObject("page", page);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("search", search);
        modelAndView.addObject("usersList", users);
        modelAndView.addObject("user", user);
        modelAndView.addObject("usersCount", usersCount);
        modelAndView.addObject("pagesCount", pagesCount);

        return modelAndView;
    }

    @RequestMapping(value = "/edit_admin/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit_admin");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_admin", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin_panel?page=" + this.page);
        if (user.getBlocked()) {
            destroySession(user.getId());
            user.setStatus("offline");
        }
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_moderator/{id}", method = RequestMethod.GET)
    public ModelAndView editPageModerator(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit_moderator");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_moderator", method = RequestMethod.POST)
    public ModelAndView editUserModerator(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/chat");
        destroySession(user.getId());
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value="/delete_admin/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin_panel?page=" + this.page);
        User user = userService.getById(id);
        destroySession(user.getId());
        userService.delete(user);
        return modelAndView;
    }

    private void destroySession(int userId) {
        userService.setStatusOffline(userService.getById(userId));
        httpSessionsService.getUserAndSession().get(userId).invalidate();
        httpSessionsService.getUserAndSession().remove(userId);
    }

}
