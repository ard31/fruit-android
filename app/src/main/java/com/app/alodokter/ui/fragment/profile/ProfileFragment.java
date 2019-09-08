package com.app.alodokter.ui.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.alodokter.R;
import com.app.alodokter.ui.activity.auth.LoginActivity;
import com.app.alodokter.ui.activity.auth.ResetPasswordActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvNoHandPhone)
    TextView tvNoHandPhone;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;

    @BindView(R.id.btnLogOut)
    Button btnLogOut;

    private String name;
    private String email;
    private String handphone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_profile, null, false);
        ButterKnife.bind(this, layout);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
            email = user.getEmail();
            handphone = user.getPhoneNumber();

            tvEmail.setText(email);
            tvUserName.setText(name);
            tvNoHandPhone.setText(handphone);
        }

        return layout;
    }


    @OnClick({R.id.btnLogOut, R.id.btnResetPassword})
    public void onProfile(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnLogOut:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case R.id.btnResetPassword:
                intent = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
