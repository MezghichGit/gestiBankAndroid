package com.sip.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sip.gestibank.models.User;
import com.sip.gestibank.remote.APIUtils;
import com.sip.gestibank.remote.AuthService;
import com.sip.gestibank.remote.ClientService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    AuthService authService;

    EditText email;
    EditText password;
    String myEmail="";
    String myPassword="";
    User user;
    String  role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // affectation des attributs aux view

        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        authService = APIUtils.getAuthService();

    }


    public void dashboardUser(View v){


        myEmail = email.getText().toString();
        myPassword = password.getText().toString();

        //Toast.makeText(Login.this, "Login : "+ myEmail +" Password : "+ myPassword, Toast.LENGTH_SHORT).show();

        // récupération du user et donc de son role

        Call<User> call = authService.getUserByEmail(myEmail);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    role = user.getRole();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        // fin récupération

        //...


        //role =user.getRole();
        //Redirection vers le dashboard en question : Admin, Client ou Agent

        switch(role)
        {
            case "AGENT" : //.... redirection vers dashboard Agent
                         Intent intent1 = new Intent(Login.this, Agent.class);
                         startActivity(intent1);
                break;

            case "CLIENT" : //.... redirection vers dashboard Agent
                         Intent intent2 = new Intent(Login.this, Client.class);
                         startActivity(intent2);
                break;

            case "ADMIN" : //.... redirection vers dashboard Agent
                         Intent intent3 = new Intent(Login.this, Admin.class);
                         startActivity(intent3);
                break;
        }
    }



    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}