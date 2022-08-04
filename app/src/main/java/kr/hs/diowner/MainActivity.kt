package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kr.hs.diowner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
   private lateinit var binding: ActivityMainBinding
    private var backpressedTime : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()
    }
    private fun settingListener(){
        binding.myLayout.setOnClickListener(this)
        binding.adLayout.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        when(view){
            binding.myLayout -> {
                val intent = Intent(this, MyActivity::class.java)
                startActivity(intent)
            }
            binding.adLayout -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - backpressedTime >= 1500 ){
            backpressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }else{
            finish()
        }
    }


}