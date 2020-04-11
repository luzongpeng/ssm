package top.lzp.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.lzp.ssm.dao.UserDao;
import top.lzp.ssm.entity.User;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private UserDao userDao;
    @RequestMapping("/test")
    public String test(){
        List<User> searchAllUsers = userDao.searchAllUsers();

        for (User user:searchAllUsers){
            System.out.println(user);
        }

        return null;
    }
}
