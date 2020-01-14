package smu.computers.contesthelper.domain.user;

import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import smu.computers.contesthelper.domain.category.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String password;
    private List<String> teamList = new ArrayList<>();
    private List<Category> category = new ArrayList<>();

    @Builder
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    @Builder
    public User(){

    }
}
