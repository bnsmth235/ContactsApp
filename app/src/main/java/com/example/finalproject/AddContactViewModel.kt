package com.example.finalproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel(){
    val fnError = MutableLiveData(false)
    val lnError = MutableLiveData(false)
    val phoneError = MutableLiveData(false)
    val emailError = MutableLiveData(false)
    val done = MutableLiveData(false)

    fun createContact(firstName: String, lastName: String, phone: String, email: String){
        fnError.value = false
        lnError.value = false
        phoneError.value = false
        emailError.value = false

        if(firstName.trim().isEmpty()){
            fnError.value = true
        }
        if(lastName.trim().isEmpty()){
            lnError.value = true
        }

        if(phone.trim().isEmpty() || email.trim().isEmpty()){

            if(phone.trim().isEmpty()){
                phoneError.value = true
            }
            if(email.trim().isEmpty()){
                emailError.value = true
            }
        }
        if(fnError.value == true || lnError.value == true || phoneError.value == true || emailError.value == true) {
            return
        }

        //save
        viewModelScope.launch {
            val contact = Contact(id = 0, firstName, lastName, phone, email)
            ContactsRepo.createContact(contact)
            done.value = true
        }

    }
}