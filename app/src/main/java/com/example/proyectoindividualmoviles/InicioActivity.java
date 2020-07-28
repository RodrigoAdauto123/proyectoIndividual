package com.example.proyectoindividualmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class InicioActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        callbackManager = CallbackManager.Factory.create();
        loginButton= findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(InicioActivity.this,"Se cancelo el inicio de sesion con Facebook",Toast.LENGTH_SHORT);

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(InicioActivity.this,"Ocurrio un error, trate de nuevo",Toast.LENGTH_SHORT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
