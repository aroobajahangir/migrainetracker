package com.example.maigrainetracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.maigrainetracker.data.MigraineRecord;
import com.example.maigrainetracker.data.MigraineRepository;

import java.util.List;

public class MigraineViewModel extends AndroidViewModel {

    private final MigraineRepository repository;
    private final LiveData<List<MigraineRecord>> allRecords;

    public MigraineViewModel(@NonNull Application application) {
        super(application);
        repository = new MigraineRepository(application);
        allRecords = repository.getAllRecords();
    }

    public void insert(MigraineRecord record) {
        repository.insert(record);
    }

    public void update(MigraineRecord record) {
        repository.update(record);
    }

    public void delete(MigraineRecord record) {
        repository.delete(record);
    }

    public void deleteAllRecords() {
        repository.deleteAllRecords();
    }

    public LiveData<List<MigraineRecord>> getAllRecords() {
        return allRecords;
    }

    public LiveData<MigraineRecord> getLatestMigraineRecord() {
        return repository.getLatestMigraineRecord();
    }

    public LiveData<MigraineRecord> getRecordById(int id) {
        return repository.getRecordById(id);
    }

    public LiveData<List<MigraineRecord>> getAllRecordsForStats() {
        return repository.getAllRecordsForStats();
    }
}
