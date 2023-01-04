package com.example.finalproject

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class ContactsViewModel: ViewModel(){
    val contacts = ObservableArrayList<Contact>()

    init {
        loadContacts()
    }

    private fun loadContacts(){
        viewModelScope.launch{
            val loadedContacts = ContactsRepo.getAllContacts()
            contacts.addAll(loadedContacts)
        }
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            ContactsRepo.deleteContact(contact)
        }
    }


}