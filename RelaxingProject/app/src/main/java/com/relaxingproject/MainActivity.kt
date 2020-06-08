package com.relaxingproject

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.util.NotificationUt
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var alarmManager: AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmFun()

        this.meditateBtn.setOnClickListener {
            startActivity(Intent(this,MeditateActivity::class.java))
        }

        this.logBtn.setOnClickListener {
            startActivity(Intent(this,LogActivity::class.java))
        }

        this.logHistBtn.setOnClickListener {
            startActivity(Intent(this,LogHistoryActivity::class.java))
        }

        this.medHistBtn.setOnClickListener {
            startActivity(Intent(this,MedHistoryActivity::class.java))
        }
    }

    fun alarmFun(){
        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val second = 86400 * 1000;
        val intent = Intent(context, Receiver:: class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, pendingIntent)
        Log.d("MainActivity", "Create : " + Date().toString())
    }

     class Receiver : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("MainActivity", "Receiver : " + Date().toString())
            NotificationUt.notifFun(context)
        }
    }
}
