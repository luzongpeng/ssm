package top.lzp.ssm.dao;

import org.springframework.stereotype.Service;
import top.lzp.ssm.entity.User;

import java.util.List;

@Service
public interface UserDao {
    //增
    int addUser1(String usernmae,String password);
    int addUser2(User user);
 /*   //删
    int deleteUser(User user);
    //改
    int updataUser(User user);*/
    //查
    List<User> searchAllUsers();
    User searchUserById(int id);
}
