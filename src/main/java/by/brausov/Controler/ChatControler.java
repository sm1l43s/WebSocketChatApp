package by.brausov.Controler;

import by.brausov.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatControler {

    static User user;

    public static void setUser(User user) {
        ChatControler.user = user;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ModelAndView chat() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
