package com.example.smaplenotification

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.smaplenotification.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission(this)

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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun requestPermission(context: Context) {
    TedPermission.create()
        .setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    context,
                    "permission request",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        .setDeniedMessage("Please grant permission. [Settings] > [Apps & notifications] > [Advanced] > [App permissions]")
        .setPermissions(
            android.Manifest.permission.POST_NOTIFICATIONS,
        )
        .check()
}