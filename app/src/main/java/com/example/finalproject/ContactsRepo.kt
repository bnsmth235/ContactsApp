package com.example.finalproject

import androidx.room.Room

object ContactsRepo {
    private val db: AppDatabase;
    private var cacheInitialized = false;
    private val contactsCache = mutableListOf<Contact>()

    init{
        db = Room.databaseBuilder(
            ContactsApplication.getInstance(),
            AppDatabase::class.java,
            "contacts-db"
        ).build()
    }

    suspend fun createContact(contact: Contact){
        contact.id = db.getContactsDao().createContact(contact)
        contactsCache.add(contact)
    }

    suspend fun getAllContacts(): List<Contact>{
        if(!cacheInitialized){
            contactsCache.addAll(db.getContactsDao().getAllContacts())
            cacheInitialized = true
        }
        return contactsCache
    }

    suspend fun deleteContact(contact: Contact){
        db.getContactsDao().deleteContact(contact)
        contactsCache.remove(contact)
    }

}
