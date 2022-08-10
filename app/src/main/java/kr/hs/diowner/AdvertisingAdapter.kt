package kr.hs.diowner

import android.content.Context
import android.view.LayoutInflater
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
            }
            binding.trashcan.setOnClickListener {
                //TODO 삭제 구현
            }
        }
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