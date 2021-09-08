package com.example.michaelasafoadjei_comp304_001_finalterm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskRoomDatabase extends RoomDatabase {
    //
    public abstract TaskDao taskDao();
    private static TaskRoomDatabase INSTANCE;
    //
    static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabase.class, "TaskDB").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}//
