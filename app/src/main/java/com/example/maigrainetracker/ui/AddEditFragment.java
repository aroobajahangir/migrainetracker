package com.example.maigrainetracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.databinding.FragmentAddEditBinding;
import com.example.maigrainetracker.viewmodel.MigraineViewModel;

import java.util.Date;

// This fragment is obsolete, but has been fixed to allow the project to compile.
public class AddEditFragment extends Fragment {

    private FragmentAddEditBinding binding;
    private MigraineViewModel viewModel;
    private int recordId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
        if (getArguments() != null) {
            recordId = getArguments().getInt("recordId", -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (recordId != -1) {
            viewModel.getRecordById(recordId).observe(getViewLifecycleOwner(), this::populateUi);
        }

        binding.btnSave.setOnClickListener(v -> saveRecord());
    }

    private void populateUi(MigraineRecord record) {
        if (record != null) {
            binding.tvDate.setText(new Date(record.recordDate).toString());
            binding.etIntensity.setText(String.valueOf(record.painLevel)); // FIX: Use painLevel
            binding.etMedication.setText(record.medication);
            binding.etNotes.setText(record.notes);
        }
    }

    private void saveRecord() {
        String intensityStr = binding.etIntensity.getText().toString();
        if (intensityStr.isEmpty()) {
            Toast.makeText(getContext(), "Intensity is required", Toast.LENGTH_SHORT).show();
            return;
        }

        MigraineRecord record = new MigraineRecord();
        if (recordId != -1) {
            record.id = recordId;
        }
        record.recordDate = new Date().getTime();
        record.painLevel = Integer.parseInt(intensityStr); // FIX: Use painLevel
        record.medication = binding.etMedication.getText().toString();
        record.notes = binding.etNotes.getText().toString();

        if (recordId == -1) {
            viewModel.insert(record);
        } else {
            viewModel.update(record);
        }

        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
