package com.omercikali.mobildefterim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class With_google_sing_up extends AppCompatActivity {
    private EditText emailet, passwordet, trypasswordet;
    private Button enterbt;
    private ProgressBar progressBar;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_google_sing_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab));

        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);
        trypasswordet = findViewById(R.id.trypasswordet);
        enterbt = findViewById(R.id.button3);
        emailet = findViewById(R.id.emailEt);
        passwordet = findViewById(R.id.passwordEt);
        AlertDialog.Builder builder = new AlertDialog.Builder(With_google_sing_up.this);
        builder.setTitle("Mobildefter");
        builder.setMessage("şifrenizi unutmanız riskinize karşılık kullanıdığınız bi google hesabı giriniz");
        builder.setPositiveButton("okudum anladım", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();

        enterbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamestr = emailet.getText().toString();
                String passwordstr = passwordet.getText().toString();
                String trypasswordSTR = trypasswordet.getText().toString();
                if (passwordstr.equals(trypasswordSTR)) {
                    progressBar.setVisibility(View.VISIBLE);
                    singUpFirebase(usernamestr, passwordstr);
                } else {
                    Toast.makeText(getApplicationContext(), "Girdiğiniz şifreler uyuşmuyor", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void singUpFirebase(String username, String userpassword) {

        auth.createUserWithEmailAndPassword(username, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(getApplicationContext(), "hesap başarılı bir şekilde oluşturuldu", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(With_google_sing_up.this, Login_page.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.anim_out, R.anim.anim_in);
                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(getApplicationContext(), "lütfen geçerli bir e-posta formatı kullanınız", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(getApplicationContext(), "bu e-posta adresi kullanılıyor", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "beklenmedik bir hata oluştu", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        }
                    }

                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
}