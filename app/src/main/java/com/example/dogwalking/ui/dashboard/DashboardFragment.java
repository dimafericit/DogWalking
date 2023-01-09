package com.example.dogwalking.ui.dashboard;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogwalking.MainActivity;
import com.example.dogwalking.R;
import com.example.dogwalking.databinding.FragmentPublishBinding;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private FragmentPublishBinding binding;
    private Button publishButton;
    private String descriptionString, addressString, dogBreedString;
    private EditText description,dogBreed, address;



    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPublishBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        publishButton = (Button) root.findViewById(R.id.buttonPublish);
        publishButton.setOnClickListener(this);

        description = (EditText) root.findViewById(R.id.editTextTextDescription);
        address = (EditText) root.findViewById(R.id.editTextTextAddress);
        dogBreed = (EditText) root.findViewById(R.id.editTextTextDogBreed);

        return root;
    }


    @Override
    public void onClick(View view) {
        descriptionString = description.getText().toString();
        dogBreedString = dogBreed.getText().toString();
        addressString = address.getText().toString();
        if (!descriptionString.isEmpty() && !addressString.isEmpty() && !dogBreedString.isEmpty()) {
            MainActivity.publishOffer(descriptionString, addressString, dogBreedString);
            Toast.makeText(getActivity(), "Offer added successfully! ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Not all Rows are completed! ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}