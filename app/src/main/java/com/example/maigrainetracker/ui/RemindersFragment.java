package com.example.maigrainetracker.ui;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.maigrainetracker.databinding.FragmentRemindersBinding;
import com.example.maigrainetracker.util.ReminderReceiver;

import java.util.Calendar;

public class RemindersFragment extends Fragment {

    private FragmentRemindersBinding binding;
    private final ActivityResultLauncher<String> requestPermissionLauncher = 
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            saveReminders();
        } else {
            Toast.makeText(getContext(), "Notification permission denied", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRemindersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSaveReminders.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) == 
                        PackageManager.PERMISSION_GRANTED) {
                    saveReminders();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                }
            } else {
                saveReminders();
            }
        });
    }

    private void saveReminders() {
        // Water Reminder
        if (binding.switchWaterReminder.isChecked()) {
            scheduleReminder(1, binding.etWaterReminderTime.getText().toString(), "Time to drink water!");
        } else {
            cancelReminder(1);
        }

        // Sleep Reminder
        if (binding.switchSleepReminder.isChecked()) {
            scheduleReminder(2, binding.etSleepReminderTime.getText().toString(), "Time to go to bed!");
        } else {
            cancelReminder(2);
        }

        // Meal Reminder
        if (binding.switchMealReminder.isChecked()) {
            scheduleReminder(3, binding.etMealReminderTime.getText().toString(), "Avoid caffeine, chocolate, and junk food before bed.");
        } else {
            cancelReminder(3);
        }

        // Medicine Reminder
        if (binding.switchMedicineReminder.isChecked()) {
            scheduleReminder(4, binding.etMedicineReminderTime.getText().toString(), "Time to take your medicine.");
        } else {
            cancelReminder(4);
        }

        Toast.makeText(getContext(), "Reminders saved", Toast.LENGTH_SHORT).show();
    }

    private void scheduleReminder(int requestCode, String time, String message) {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), ReminderReceiver.class);
        intent.putExtra("message", message);

        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, flags);

        String[] timeParts = time.split(":");
        if (timeParts.length != 2) {
            Toast.makeText(getContext(), "Invalid time format for reminder " + requestCode, Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void cancelReminder(int requestCode) {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), ReminderReceiver.class);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), requestCode, intent, flags);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
