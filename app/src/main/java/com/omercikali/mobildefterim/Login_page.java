package com.omercikali.mobildefterim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Login_page extends AppCompatActivity {

    private Button withgoogleaccount, loginbutton;
    private EditText emailet, passwordet;
    private FirebaseAuth auth;
    private TextView forgatmypass;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab));
        progressBar = findViewById(R.id.progressBar);
        withgoogleaccount = findViewById(R.id.withgooglebtn);
        loginbutton = findViewById(R.id.button);
        forgatmypass = findViewById(R.id.forgatmypass);
        emailet = findViewById(R.id.emailet);
        passwordet = findViewById(R.id.passwordet);

        auth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);
        forgatmypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_page.this, Forgat_pass.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String usernameStr = emailet.getText().toString();
                String passwordStr = passwordet.getText().toString();
                if (!usernameStr.equals("")) {
                    singin(usernameStr, passwordStr);
                } else {
                    Toast.makeText(getApplicationContext(), "lütfen geçerli bir e-posta giriniz", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    public void withgoogle(View view) {
        Intent intent = new Intent(Login_page.this, With_google_sing_up.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }

    private void singin(String username, String password) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Sign in success, update UI with the signed-in user's information

                        Intent intent = new Intent(Login_page.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                try {
                    throw e;
                } catch (FirebaseAuthInvalidUserException exception) {
                    Toast.makeText(getApplicationContext(), "bu e-posta kayıtlı değil", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                } catch (FirebaseNetworkException exception) {
                    Toast.makeText(getApplicationContext(), "İnternet bağlantınızı kontrol ediniz", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "telefon numarası veya şifre hatalı", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(Login_page.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}