package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.hs.diowner.databinding.ActivityTermsOfUseBinding

class TermsOfUse : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingViews()
        settingListener()
    }

    private fun settingViews() {
        binding.startBtn.isEnabled = binding.checkbox.isChecked
    }

    private fun settingListener() {
        binding.backBtn.setOnClickListener(this)
        binding.assentLayout.setOnClickListener(this)
        binding.checkImg.setOnClickListener(this)
        binding.startBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v) {
            binding.backBtn -> {
                finish()
            }
            binding.assentLayout -> {
                binding.checkbox.isChecked = !binding.checkbox.isChecked
            }
            binding.checkImg -> {
                //TODO 이용약관 동의 웹뷰 띄워서 보여주기
            }
            binding.startBtn -> {
                //TODO 회원가입창으로 intent
            }
        }
    }

}