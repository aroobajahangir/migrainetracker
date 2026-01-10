package com.example.maigrainetracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// @Dao annotation batata hai ke yeh ek Data Access Object hai.
@Dao
public interface MigraineDao {

    // OnConflictStrategy.REPLACE ka matlab hai agar ID pehle se mojood hai to usay update kar do.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MigraineRecord record);

    @Update
    void update(MigraineRecord record);

    @Delete
    void delete(MigraineRecord record);

    @Query("DELETE FROM migraine_records")
    void deleteAllRecords();

    @Query("SELECT * FROM migraine_records ORDER BY recordDate DESC")
    LiveData<List<MigraineRecord>> getAllRecords();

    @Query("SELECT * FROM migraine_records WHERE id = :id")
    LiveData<MigraineRecord> getRecordById(int id);

    @Query("SELECT * FROM migraine_records ORDER BY recordDate DESC LIMIT 1")
    LiveData<MigraineRecord> getLatestMigraineRecord();

    @Query("SELECT * FROM migraine_records")
    LiveData<List<MigraineRecord>> getAllRecordsForStats();
}
