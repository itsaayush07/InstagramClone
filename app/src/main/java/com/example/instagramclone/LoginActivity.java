package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLoginActivity,btnSignUpLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER&& event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });



        btnLoginActivity=findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity=findViewById(R.id.btnSignUpLoginActivity);

        btnLoginActivity.setOnClickListener(LoginActivity.this);
        btnSignUpLoginActivity.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLoginActivity:
                if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("") ) {
                    FancyToast.makeText(LoginActivity.this, "Email,Password is required ",
                            Toast.LENGTH_SHORT,
                            FancyToast.INFO, true).show();
                }
                else{
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null)
                        {
                            FancyToast.makeText(LoginActivity.this,user.getUsername()+" is logged in successfully ",
                                    Toast.LENGTH_LONG,
                                    FancyToast.SUCCESS,true).show();
                            transitionTOSocialMediaActvity();
                        }
                        else
                        {
                            FancyToast.makeText(LoginActivity.this,"There is something error"+ e.getMessage(),
                                    Toast.LENGTH_LONG,
                                    FancyToast.ERROR,true).show();

                        }
                    }
                });
                }
                break;
            case R.id.btnSignUpLoginActivity:
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void loginRootLayout(View view)
    {
        try
        {


            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    private void transitionTOSocialMediaActvity()
    {
        Intent intent=new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
