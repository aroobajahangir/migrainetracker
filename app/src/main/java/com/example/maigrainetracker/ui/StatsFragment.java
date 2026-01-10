package com.example.maigrainetracker.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maigrainetracker.R;
import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.databinding.FragmentStatsBinding;
import com.example.maigrainetracker.viewmodel.MigraineViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;
    private MigraineViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAllRecordsForStats().observe(getViewLifecycleOwner(), this::setupChart);
    }

    private void setupChart(List<MigraineRecord> records) {
        if (records == null || records.isEmpty()) {
            binding.pieChart.setNoDataText("No data available to display chart.");
            return;
        }

        int highCount = 0;
        int lowCount = 0;

        for (MigraineRecord record : records) {
            if (record.painLevel >= 6) {
                highCount++;
            } else {
                lowCount++;
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(highCount, "High"));
        entries.add(new PieEntry(lowCount, "Low"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{R.color.status_high, R.color.status_low}, getContext());
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(binding.pieChart));

        binding.pieChart.setData(data);
        binding.pieChart.setUsePercentValues(true);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.setDrawHoleEnabled(true);
        binding.pieChart.setHoleColor(Color.TRANSPARENT);
        binding.pieChart.setTransparentCircleRadius(61f);
        binding.pieChart.getLegend().setEnabled(false);
        binding.pieChart.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
