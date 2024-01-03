package com.example.smaplenotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Person
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class CallService : Service() {

    private val CHANNEL_ID_1 = "1"
    private val CHANNEL_ID_2 = "2"
    private val CHANNEL_ID_3 = "3"

    val intent = Intent(this, MainActivity::class.java)
    val contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    val declineIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    val answerIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    val hangupIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    val incomingCaller = Person.Builder()
        .setName("김종민")
        .setImportant(true)
        .build()
    val notificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_ID_1,
                "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel1)

            val channel2 = NotificationChannel(
                CHANNEL_ID_2,
                "Channel 2",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel2)

            val channel3 = NotificationChannel(
                CHANNEL_ID_3,
                "Channel 3",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel3)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_NOT_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun notification1() {
        val builder = Notification.Builder(this, "1")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
            .setStyle(
                Notification.CallStyle.forIncomingCall(incomingCaller, declineIntent, answerIntent)
            )
            .addPerson(incomingCaller)
        notificationManager.notify(1, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun notification2() {
        val builder = Notification.Builder(this, "2")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
            .setStyle(
                Notification.CallStyle.forOngoingCall(incomingCaller, hangupIntent))
            .addPerson(incomingCaller)
        notificationManager.notify(2, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun notification3() {
        val builder = Notification.Builder(this, "3")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
            .setStyle(
                Notification.CallStyle.forScreeningCall(incomingCaller, hangupIntent, answerIntent))
            .addPerson(incomingCaller)
        notificationManager.notify(3, builder.build())
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}