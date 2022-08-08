package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.hs.diowner.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.bringToFront()
    }
}