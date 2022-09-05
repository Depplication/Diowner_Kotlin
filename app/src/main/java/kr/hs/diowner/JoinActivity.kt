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

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.NextBtn1.setOnClickListener {

            var intent = Intent(this, JoinActivity2::class.java);
            intent.putExtra("name", binding.RName1.text.toString());
            intent.putExtra("id", binding.userId1.text.toString());
            intent.putExtra("PW1", binding.PW1.text.toString());
            intent.putExtra("PW2", binding.CPW1.text.toString());
            intent.putExtra("SHOP", binding.userSHOP1.text.toString());
            startActivity(intent);
        }


    }


}