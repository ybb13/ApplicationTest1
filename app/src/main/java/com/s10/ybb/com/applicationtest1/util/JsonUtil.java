package com.s10.ybb.com.applicationtest1.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.s10.ybb.com.applicationtest1.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/2/7.
 */
public class JsonUtil {

    /*  用户登录
        返回0:登陆成功
        返回1:用户名不存在
        返回2:密码错误
        返回3:帐号状态已冻结
     */
    public static int User_Login(String jsonData , String pwd){
        Gson gson = new Gson();
        List<User> userlist = gson.fromJson(jsonData,new TypeToken<List<User>>(){}.getType());
        if(userlist==null||userlist.size()<=0){
            return 1;
        }
        for (User user:userlist) {
            Log.d("App1","password="+user.getPassword());
            if(pwd.equals(user.getPassword())){

                if("1".equals(user.getStaute())){
                    return 3;
                }else{
                    return 0;
                }
            }else{
                return 2;
            }
        }
         return 0;
    }
}
