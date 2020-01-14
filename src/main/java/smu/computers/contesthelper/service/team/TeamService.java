package smu.computers.contesthelper.service.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.computers.contesthelper.domain.team.Team;
import smu.computers.contesthelper.domain.team.TeamRepository;
import smu.computers.contesthelper.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepo;

    public List<Team> findTeamListByName(String team_name) {
        List<Team> teamList = new ArrayList<>();
        for (Team t : teamRepo.findAll()) {
            if (t.getTeam_name().equals(team_name)) {
                teamList.add(t);
            }
        }
        return teamList;
    }

    public Team findTeamById(String team_name, String leader_id) {
        Optional<Team> team = Optional.of(teamRepo.findById(leader_id + team_name).get());
        return team.orElse(null);
    }

    public boolean createTeam(String team_name, String leader_id, String contest_name) {
        String id = leader_id + team_name;
        for (Team t : teamRepo.findAll()) {
            if (t.getId().equals(id)) {
                return false;
            }
        }
        teamRepo.save(Team.builder().team_name(team_name).leader_id(leader_id).contest_name(contest_name).build());
        return true;
    }

    public boolean deleteTeam(String team_name, String leader_id) {
        for (Team t : teamRepo.findAll()) {
            if (t.getTeam_name().equals(team_name) && t.getLeader_id().equals(leader_id)) {
                teamRepo.delete(t);
                return true;
            }
        }
        return false;
    }

    public List<Team> getTeamList(){
        return teamRepo.findAll();
    }

    public boolean updateTeam(Team team){
        teamRepo.save(team);
        return true;
    }

    public boolean moveUserFromParticipantsToMemebers(String team_name, String leader_id, String user_id){
        Team team = teamRepo.findById(leader_id+team_name).get();
        User user = new User();
        for(User u : team.getParticipants()){
            if(u.getId().equals(user_id)){
                user = u;
            }
        }
        team.getMembers().add(user);
        team.getParticipants().remove(user);
        teamRepo.save(team);
        return true;
    }
}
