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
import kr.hs.diowner.data.OwnerData
import kr.hs.diowner.databinding.ActivityJoin2Binding
import kr.hs.diowner.databinding.ActivityJoinBinding
import kr.hs.diowner.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class JoinActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityJoin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()


    }

    private fun JoinPost(ownerData: OwnerData) {
        RetrofitBuilder.api.JoinPost(ownerData).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    intent()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }

    private fun intent() {
        val intent = Intent(this, LoginActivity::class.java)
        finishAffinity()
        startActivity(intent)
    }

    private fun settingListener() {
        binding.JoinBtn1.setOnClickListener(this)
        binding.backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.JoinBtn1 -> {
                val name = intent.getStringExtra("name").toString()
                val id = intent.getStringExtra("id").toString()
                val pw = intent.getStringExtra("PW").toString()
                val shop = intent.getStringExtra("SHOP").toString()

                val pn = binding.userPNum1.text.toString()
                val add = binding.userAdd1.text.toString()
                val bank = binding.userBank1.text.toString()
                val acn = binding.Acn1.text.toString()

                Log.d("JOINTAG", id)
                Log.d("JOINTAG", pw)

                val data = OwnerData(add, name, pn, id, pw, shop)

                JoinPost(data)

            }
            binding.backBtn -> {
                finish()
            }
        }

    }


}