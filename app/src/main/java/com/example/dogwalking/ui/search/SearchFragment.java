package com.example.dogwalking.ui.search;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogwalking.R;
import com.example.dogwalking.databinding.FragmentSearchBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class SearchFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private Button btn_datePick;
    private FragmentSearchBinding binding;
    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel homeViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_datePick = (Button) root.findViewById(R.id.pickDateButton);
        btn_datePick.setOnClickListener(this);

        spinner = (Spinner) root.findViewById(R.id.spinnerParameter);
        //@SuppressLint("ResourceType") List<String> list = root.findViewById(R.array.languages);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.languages,
                         android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        final TextView textView = binding.dateText;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        View root = binding.getRoot();
        TextView textView = (TextView) root.findViewById(R.id.dateText);
        textView.setText(currentDate);
    }
}