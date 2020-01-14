package smu.computers.contesthelper.service.team;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smu.computers.contesthelper.domain.team.Team;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {
    @Autowired
    TeamService service;

    @Test
    public void 팀테스트(){
        String teamName= "test_team";
        String leaderId= "test_leader";
        String contestName="test_contest";

        service.deleteTeam(teamName,leaderId);
        boolean creating_team = service.createTeam(teamName, leaderId, contestName);
        boolean finding_team_by_name = service.findTeamListByName(teamName).isEmpty();
        Team team = service.findTeamById(teamName,leaderId);
        String team_name = team.getTeam_name();
        boolean deleting_team = service.deleteTeam(teamName,leaderId);

        assertThat(creating_team, is(true));
        assertThat(finding_team_by_name, is(false));
        assertThat(team_name, is(teamName));
        assertThat(deleting_team, is(true));
    }

}