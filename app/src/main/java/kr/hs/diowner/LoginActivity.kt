package kr.hs.diowner

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kr.hs.diowner.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    val MainTitle : TextView by lazy {
        findViewById(R.id.Main_Title)
    }
    val JoinText : TextView by lazy {
        findViewById(R.id.Join_Text)
    }
    val LoginBtn : Button by lazy {
        findViewById(R.id.Login_Btn)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setting()
        settingListener()
    }
    private fun setting(){
        binding.tvId.bringToFront()
        binding.tvPw.bringToFront()
        binding.JoinText.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
    /*private fun settingText(){
        JoinText.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        val ssb = SpannableStringBuilder("Diowner")
        ssb.apply {
            setSpan(ForegroundColorSpan(getColor(R.color.MainColor)), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        MainTitle.text = ssb
    }*/
    private fun settingListener(){
        LoginBtn.setOnClickListener(this)
        JoinText.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        when(view){
            LoginBtn -> {
                //TODO 로그인 구현
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            JoinText -> {
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

