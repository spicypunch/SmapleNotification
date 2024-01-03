package com.example.smaplenotification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.smaplenotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncomingCall.setOnClickListener {
            startNotification(1)
        }

        binding.btnOngoingCall.setOnClickListener {
            startNotification(2)
        }

        binding.btnScreeningCall.setOnClickListener {
            startNotification(3)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun startNotification(notificationId: Int) {
        val serviceIntent = Intent(this, CallService::class.java)
        serviceIntent.putExtra("NOTIFICATION_ID", notificationId)
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}