package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val My_Btn : Button by lazy {
        findViewById(R.id.my_btn)
    }
    val Register_Btn : Button by lazy {
        findViewById(R.id.register)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingListener()
    }
    private fun settingListener(){
        My_Btn.setOnClickListener(this)
        Register_Btn.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        when(view){
            My_Btn -> {
                val intent = Intent(this, MyActivity::class.java)
                startActivity(intent)
            }
            Register_Btn -> {
                val intent = Intent(this, Register_Btn::class.java)
                startActivity(intent)
            }
        }
    }
}