package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kr.hs.diowner.data.AdvertisingResponseData
import kr.hs.diowner.data.OwnerData
import kr.hs.diowner.data.OwnerResponseData
import kr.hs.diowner.data.PostAdvertisingData
import kr.hs.diowner.databinding.ActivityModifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ModifyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingView()
        settingListener()
    }

    private fun settingView() {
        binding.tvName.bringToFront()
        binding.tvShopName.bringToFront()
        binding.tvPhoneNum.bringToFront()
        binding.tvAddress.bringToFront()
        binding.tvBank.bringToFront()
        binding.tvAccount.bringToFront()
        //TODO 서버에서 값받아서 기본값 세팅
    }

    private fun settingListener() {
        binding.backBtn.setOnClickListener(this)
        binding.searchBtn.setOnClickListener(this)
        binding.changeBtn.setOnClickListener(this)
        binding.choiceBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.backBtn -> {
                finish()
            }
            binding.searchBtn -> {
                //TODO 다음 우편번호 api 적용하기
            }
            binding.changeBtn -> {
                checkModify()
            }
            binding.choiceBtn -> {
                val intent = Intent(this, ChoiceBank::class.java).apply {
                    putExtra("data", "modify")
                }
                startActivity(intent)
            }
        }
    }

    private fun checkModify(){
        if(binding.nameET.text.isNullOrBlank()){
            binding.nameET.requestFocus()
            Toast.makeText(this, "대표자명란이 비었습니다.", Toast.LENGTH_SHORT).show()
        }else if(binding.shopNameET.text.isNullOrBlank()){
            binding.shopNameET.requestFocus()
            Toast.makeText(this, "상점 명란이 비었습니다.", Toast.LENGTH_SHORT).show()
        }else if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$", binding.phoneNumET.text.toString())) {
            binding.phoneNumET.requestFocus()
            Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
        }else if(binding.addressET.text.isNullOrBlank()){
            binding.addressET.requestFocus()
            Toast.makeText(this, "주소란이 비었습니다.", Toast.LENGTH_SHORT).show()
        }else if(false){
            //TODO 은행선택
        }else if(binding.accountET.text.isNullOrBlank()){
            binding.accountET.requestFocus()
            Toast.makeText(this, "계좌번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }else {
            //서버에 포스트



//            var address = ""
//            var name = ""
//            var number = ""
//            var ownerId = ""
//            var password = ""
//            val store = ""
//            modifyOwner()
        }
    }

    private fun modifyOwner(ownerData: OwnerData) {
        RetrofitBuilder.api.modifyOwner(App.prefs.id, ownerData).enqueue(object :
            Callback<OwnerResponseData> {
            override fun onResponse(
                call: Call<OwnerResponseData>,
                response: Response<OwnerResponseData>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    App.prefs.name = response.body()!!.name
                    Toast.makeText(applicationContext, "정보가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<OwnerResponseData>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }


}