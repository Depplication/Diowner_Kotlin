package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*

class JoinActivity : AppCompatActivity() {
    val RuleCheck : CheckBox by lazy {
        findViewById(R.id.check)
    }
    val JoinBtn : Button by lazy {
        findViewById(R.id.Join_Btn)
    }
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
        tv_rule?.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrolly ->
            if (!tv_rule.canScrollVertically(1)) {
                RuleCheck.isEnabled = true
                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
            }
        }
        JoinBtn.setOnClickListener {
            if(RuleCheck.isChecked){
                Toast.makeText(this, "동의 하셨습니다", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "이용약관에 동의해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}