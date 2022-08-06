package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.diowner.data.PointData
import kr.hs.diowner.databinding.ActivityMyBinding

class MyActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener {
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
        settingListener()
    }

    private fun settingListener() {
        binding.settingImg.setOnClickListener(this)
        binding.myAdLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //TODO 메뉴구현하기
        when (v) {
            binding.settingImg -> {
                showPopup(binding.settingImg)
            }
        }
        //TODO 내 광고 화면 만들고 이어주기
    }

    private fun showPopup(v: View) {
        val themeWrapper = ContextThemeWrapper(this, R.style.PopupMenu)
        val popup = PopupMenu(themeWrapper, v, Gravity.NO_GRAVITY, 0, R.style.PopupMenu)
        popup.menu.add(0, 0,0, "정보수정")
        popup.menu.add(0, 1, 1, "로그아웃")
        popup.setOnMenuItemClickListener(this)
        popup.show()
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            0 -> Intent(this, ModifyActivity::class.java).run {
                startActivity(this) }
            1 -> Intent(this, LoginActivity::class.java).run {
                startActivity(this)
                finish() }
        }
        return item != null
    }
}