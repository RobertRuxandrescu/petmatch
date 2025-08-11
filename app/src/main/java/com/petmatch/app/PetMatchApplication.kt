package com.petmatch.app

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetMatchApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
