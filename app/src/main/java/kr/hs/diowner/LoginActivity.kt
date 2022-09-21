package kr.hs.diowner

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kr.hs.diowner.data.LoginResponseData
import kr.hs.diowner.data.OwnerData
import kr.hs.diowner.data.OwnerLoginData
import kr.hs.diowner.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    val MainTitle: TextView by lazy {
        findViewById(R.id.Main_Title)
    }
    val JoinText: TextView by lazy {
        findViewById(R.id.Join_Text)
    }
    val LoginBtn: Button by lazy {
        findViewById(R.id.Login_Btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.prefs = Prefs(applicationContext)
        setting()
        settingListener()
    }

    private fun setting() {
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
    private fun settingListener() {
        LoginBtn.setOnClickListener(this)
        JoinText.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            LoginBtn -> {
                //TODO 로그인 구현
                val data = OwnerLoginData(binding.IdET.text.toString(), binding.PwET.text.toString())
                LoginPost(data)
            }
            JoinText -> {
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun LoginPost(ownerLoginData: OwnerLoginData) {
        RetrofitBuilder.api.LoginPost(ownerLoginData).enqueue(object :
            Callback<LoginResponseData> {
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body() // GsonConverter를 사용해 데이터매핑
                    App.prefs.token = data!!.tokenData.token
                    App.prefs.id = data.ownerData.id
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    //Log.d("testasd", data)
                }
            }

            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }
}

