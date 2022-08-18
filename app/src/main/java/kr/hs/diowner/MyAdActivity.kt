package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.Adapter.AdvertisingAdapter
import kr.hs.diowner.data.AdvertisingData
import kr.hs.diowner.databinding.ActivityMyAdBinding

class MyAdActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMyAdBinding
    private val mDatas = mutableListOf<AdvertisingData>(
        AdvertisingData("안녕하세요"),
        AdvertisingData("짜장면 광고입니다."),
        AdvertisingData("광고를 이렇게 많이 올릴까?"),
        AdvertisingData("저도 의문입니다.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addDataRecyclerView()
        initDataRecyclerView()
        settingListener()
    }
    private fun settingListener() {
        binding.backBtn.setOnClickListener(this)
        binding.addAdLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.backBtn -> {
                finish()
            }
            binding.addAdLayout -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initDataRecyclerView() {
        val adapter = AdvertisingAdapter(this)
        adapter.dataList = mDatas
        binding.myAdRecycler.adapter = adapter
        binding.myAdRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun addDataRecyclerView() {
        //TODO 서버값
    }
}