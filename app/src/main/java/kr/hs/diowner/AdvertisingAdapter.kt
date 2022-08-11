package kr.hs.diowner

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.diowner.data.AdvertisingData
import kr.hs.diowner.databinding.MyAdListBinding

class AdvertisingAdapter(val context: Context) :
    RecyclerView.Adapter<AdvertisingAdapter.MyViewHolder>() {
    var dataList = mutableListOf<AdvertisingData>()

    inner class MyViewHolder(private val binding: MyAdListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdvertisingData) {
            binding.title.text = data.title.toString()
            binding.modify.setOnClickListener {
                //TODO 수정 구현
                Intent(context, RegisterActivity::class.java).apply {
                    putExtra("data", data)
                }.run { context.startActivity(this) }
            }
            binding.trashcan.setOnClickListener {
                //TODO 삭제 구현
                removeItem(position)
                Log.d("recyclerTest", position.toString())
            }
        }
    }

    private fun removeItem(position: Int){
        dataList.removeAt(position)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MyAdListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}