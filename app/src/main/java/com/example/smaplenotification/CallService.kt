package com.example.smaplenotification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Person
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat

@RequiresApi(Build.VERSION_CODES.P)
class CallService : Service() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @SuppressLint("ForegroundServiceType")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
    }

    fun startService(context: Context) {
        val intent = Intent(context, CallService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }

    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                "1",
                "Channel 1",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel1)

            val channel2 = NotificationChannel(
                "2",
                "Channel 2",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel2)

            val channel3 = NotificationChannel(
                "3",
                "Channel 3",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel3)
        }
        // 알림 생성
        val notification: Notification = Notification.Builder(this, "1")
            .setSmallIcon(R.drawable.baseline_catching_pokemon_24) //알림 아이콘
            .setContentTitle("뮤직 플레이어 앱") //알림의 제목 설정
            .setContentText("앱이 실행 중입니다.") //알림의 내용 설정
            .build()

        startForeground(1, notification) //인수로 알림 ID와 알림 지정

        return START_NOT_STICKY
    }

    @SuppressLint("ForegroundServiceType")
    fun notification1(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val declineIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val answerIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val incomingCaller = Person.Builder()
            .setName("김종민")
            .setImportant(true)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val builder = Notification.Builder(context, "1")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
                .setStyle(
                    Notification.CallStyle.forIncomingCall(incomingCaller, declineIntent, answerIntent)
                )
                .addPerson(incomingCaller)
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(1, builder.build())
            }
        }
    }

    @SuppressLint("ForegroundServiceType")
    fun notification2(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val hangupIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val incomingCaller = Person.Builder()
            .setName("김종민")
            .setImportant(true)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val builder = Notification.Builder(context, "2")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
                .setStyle(
                    Notification.CallStyle.forOngoingCall(incomingCaller, hangupIntent)
                )
                .addPerson(incomingCaller)
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(1, builder.build())
            }
        }
    }

    @SuppressLint("ForegroundServiceType")
    fun notification3(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val answerIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val hangupIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val incomingCaller = Person.Builder()
            .setName("김종민")
            .setImportant(true)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val builder = Notification.Builder(context, "3")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.baseline_catching_pokemon_24)
                .setStyle(
                    Notification.CallStyle.forScreeningCall(incomingCaller, hangupIntent, answerIntent))
                .addPerson(incomingCaller)
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(2, builder.build())
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}