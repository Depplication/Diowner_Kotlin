package kr.hs.diowner.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.diowner.data.PointData
import kr.hs.diowner.databinding.TodayPointListBinding

class PointListAdapter(val context: Context) :
    RecyclerView.Adapter<PointListAdapter.MyViewHolder>() {
    var dataList = mutableListOf<PointData>()

    inner class MyViewHolder(private val binding: TodayPointListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PointData) {
            binding.tvId.text = data.id
            binding.tvPoint.text = data.point.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            TodayPointListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}