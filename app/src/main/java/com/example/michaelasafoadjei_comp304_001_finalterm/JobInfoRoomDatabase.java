package com.example.michaelasafoadjei_comp304_001_finalterm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {JobInfo.class}, version = 1)
public abstract class JobInfoRoomDatabase extends RoomDatabase {
    public abstract JobInfoDao jobInfoDao();
    private static JobInfoRoomDatabase INSTANCE;
    //
    static JobInfoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (JobInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), JobInfoRoomDatabase.class, "JobInfoDB").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}






