package by.brausov.Controler;

import by.brausov.model.entities.Post;
import by.brausov.model.entities.User;
import by.brausov.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NewsController {
    private int page;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView chat(@RequestParam(defaultValue = "1", required = false) int page,
                             @AuthenticationPrincipal User user) {
        this.page = page;
        List<Post> posts = postService.allPosts(page);

        int postsCount = postService.postsCount();
        int pagesCount = (postsCount + 9) / 10;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news");
        modelAndView.addObject("user", user);
        modelAndView.addObject("postList", posts);
        modelAndView.addObject("postsCount", postsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @RequestMapping(value = "/newsAdd", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post") Post post,
                          @AuthenticationPrincipal User user) {
        post.setUser(user);
        postService.add(post);
        return "redirect:/news";
    }

    @RequestMapping(value="/delete_post/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/news?page=" + this.page);
        Post post = postService.getById(id);
        postService.delete(post);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_post/{id}", method = RequestMethod.GET)
    public ModelAndView editPageModerator(@PathVariable("id") int id) {
        Post post = postService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit_post");
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_post", method = RequestMethod.POST)
    public ModelAndView editUserModerator(@ModelAttribute("post") Post post,
                                          @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/news?page=" + this.page);
        post.setUser(user);
        postService.edit(post);
        return modelAndView;
    }


}
