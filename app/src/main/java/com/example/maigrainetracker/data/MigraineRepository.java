package com.example.maigrainetracker.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MigraineRepository {

    private final MigraineDao migraineDao;
    private final LiveData<List<MigraineRecord>> allRecords;
    private final ExecutorService executorService;

    public MigraineRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        migraineDao = db.migraineDao();
        allRecords = migraineDao.getAllRecords();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<MigraineRecord>> getAllRecords() {
        return allRecords;
    }

    public void insert(MigraineRecord record) {
        executorService.execute(() -> migraineDao.insert(record));
    }

    public void update(MigraineRecord record) {
        executorService.execute(() -> migraineDao.update(record));
    }

    public void delete(MigraineRecord record) {
        executorService.execute(() -> migraineDao.delete(record));
    }

    public void deleteAllRecords() {
        executorService.execute(migraineDao::deleteAllRecords);
    }

    public LiveData<MigraineRecord> getRecordById(int id) {
        return migraineDao.getRecordById(id);
    }

    public LiveData<MigraineRecord> getLatestMigraineRecord() {
        return migraineDao.getLatestMigraineRecord();
    }

    public LiveData<List<MigraineRecord>> getAllRecordsForStats() {
        return migraineDao.getAllRecordsForStats();
    }
}
