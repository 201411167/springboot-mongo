package smu.computers.contesthelper.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository repo;

    @After
    public void cleanUp(){
        repo.deleteById("testId");
    }

    @Test
    public void 유저등록(){
        User user = User.builder()
                .id("testId")
                .password("testPw")
                .build();
        repo.save(user);

        User foundUser = repo.findById("testId").get();

        assertThat(foundUser.getId() ,is(user.getId()));
    }
}