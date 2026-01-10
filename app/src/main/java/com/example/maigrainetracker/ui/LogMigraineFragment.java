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

import com.example.maigrainetracker.databinding.FragmentLogMigraineBinding;
import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.viewmodel.MigraineViewModel;

import java.util.Date;

public class LogMigraineFragment extends Fragment {

    private FragmentLogMigraineBinding binding;
    private MigraineViewModel migraineViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogMigraineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        migraineViewModel = new ViewModelProvider(this).get(MigraineViewModel.class);

        binding.btnSave.setOnClickListener(v -> saveMigraineRecord());
    }

    private void saveMigraineRecord() {
        MigraineRecord record = new MigraineRecord();

        // Date and Time
        record.recordDate = new Date().getTime();

        // Pain Level
        record.painLevel = binding.sbPainLevel.getProgress();

        // Duration
        try {
            record.duration = Long.parseLong(binding.etDuration.getText().toString());
        } catch (NumberFormatException e) {
            record.duration = 0;
        }

        // Migraine Type
        if (binding.rbAura.isChecked()) {
            record.migraineType = "Aura";
        } else if (binding.rbNoAura.isChecked()) {
            record.migraineType = "No Aura";
        } else {
            record.migraineType = "";
        }

        // Symptoms
        record.nausea = binding.cbNausea.isChecked();
        record.vomiting = binding.cbVomiting.isChecked();
        record.lightSensitivity = binding.cbLightSensitivity.isChecked();
        record.soundSensitivity = binding.cbSoundSensitivity.isChecked();
        record.dizziness = binding.cbDizziness.isChecked();

        // Triggers
        record.stress = binding.cbStress.isChecked();
        record.sleepDeprivation = binding.cbSleepDeprivation.isChecked();
        record.screenTime = binding.cbScreenTime.isChecked();
        record.coffee = binding.cbCoffee.isChecked();
        record.chocolate = binding.cbChocolate.isChecked();
        record.skippedMeals = binding.cbSkippedMeals.isChecked();

        // Notes and Medication
        record.notes = binding.etNotes.getText().toString();
        record.medication = binding.etMedication.getText().toString();

        migraineViewModel.insert(record);

        Toast.makeText(getContext(), "Migraine logged successfully", Toast.LENGTH_SHORT).show();
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
