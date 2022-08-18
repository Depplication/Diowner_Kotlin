package kr.hs.diowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.Adapter.BankAdapter
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
        BankData("kakao", "카카오뱅크"),
        BankData("ibk", "기업은행"),
        BankData("shinhan", "신한은행"),
        BankData("woori", "우리은행"),
        BankData("hana", "하나은행"),
        BankData("bnk", "경남은행"),
        BankData("dgb", "대구은행"),
        BankData("bk", "뱅크오브아메리카"),
        BankData("sj", "산림조합중앙회"),
        BankData("kcc", "새마을금고"),
        BankData("sin", "신협중앙회"),
        BankData("jb", "전북은행"),
        BankData("ccb", "중국건설은행"),
        BankData("bnp", "BNP파리바은행"),
        BankData("jp", "JP모간체이스"),
        BankData("kbank", "케이뱅크")

    )
    private val bank2Datas = arrayListOf<BankData>(
        BankData("kb", "국민은행"),
        BankData("nh", "농협은행"),
        BankData("su", "산업은행"),
        BankData("kciti", "한국씨티은행"),
        BankData("sc", "SC제일은행"),
        BankData("ju", "광주은행"),
        BankData("doe", "도이치은행"),
        BankData("boo", "부산은행"),
        BankData("sb", "저축은행"),
        BankData("sh", "수협은행"),
        BankData("epost", "우체국"),
        BankData("jeju", "제주은행"),
        BankData("cgong", "중국공산은행"),
        BankData("china", "중국은행"),
        BankData("hsbc", "HSBC은행"),
        BankData("toss", "토스뱅크")
    )
}