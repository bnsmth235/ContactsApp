package com.example.finalproject

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getContactsDao(): ContactsDao
}