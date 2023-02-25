package com.user.userops.service;

import com.user.userops.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static ArrayList<User> users=new ArrayList<>();
    public static int id=0;

    static {
        users.add(new User(++id, "Messi", LocalDate.now().minusYears(20)));
        users.add(new User(++id, "Suarez", LocalDate.now().minusYears(18)));
        users.add(new User(++id, "Neymar", LocalDate.now().minusYears(15)));
    }



    public List<User> retrieveAllUsers() {
        return users;
    }

    public User getUser(int userId){

       for (User user:users){
           if(user.getId()==userId)return user;
       }
       return null;
    }

    public User addUser(User user)
    {
        User newUser = User.builder().id(++id).name(user.getName()).dob(user.getDob()).build();
        users.add(newUser);
        return newUser;
    }

    public User deleteUser(int userId){

        for (User user:users){
            if(user.getId()==userId) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }

    public User updateUser(int userId, User requestUser)
    {
        int counter = 0;
        for (User user:users)
        {
            if(user.getId()==userId)
            {
                //users.remove(user);
                User newUser = User.builder().id(userId).name(requestUser.getName()).dob(requestUser.getDob()).build();
                users.set(counter,newUser);
                return newUser;
            }
            counter++;
        }
        return null;
    }

}
