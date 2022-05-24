package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.TextView

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val sv : ScrollView? = findViewById(R.id.scroll)
        val tv_rule = findViewById<TextView>(R.id.tv_rule)
        tv_rule.setMovementMethod(ScrollingMovementMethod()) // 스크롤 가능하게 해주는 부분
        tv_rule?.setOnTouchListener { v, event ->
            sv?.requestDisallowInterceptTouchEvent(true)//부모 scroll 권한 빼는 부분
            return@setOnTouchListener false
        }
    }
}