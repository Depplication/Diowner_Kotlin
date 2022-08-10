package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.data.AdvertisingData
import kr.hs.diowner.databinding.ActivityMyAdBinding

class MyAdActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMyAdBinding

    private val mDatas = mutableListOf<AdvertisingData>(
        AdvertisingData("안녕하세요"),
        AdvertisingData("짜장면 광고입니다.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDataRecyclerView()
        addDataRecyclerView()
        settingLstener()
    }
    private fun settingLstener() {
        binding.backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.backBtn -> {
                finish()
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