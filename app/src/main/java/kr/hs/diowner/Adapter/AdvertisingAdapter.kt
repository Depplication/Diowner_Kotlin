package kr.hs.diowner.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.diowner.RegisterActivity
import kr.hs.diowner.data.AdvertisingData
import kr.hs.diowner.databinding.MyAdListBinding

class AdvertisingAdapter(val action: AdvertisingAction) :
    RecyclerView.Adapter<AdvertisingAdapter.MyViewHolder>() {
    var dataList = mutableListOf<AdvertisingData>()

    inner class MyViewHolder(private val binding: MyAdListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdvertisingData) {
            binding.title.text = data.title.toString()
            binding.modify.setOnClickListener {
                action.onClickModify(data)
            }
            binding.trashcan.setOnClickListener {
                action.onClickTrashCan()
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

    interface AdvertisingAction {
        fun onClickModify(data: AdvertisingData)
        fun onClickTrashCan()
    }
}