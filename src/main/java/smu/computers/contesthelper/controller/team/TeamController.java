package smu.computers.contesthelper.controller.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smu.computers.contesthelper.domain.team.Team;
import smu.computers.contesthelper.service.team.TeamService;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/teamList")
    public List<Team> teamList(){
        return teamService.getTeamList();
    }

    @GetMapping("/addTeam")
    public void addTeam(@RequestParam String teamName, @RequestParam String leaderId, @RequestParam String contestName){
        teamService.createTeam(teamName, leaderId, contestName);
    }

    @GetMapping("/deleteTeam")
    public void deleteTeam(@RequestParam String teamName, @RequestParam String leaderId){
        teamService.deleteTeam(teamName,leaderId);
    }

    @GetMapping("/participantsToMember")
    public void participantsToMember(@RequestParam String team_name, @RequestParam String leader_id, @RequestParam String user_id){
        teamService.moveUserFromParticipantsToMemebers(team_name,leader_id,user_id);
    }
}
