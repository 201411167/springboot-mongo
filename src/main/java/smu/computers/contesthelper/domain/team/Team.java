package smu.computers.contesthelper.domain.team;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import smu.computers.contesthelper.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Team {
    @Id
    private String id;
    private String team_name;
    private String leader_id;
    private String contest_name;
    private List<User> members = new ArrayList<>();
    private List<User> participants = new ArrayList<>();

    @Builder
    public Team(String team_name, String leader_id, String contest_name) {
        this.id = leader_id + team_name;
        this.team_name = team_name;
        this.leader_id = leader_id;
        this.contest_name = contest_name;
    }
}
