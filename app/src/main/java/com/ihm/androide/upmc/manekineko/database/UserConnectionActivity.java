package com.ihm.androide.upmc.manekineko.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ihm.androide.upmc.manekineko.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserConnectionActivity extends AppCompatActivity {


    TextView userView ;
    //private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection);



        userView = findViewById(R.id.userDescription);
    }

    private void registerProcess(String name, String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        userView.setText("Loading registration...");
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp != null)
                {
                    userView.setText(resp.getMessage());
                }
                else
                {
                    userView.setText("resp is null");
                }
                //Snackbar.make(getView(), , Snackbar.LENGTH_LONG).show();
                //progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                userView.setText(t.getLocalizedMessage());
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
            userView.setText("Fields are empty ! "+username+" mdp : "+password+" email : "+email);
            //Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
        }

    }

    public void login(View view) {
        EditText username_edit = findViewById(R.id.username);
        String username = username_edit.getText().toString();

        EditText password_edit = findViewById(R.id.password);
        String password = password_edit.getText().toString();


        EditText email_edit = findViewById(R.id.email);
        String email = email_edit.getText().toString();


        if(!email.isEmpty() && !password.isEmpty()) {

            loginProcess(email,password);

        } else {
            userView.setText("Fields are empty !");

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
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        userView.setText("Loading log in...");
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();


                if(resp!= null)
                {
                    userView.setText(resp.getMessage());
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
                        userView.append("successfully logged !");
                    }
                    //progress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    userView.setText("resp is null");
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                userView.setText(t.getLocalizedMessage());
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }




}
