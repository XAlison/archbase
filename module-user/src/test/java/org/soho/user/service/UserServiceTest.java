package org.soho.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soho.UserApplication;
import org.soho.common.ResultInfo;
import org.soho.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhuozl on 17-4-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserApplication.class)
public class UserServiceTest {

    Logger logger= LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testAll(){
        findAllUsers();
        findUserById();
        createUser();
    }

    @Test
    public void findAllUsers()  {
        List<User> users = userService.getAll();
        assertNotNull(users);
        assertTrue(!users.isEmpty());

    }

    @Test
    public void findUserById()  {
        User user = userService.getByID("1");
        assertNotNull(user);
    }

    private void updateById(String id)  {
        User newUser = new User();
        newUser.setId("77");
        newUser.setName("Z77");
        newUser.setSex(26);
        newUser.setAge(33);
        userService.update(newUser);
        User newUser2 = userService.getByID(newUser.getId());
        assertEquals(newUser.getName(), newUser2.getName());
    }
    @Test
    public void createUser() {
        User user = new User();
        user.setId("77");
        user.setName("ZZ77");
        user.setSex(26);
        user.setAge(33);

        ResultInfo resultInfo = userService.insert(user);
        logger.debug("{}",resultInfo);
        User newUser = userService.getByID(user.getId());
        assertEquals("ZZ77", newUser.getName());
        updateById(user.getId());
        userService.deleteById(newUser.getId());
    }
}
