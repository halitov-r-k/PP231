package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.DAO.UserDAO;

@Controller
public class Controller1 {

    private UserDAO userDAO;
    @Autowired
    public Controller1(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
//    @GetMapping(value = "/")
    @GetMapping("/")
        public String showUsers(Model model) {
            model.addAttribute("users", userDAO.index());
        return "index";
        }
//        @GetMapping(value = "/{id}")
        @GetMapping("/{id}")
        public String showUser(@PathVariable("id") int id, Model model) {
            model.addAttribute("user", userDAO.showUser(id));
        return "user";
    }


}
