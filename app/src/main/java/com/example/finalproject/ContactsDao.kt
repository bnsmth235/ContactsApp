package com.example.finalproject

import androidx.room.*

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contact")
    suspend fun getAllContacts(): List<Contact>

    @Insert
    suspend fun createContact(contact: Contact): Long

    @Delete
    suspend fun deleteContact(contact: Contact)
}