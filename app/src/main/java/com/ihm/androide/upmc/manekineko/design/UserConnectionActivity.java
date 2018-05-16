package com.ihm.androide.upmc.manekineko.design;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;
import com.ihm.androide.upmc.manekineko.database.Constants;
import com.ihm.androide.upmc.manekineko.database.Meal;
import com.ihm.androide.upmc.manekineko.database.RequestInterface;
import com.ihm.androide.upmc.manekineko.database.ServerUserRequest;
import com.ihm.androide.upmc.manekineko.database.ServerUserResponse;
import com.ihm.androide.upmc.manekineko.database.User;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserConnectionActivity extends AppCompatActivity {


    TextView userInfoView;
    //private ProgressBar progress;
    ArrayList<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection);
        userInfoView = findViewById(R.id.information);



        userInfoView.setText(getString(R.string.welcomeText));
    }


    private void registerProcess(String name, final String email, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        final User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        ServerUserRequest request = new ServerUserRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        Call<ServerUserResponse> response = requestInterface.operation(request);

        userInfoView.setText("Loading registration...");
        response.enqueue(new Callback<ServerUserResponse>() {
            @Override
            public void onResponse(Call<ServerUserResponse> call, retrofit2.Response<ServerUserResponse> response) {

                ServerUserResponse resp = response.body();
                if(resp != null)
                {
                    userInfoView.setText(resp.getMessage());
                    if(resp.getResult().equals(Constants.SUCCESS))
                    {
                        connect(user);
                        Log.d(getClass().getName(), user.getName()+"---------------");
                    }
                    else
                    {
                        userInfoView.setText("Error : "+resp.getMessage());
                    }
                }
                else
                {
                    userInfoView.setText("resp is null");
                }
                //Snackbar.make(getView(), , Snackbar.LENGTH_LONG).show();
                //progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerUserResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                userInfoView.setText(t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    public void newUser(View view){
        EditText username_edit = findViewById(R.id.username);
        String username = username_edit.getText().toString();

        EditText password_edit = findViewById(R.id.password);
        String password = password_edit.getText().toString();


        EditText email_edit = findViewById(R.id.email);
        String email = email_edit.getText().toString();

        if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

            //progress.setVisibility(View.VISIBLE);
            registerProcess(username,email,password);


        } else {
            showErrorDialog(getString(R.string.missingInfo_Title), getString(R.string.registerText));
            //userInfoView.setText("Fields are empty ! "+username+" mdp : "+password+" email : "+email);
            //Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
        }

    }

    private void connect(User user) {

        EditText username_edit = findViewById(R.id.username);
        username_edit.setText("");

        EditText password_edit = findViewById(R.id.password);
        password_edit.setText("");


        EditText email_edit = findViewById(R.id.email);
        email_edit.setText("");

        Intent myIntent = new Intent(UserConnectionActivity.this, OrderActivity.class);
        myIntent.putExtra("user", user); //Optional parameters
        UserConnectionActivity.this.startActivity(myIntent);

        //userInfoView.setText(user.toString());
    }

    public void login(View view) {
        EditText password_edit = findViewById(R.id.password);
        String password = password_edit.getText().toString();


        EditText email_edit = findViewById(R.id.email);
        String email = email_edit.getText().toString();


        if(!email.isEmpty() && !password.isEmpty()) {

            loginProcess(email,password);

        } else {
            showErrorDialog(getString(R.string.missingInfo_Title), getString(R.string.loginText));
            userInfoView.setText("Fields are empty !");
        }
    }

    private void loginProcess(String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        ServerUserRequest request = new ServerUserRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerUserResponse> response = requestInterface.operation(request);

        userInfoView.setText("Loading log in...");
        response.enqueue(new Callback<ServerUserResponse>() {
            @Override
            public void onResponse(Call<ServerUserResponse> call, retrofit2.Response<ServerUserResponse> response) {

                ServerUserResponse resp = response.body();


                if(resp!= null)
                {
                    userInfoView.setText(resp.getMessage());
                    if(resp.getResult().equals(Constants.SUCCESS)){
                        /*
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean(Constants.IS_LOGGED_IN,true);
                        editor.putString(Constants.EMAIL,resp.getUser().getEmail());
                        editor.putString(Constants.NAME,resp.getUser().getName());
                        editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
                        editor.apply();
                        goToProfile();
                        */
                        connect(resp.getUser());
                        userInfoView.append("\nsuccessfully logged !");
                    }
                    else
                    {
                        userInfoView.setText(resp.getMessage());
                    }
                    //progress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    userInfoView.setText("resp is null");
                }

            }

            @Override
            public void onFailure(Call<ServerUserResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                userInfoView.setText(t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }


    public void showUsernameMessage(View view) {
        showMessage(getString(R.string.usernameText));
    }

    public void showEmailMessage(View view) {
        showMessage(getString(R.string.emailText));
    }

    public void showPasswordMessage(View view) {
        showMessage(getString(R.string.passwordText));
    }

    public void showMessage(String msg){
        userInfoView.setText(msg);
    }

    public void showErrorDialog(String title, String msg){

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(title)
                .setMessage(msg)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void pass(View view) {
        EditText username_edit = findViewById(R.id.username);
        String username = username_edit.getText().toString();

        if(username.isEmpty()){
            showErrorDialog(getString(R.string.missingInfo_Title), getString(R.string.passText));
        }
        else
        {
            connect(new User(username));
        }

    }

    @Override
    protected void onResume() {
        EditText username_edit = findViewById(R.id.username);
        username_edit.clearComposingText();

        EditText password_edit = findViewById(R.id.password);
        password_edit.clearComposingText();


        EditText email_edit = findViewById(R.id.email);
        email_edit.clearComposingText();
        super.onResume();
    }
}
