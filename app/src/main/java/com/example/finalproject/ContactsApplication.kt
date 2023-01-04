package com.example.finalproject

import android.app.Application

class ContactsApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun getInstance(): Application {
            return instance!!
        }
    }
}