package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.data.PointData
import kr.hs.diowner.databinding.ActivityMyBinding

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    val mDatas = mutableListOf<PointData>(
        PointData("hello123", 14.0),
        PointData("abc7777", 14.0),
        PointData("qwer456", 14.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDataRecyclerView()
        addDataRecyclerView()
    }

    private fun initDataRecyclerView() {
        val adapter = PointListAdapter(this)//어댑터 객체 만듬
        adapter.dataList = mDatas//데이터 넣어줌
        binding.recyclerView.adapter = adapter//리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager = LinearLayoutManager(this)//레이아웃 매니저 연결
    }

    private fun addDataRecyclerView() {
        //TODO 서버에서 값받아오기
        //mDatas.add(서버값)
    }
}