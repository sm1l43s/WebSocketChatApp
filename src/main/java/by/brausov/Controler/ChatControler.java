package by.brausov.Controler;

import by.brausov.model.entities.User;
import by.brausov.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatControler {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ModelAndView chat(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/chat/ajax", method = RequestMethod.POST)
    public String getAllUsers() {
        Gson gson = new Gson();
        List<User> userList = userService.allOnlineUsers();
        ArrayList list = new ArrayList();

        for (User user: userList) {
            String str = user.toString();
            list.add(str);
        }

        return gson.toJson(list);
    }
}
