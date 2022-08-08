package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.hs.diowner.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingView()
        settingListener()
    }

    private fun settingView(){
        binding.tvName.bringToFront()
        binding.tvShopName.bringToFront()
        binding.tvPhoneNum.bringToFront()
        binding.tvAddress.bringToFront()
        binding.tvBank.bringToFront()
        binding.tvAccount.bringToFront()
    }
    private fun settingListener(){
        binding.backBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v) {
            binding.backBtn -> {
                finish()
            }
        }
    }


}