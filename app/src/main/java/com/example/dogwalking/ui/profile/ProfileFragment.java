package com.example.dogwalking.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogwalking.Chat;
import com.example.dogwalking.Chats;
import com.example.dogwalking.EditProfileActivity;
import com.example.dogwalking.MainActivity;
import com.example.dogwalking.PopUpActivity;
import com.example.dogwalking.R;
import com.example.dogwalking.databinding.FragmentProfileBinding;
import com.example.dogwalking.model.profile.User;
import com.example.dogwalking.ui.login.Login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button btn_logout, btn_edit;
    private TextView username;
    private FragmentProfileBinding binding;
    private TextView bio, phone;
    private ImageButton imageButton, inbox;
    int SELECT_PICTURE = 200;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        username = (TextView) root.findViewById(R.id.profileName13);
        String name = String.valueOf(Login.et_username.getText());
        setUsername(name);

        imageButton = (ImageButton) root.findViewById(R.id.profilePicture);
        imageButton.setOnClickListener(this);
        setImage();

        phone = (TextView) root.findViewById(R.id.editTextTextPhone);
        bio = (TextView) root.findViewById(R.id.editTextTextBio);
        User user = MainActivity.getUser(name);
        if (user.getPhone() != null)
            phone.setText(user.getPhone());
        if (user.getBio() != null)
            bio.setText(user.getBio());

        btn_logout = (Button) root.findViewById(R.id.logout_button);
        btn_logout.setOnClickListener(this);

        btn_edit= (Button) root.findViewById(R.id.editButton);
        btn_edit.setOnClickListener(this);

        inbox = (ImageButton) root.findViewById(R.id.inboxButton);
        inbox.setOnClickListener(this);

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
            case R.id.profilePicture:
                imageChooser();
                break;
            case R.id.editButton:
                openPopUpActivity();
                break;
            case R.id.inboxButton:
                openChatsActivity();
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

    public void refreshFragment(){
    }

    public void openPopUpActivity(){
        Intent intent = new Intent(getActivity(), PopUpActivity.class);
        startActivity(intent);
    }

    public void openChatsActivity(){
        Intent intent = new Intent(getActivity(), Chats.class);
        startActivity(intent);
    }


    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap imageBitMap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImageUri);
                    byte[] imageToSave = getBitmapAsByteArray(imageBitMap);
                    MainActivity.updateImage(imageToSave);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != selectedImageUri) {
                    imageButton.setImageURI(selectedImageUri);
                    System.out.println(selectedImageUri);
                }
            }
        }
    }

    public void setBio(String bio) {
        this.bio.setText(bio);
    }

    public void setUsername(String username) {
        this.username.setText( username);
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void setImage(){

        byte[] image = MainActivity.getImage(String.valueOf(Login.et_username.getText()));
        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageButton.setImageBitmap(bmp);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}