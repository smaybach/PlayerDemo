package com.baway.liuheng.playerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baway.liuheng.playerdemo.bean.UserBean;
import com.baway.liuheng.playerdemo.dao.MyUserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyUserDao dao = new MyUserDao(this);
        for (int i = 1; i < 10; i++) {
            UserBean user = new UserBean();
            user.setId(Long.valueOf(i));
            user.setAge(i * 3);
            user.setName("第" + i + "人");
            dao.insertUser(user);
        }
        List<UserBean> userList = dao.queryUserList();
        for (UserBean user : userList) {
            Log.e("TAG", "queryUserList--before-->" + user.getId() + "--" + user.getName() +"--"+user.getAge());
            if (user.getId() == 5) {
                dao.deleteUser(user);
            }
            if (user.getId() == 8) {
                user.setAge(15);
                dao.updateUser(user);
            }
        }
        userList = dao.queryUserList();
        for (UserBean user : userList) {
            Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName()+"--"+user.getAge());
        }    }
}
