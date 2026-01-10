package com.example.maigrainetracker.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// @Database annotation mein entities (tables) aur version number batate hain.
@Database(entities = {MigraineRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // Yeh abstract method DAO ka instance provide karega.
    public abstract MigraineDao migraineDao();

    // Singleton pattern istemal kar rahe hain taake poori app mein database ka sirf ek hi instance bane.
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "migraine_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
