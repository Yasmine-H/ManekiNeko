package com.ihm.androide.upmc.manekineko.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;

import java.util.ArrayList;

public class UserConnectionActivity extends AppCompatActivity {

    private UserDbHelper userDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection);

        userDbHelper = new UserDbHelper(this);


    }


    public void newUser(View view) throws Exception {
        EditText username_edit = findViewById(R.id.username);
        String username = username_edit.getText().toString();
        TextView userView = findViewById(R.id.userDescription);

        EditText password_edit = findViewById(R.id.password);
        String password = password_edit.getText().toString();


        EditText email_edit = findViewById(R.id.email);
        String email = email_edit.getText().toString();

        User user;


        if(email.equals(""))
        {
            user = new User(username, password);
        }
        else
        {
            user = new User(username, password, email);
        }
        if(user.checkUserValidity())
        {
            String dbPassword = userDbHelper.fetchUser(user);
            if(dbPassword==null)
            {
                userDbHelper.createUser(user);
                userView.setText("New user successfully added ! ");
            }
            else
            {
                if(password.equals(dbPassword)){
                    userView.setText("Password correct");
                }
                else
                {
                    userView.setText("Bad password");
                }
            }
        }
        else
        {

            userView.setText("ERROR");
        }

    }

    public void showUserList(View view) {

        ArrayList<User> userList = userDbHelper.fetchAllUsers();

        TextView userView = findViewById(R.id.userDescription);
        for(User user : userList){
            userView.append("\n"+user.getId()+"    "+user.getName()+"   "+user.getEmail()+"    "+user.getPhoto());
        }

    }


}
