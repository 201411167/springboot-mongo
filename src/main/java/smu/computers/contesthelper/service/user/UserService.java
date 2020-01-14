package smu.computers.contesthelper.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.computers.contesthelper.domain.category.Category;
import smu.computers.contesthelper.domain.team.Team;
import smu.computers.contesthelper.domain.user.User;
import smu.computers.contesthelper.domain.user.UserRepository;
import smu.computers.contesthelper.service.team.TeamService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    TeamService teamService;

    public boolean alreadyExistedId(String id){
        List<User> userList = userRepo.findAll();
        for(User u : userList){
            if(u.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String id, String password){
        if(!alreadyExistedId(id)){
            userRepo.save(User.builder()
            .id(id)
            .password(password)
            .build());
            return true;
        }else{
            return false;
        }
    }

    public User findUserById(String id){
        Optional<User> user = Optional.of(userRepo.findById(id).get());
        return user.orElse(null);
    }

    public boolean deleteUser(String id, String password){
        List<User> userList = userRepo.findAll();
        for(User u : userList){
            if(u.getId().equals(id) && u.getPassword().equals(password)){
                userRepo.delete(u);
                return true;
            }
        }
        return false;
    }

    public List<User> findAllUser() {
        List<User> userList = userRepo.findAll();
        return userList;
    }

    public boolean addCategoryToUserById(String category_name, double score, String id) {
        User user = userRepo.findById(id).get();
        user.getCategory().add(Category.builder().category_name(category_name).score(score).build());
        userRepo.save(user);
        return true;
    }

    public boolean participateToTeam(String user_id, String team_id, String leader_id){
        Optional<Team> optional_team = Optional.of(teamService.findTeamById(team_id,leader_id));
        if(optional_team.isPresent()){
            Team team = optional_team.get();
            User user = userRepo.findById(user_id).get();
            team.getParticipants().add(user);
            teamService.updateTeam(team);
            return true;
        }else{
            return false;
        }
    }
}
