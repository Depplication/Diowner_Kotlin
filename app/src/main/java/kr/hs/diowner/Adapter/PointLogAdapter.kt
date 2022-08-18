package kr.hs.diowner.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.diowner.data.PointLogData
import kr.hs.diowner.databinding.PointLogListBinding

class PointLogAdapter(val context: Context) :
    RecyclerView.Adapter<PointLogAdapter.MyViewHolder>() {
    var dataList = mutableListOf<PointLogData>()

    inner class MyViewHolder(private val binding: PointLogListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PointLogData) {
            val date: String = data.date.toString()
            val time: String = data.time
            if (date.length == 8) {
                val msg = date.substring(0, 4) + "." + date.substring(4, 6) + "." + date.substring(
                    6,
                    8
                ) + "."
                binding.tvDate.text = msg
            }
            if (time.length == 4) {
                val msg = time.substring(0, 2) + ":" + time.substring(2, 4)
                binding.tvTime.text = msg
            }
            binding.tvId.text = data.id
            binding.tvPoint.text = "-" + data.point.toString() + "p"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            PointLogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}