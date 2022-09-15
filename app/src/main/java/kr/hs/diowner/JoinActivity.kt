package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import kr.hs.diowner.databinding.ActivityJoinBinding
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()
    }

    private fun settingListener() {
        binding.NextBtn1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.NextBtn1 -> {
                Intent(this, JoinActivity2::class.java).run {
                    putExtra("name", binding.RName1.text.toString())
                    putExtra("id", binding.userId1.text.toString())
                    putExtra("PW", binding.userPW1.text.toString())
                    putExtra("SHOP", binding.userSHOP1.text.toString())
                    finish()
                    startActivity(this)
                }
            }
        }
    }

}