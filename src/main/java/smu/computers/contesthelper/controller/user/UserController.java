package smu.computers.contesthelper.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smu.computers.contesthelper.domain.user.User;
import smu.computers.contesthelper.service.user.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/addUser")
    public void addUser(@RequestParam String id, @RequestParam String password){
        userService.addUser(id,password);
    }

    @GetMapping("/userList")
    public List<User> userList(){
        return userService.findAllUser();
    }

    @GetMapping("/addCategory")
    public void addCategory(@RequestParam String category_name, @RequestParam double score, @RequestParam String id){
        userService.addCategoryToUserById(category_name,score,id);
    }

    @GetMapping("/participate")
    public void participate(@RequestParam String user_id, @RequestParam String team_name, @RequestParam String leader_id){
        userService.participateToTeam(user_id, team_name, leader_id);
    }
}
