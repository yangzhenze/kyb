package com.api;

import com.api.bean.User;
import com.api.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setAgent(1);
        user.setCreateDate(new Date());
        User newUser = userService.add(user);
        System.out.println(newUser);
    }

}
