package com.example.maigrainetracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maigrainetracker.R;
import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.databinding.FragmentHomeBinding;
import com.example.maigrainetracker.viewmodel.MigraineViewModel;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MigraineViewModel viewModel;
    private MigraineAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        viewModel.getAllRecords().observe(getViewLifecycleOwner(), records -> {
            adapter.submitList(records);
            binding.tvEmptyView.setVisibility(records.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    private void setupRecyclerView() {
        adapter = new MigraineAdapter();
        binding.recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                MigraineRecord recordToDelete = adapter.getCurrentList().get(position);
                viewModel.delete(recordToDelete);

                Snackbar.make(binding.getRoot(), "Record deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", v -> viewModel.insert(recordToDelete))
                        .show();
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
