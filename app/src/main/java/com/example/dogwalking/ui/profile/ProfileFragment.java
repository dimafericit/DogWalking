package com.example.dogwalking.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogwalking.EditProfileActivity;
import com.example.dogwalking.PopUpActivity;
import com.example.dogwalking.R;
import com.example.dogwalking.databinding.FragmentProfileBinding;
import com.example.dogwalking.ui.login.Login;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button btn_logout;
    private Button btn_edit;
    private TextView username;
    private FragmentProfileBinding binding;
    private TextView bio;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        username = (TextView) root.findViewById(R.id.profileName13);
        String name = String.valueOf(Login.et_username.getText());
        setUsername(name);

        btn_edit = (Button) root.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);

        btn_logout = (Button) root.findViewById(R.id.logout_button);
        btn_logout.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_button:
                openLoginActivity();
                break;
            case R.id.btn_edit:
                openPopUpActivity();
                break;
        }
    }

    public void openLoginActivity(){
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }

    public void openEditProfileActivity(){
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    public void openPopUpActivity(){
        Intent intent = new Intent(getActivity(), PopUpActivity.class);
        startActivity(intent);
    }



    public void setBio(String bio) {
        this.bio.setText(bio);
    }

    public void setUsername(String username) {
        this.username.setText( username);
    }
}