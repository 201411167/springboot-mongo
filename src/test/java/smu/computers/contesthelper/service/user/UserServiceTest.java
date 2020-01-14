package smu.computers.contesthelper.service.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import smu.computers.contesthelper.domain.user.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;

    @After
    public void cleanUp(){
        service.deleteUser("testId", "testPw");
    }

    @Test
    public void 유저서비스(){
        boolean addingUser = service.addUser("testId", "testPw");
        service.addCategoryToUserById("it",4,"testId");
        User updatedUser = service.findUserById("testId");
        System.out.println(updatedUser);
        boolean alreadyExistedId = service.alreadyExistedId("testId");
        boolean deletingUser = service.deleteUser("testId", "testPw");

        assertThat(addingUser, is(true));
        assertThat(alreadyExistedId, is(true));
        assertThat(deletingUser, is(true));

    }
}