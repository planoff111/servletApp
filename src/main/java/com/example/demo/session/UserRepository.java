package com.example.demo.session;

import java.util.HashMap;

public class UserRepository {
    public  static HashMap<String, String> userStorage = new HashMap<>();

    public static   void  userStorage(){
        userStorage.put("Admin","password");
        userStorage.put("User1","password1");
        userStorage.put("User2","password2");
        userStorage.put("User3","password3");

    }

    public static boolean checkUser(String user, String pwd){
        if(userStorage.containsKey(user) && userStorage.get(user).equals(pwd)){
            return true;
        }
        return false;
    }
}
