package com.example.dogwalking.ui.search;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogwalking.MainActivity;
import com.example.dogwalking.R;
import com.example.dogwalking.Slider;
import com.example.dogwalking.databinding.FragmentSearchBinding;
import com.example.dogwalking.ui.dashboard.ListAdapter;
import com.example.dogwalking.ui.dashboard.Offer;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel homeViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<Offer> offers = MainActivity.getOffers();

        ListAdapter listAdapter = new ListAdapter(getActivity(),offers);

        ListView listView = root.findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), Slider.class);

                TextView description = (TextView) view.findViewById(R.id.description);
                TextView address = (TextView) view.findViewById(R.id.address);
                TextView dogBreed = (TextView) view.findViewById(R.id.dogBreed);
                TextView username = (TextView) view.findViewById(R.id.hiddenUsername);
                Slider.saveFields(description.getText().toString(), address.getText().toString(), dogBreed.getText().toString(), username.getText().toString());

                System.out.println(address.getText().toString());
                CircleImageView image = (CircleImageView) view.findViewById(R.id.profile_pic);
                CircleImageView image2 = (CircleImageView) view.findViewById(R.id.profile_image);
                startActivity(i);

            }
        });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}