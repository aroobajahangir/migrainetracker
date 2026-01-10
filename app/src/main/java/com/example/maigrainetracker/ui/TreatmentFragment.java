package com.example.maigrainetracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maigrainetracker.databinding.FragmentTreatmentBinding;
import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.viewmodel.MigraineViewModel;
import com.example.maigrainetracker.treatment.TreatmentEngine;

public class TreatmentFragment extends Fragment {

    private FragmentTreatmentBinding binding;
    private MigraineViewModel migraineViewModel;
    private TreatmentEngine treatmentEngine;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTreatmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        migraineViewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
        treatmentEngine = new TreatmentEngine();

        binding.btnGetTreatment.setOnClickListener(v -> getTreatment());
    }

    private void getTreatment() {
        migraineViewModel.getLatestMigraineRecord().observe(getViewLifecycleOwner(), this::displayTreatment);
    }

    private void displayTreatment(MigraineRecord record) {
        String advice = treatmentEngine.getTreatmentAdvice(record);
        binding.tvTreatmentAdvice.setText(advice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
