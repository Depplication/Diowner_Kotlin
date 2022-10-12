package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kr.hs.diowner.data.OwnerResponseData
import kr.hs.diowner.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var backpressedTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getOwnerData()
        //TODO ID값 받아서 이름 바꿔주기
        settingListener()

    }

    private fun settingListener() {
        binding.myLayout.setOnClickListener(this)
        binding.adLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
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
        if (System.currentTimeMillis() - backpressedTime >= 1500) {
            backpressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }

    private fun getOwnerData() {
        binding.tvTitle.text = App.prefs.name + "님\n안녕하세요!"
        var pointData = pointCal(App.prefs.point.toString())
        binding.tvPoint.text = pointData + "P"

//        RetrofitBuilder.api.getOwnerData(App.prefs.id).enqueue(object :
//            Callback<OwnerResponseData> {
//            override fun onResponse(
//                call: Call<OwnerResponseData>,
//                response: Response<OwnerResponseData>,
//            ) {
//                Log.d("testasd", response.toString())
//                if (response.isSuccessful) {
//                    Log.d("testasd", response.body().toString())
//                    var data = response.body() // GsonConverter를 사용해 데이터매핑
//                    binding.tvTitle.text = data!!.name + "님\n안녕하세요!"
//                    var pointData = pointCal(data.point.toString())
//                    binding.tvPoint.text = pointData + "P"
//                    //Log.d("testasd", data)
//                }
//            }
//
//            override fun onFailure(call: Call<OwnerResponseData>, t: Throwable) {
//                Log.d("testasd", "실패$t")
//            }
//
//        })
    }
    private fun pointCal(point: String): String {
//        if(point.isNotEmpty()){
//            val result = ""
//            for(i in point.length-1 downTo 0){
//
//            }
//        } else {
//            return point
//        }
        var result: String = ""
        var pointResult: String = ""
        for(i in point.length-1 downTo 0){
            if(i != 0 && i % 3 == 0){
                result = "$result,"
            }
            result += point[i]
        }
        for(i in result.length - 1 downTo 0){
            pointResult += result[i]
        }
        return pointResult
    }


}