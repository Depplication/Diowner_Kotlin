package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.Adapter.PointLogAdapter
import kr.hs.diowner.data.PointLogData
import kr.hs.diowner.databinding.ActivityPointLogBinding

class PointLogActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPointLogBinding

    private val mDatas = mutableListOf<PointLogData>(
        PointLogData(20220805, "2208", "turtle", 14),
        PointLogData(20220806, "0105", "cat", 14),
        PointLogData(20220807, "1908", "dinosaur", 14)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()
        initDataRecyclerView()
        addDataRecyclerView()
    }

    private fun settingListener() {
        binding.backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.backBtn -> {
                finish()
            }
        }
    }

    private fun initDataRecyclerView() {
        val adapter = PointLogAdapter(this)
        adapter.dataList = mDatas
        binding.logRecycler.adapter = adapter
        binding.logRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun addDataRecyclerView() {
        //TODO 서버값
    }
}