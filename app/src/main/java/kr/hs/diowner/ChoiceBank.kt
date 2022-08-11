package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.data.BankData
import kr.hs.diowner.databinding.ActivityChoiceBankBinding

class ChoiceBank : AppCompatActivity() {
    private lateinit var binding: ActivityChoiceBankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataRecyclerView()
    }

    private fun initDataRecyclerView(){
        val data = intent.getStringExtra("data")
        val adapter = BankAdapter(this, bank1Datas, data!!.toString())
        binding.bankRecycler1.adapter = adapter
        binding.bankRecycler1.layoutManager = LinearLayoutManager(this)
        val madapter = BankAdapter(this, bank2Datas, data!!.toString())
        binding.bankRecycler2.adapter = madapter
        binding.bankRecycler2.layoutManager = LinearLayoutManager(this)
    }

    private val bank1Datas = arrayListOf<BankData>(
        BankData("kakao", "카카오뱅크")
    )
    private val bank2Datas = arrayListOf<BankData>(

    )
}