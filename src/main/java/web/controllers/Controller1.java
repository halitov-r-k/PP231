package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.DAO.UserDAO;
import web.models.User;

@Controller
public class Controller1 {

    private UserDAO userDAO;
    @Autowired
    public Controller1(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userDAO.index());
    return "index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.showUser(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/";
    }
}
