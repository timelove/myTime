package com.chicbian.app.mytime

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.blankj.utilcode.util.TimeUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val HH_MM_SS: DateFormat = object : SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {}
        private var time: Timer? = null
        private val FONT_DIGITAL_7 = ("digital-7.ttf")
        private var m_wklk: WakeLock? = null
    }

    @SuppressLint("WakelockTimeout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        time = object : Timer() {}
        time?.schedule(timeTask, 0, 1000);


        val pm: PowerManager = getSystemService(android.content.Context.POWER_SERVICE) as PowerManager
        m_wklk = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "cn")
        //设置保持唤醒
        m_wklk?.acquire()

    }

    private val timeTask: TimerTask? = object : TimerTask() {
        @SuppressLint("SetTextI18n")
        override fun run() {
            val times = TimeUtils.getNowString()
            val week = TimeUtils.getUSWeek(times)

            runOnUiThread {
                tv_current_time.text = times.substring(11, times.length)
                tv_current_date.text = times.substring(5, 9) + " " + week
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        time?.cancel()
        m_wklk?.release(); //解除保持唤醒
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        m_wklk?.acquire(); //设置保持唤醒
    }
}
