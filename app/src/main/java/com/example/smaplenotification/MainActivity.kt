package com.example.smaplenotification

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.smaplenotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CallService().startService(this)

        binding.btnIncomingCall.setOnClickListener {
            CallService().notification1(this)
        }

        binding.btnOngoingCall.setOnClickListener {
            CallService().notification2(this)
        }

        binding.btnScreeningCall.setOnClickListener {
            CallService().notification3(this)
        }
    }
}