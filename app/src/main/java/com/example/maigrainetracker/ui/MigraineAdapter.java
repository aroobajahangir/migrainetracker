package com.example.maigrainetracker.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.maigrainetracker.R;
import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.databinding.ItemMigraineRecordBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MigraineAdapter extends ListAdapter<MigraineRecord, MigraineAdapter.RecordViewHolder> {

    private OnItemClickListener listener;

    public MigraineAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<MigraineRecord> DIFF_CALLBACK = new DiffUtil.ItemCallback<MigraineRecord>() {
        @Override
        public boolean areItemsTheSame(@NonNull MigraineRecord oldItem, @NonNull MigraineRecord newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MigraineRecord oldItem, @NonNull MigraineRecord newItem) {
            return oldItem.recordDate == newItem.recordDate && oldItem.painLevel == newItem.painLevel;
        }
    };

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMigraineRecordBinding binding = ItemMigraineRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        MigraineRecord currentRecord = getItem(position);
        holder.bind(currentRecord);
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {
        private final ItemMigraineRecordBinding binding;

        public RecordViewHolder(ItemMigraineRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        public void bind(MigraineRecord record) {
            binding.tvIntensityValue.setText(String.format(Locale.getDefault(), "%d/10", record.painLevel));

            if (record.painLevel >= 6) {
                binding.tvMigraineStatus.setText("High");
                binding.tvMigraineStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.status_high));
            } else {
                binding.tvMigraineStatus.setText("Low");
                binding.tvMigraineStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.status_low));
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

            binding.tvRecordDate.setText(dateFormat.format(new Date(record.recordDate)));
            binding.tvRecordTime.setText(timeFormat.format(new Date(record.recordDate)));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MigraineRecord record);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
