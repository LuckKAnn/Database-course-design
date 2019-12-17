package scu.cj.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import scu.cj.community.cache.HotTagCache;
import scu.cj.community.model.Advertisement;
import scu.cj.community.model.Question;
import scu.cj.community.model.User;
import scu.cj.community.service.AdService;
import scu.cj.community.service.QuestionService;
import scu.cj.community.service.UserService;

import java.util.List;

@Controller
public class UserSearchController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @Autowired
    private AdService adService;

    @GetMapping("/userSearch")
    public String userSearch(@RequestParam(name = "search", required = false) String search, Model model) {
//        System.out.printf("---------------");
        List<User> userList = userService.findUserBySearch(search);
//        for (User user:userList) {
//            System.out.println("++++++++++++++");
//            System.out.println(user);
//        }
        List<Question> recommands = questionService.getRecommand();
        List<Advertisement> advs = adService.getAll();
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("advs", advs);
        model.addAttribute("recommands",recommands);
        model.addAttribute("tags", tags);
       model.addAttribute("userList",userList);
        model.addAttribute("sectionName","搜索用户");
        return "user_search_list.html";
    }
}
