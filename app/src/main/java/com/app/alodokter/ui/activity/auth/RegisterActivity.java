package com.app.alodokter.ui.activity.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.alodokter.ui.MainActivity;
import com.app.alodokter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtTelpon)
    EditText edtTelpon;
    @BindView(R.id.edtPassword)
    ShowHidePasswordEditText edtPassword;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

        }
    }

    private void registerUser() {
        final String name = edtName.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String telpon = edtTelpon.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();


        if (name.isEmpty()) {
            edtName.setError("name required");
            edtName.requestFocus();
            return;
        }


        if (email.isEmpty()) {
            edtEmail.setError("email required");
            edtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("enter a valid email");
            edtEmail.requestFocus();
            return;
        }


        if (telpon.isEmpty()) {
            edtTelpon.setError("telpon required");
            edtTelpon.requestFocus();
            return;
        }
        if (telpon.length() != 12) {
            edtTelpon.setError("no telpon tidak valid");
            edtTelpon.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            edtPassword.setError("password required");
            edtPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            edtPassword.setError("minimum lenght of password should be 6");
            edtPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    @OnClick({R.id.btnRegister, R.id.tvLogin})
    public void onSignUp(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnRegister:
                registerUser();
                break;

            case R.id.tvLogin:
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
