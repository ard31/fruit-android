package com.app.alodokter.ui.activity.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.alodokter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
    }
    private void ResetPassword() {
        final String email = edtEmail.getText().toString().trim();

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

        // Reset Password
        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(ResetPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @OnClick(R.id.btnResetPassword)
    public void onResetPassword(View view) {
        ResetPassword();
    }
}
