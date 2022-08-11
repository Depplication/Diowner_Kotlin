package kr.hs.diowner

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.diowner.data.BankData
import kr.hs.diowner.databinding.BankListBinding
import java.util.ArrayList

class BankAdapter(val context: Context, val bankList: ArrayList<BankData>, val key: String):
    RecyclerView.Adapter<BankAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: BankListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: BankData, context: Context) {
                val resourceId = context.resources.getIdentifier(data.image, "drawable", context.packageName)
                binding.bankImg.setImageResource(resourceId)
                binding.title.text = data.name
                binding.bankLayout.setOnClickListener {
                    if(key.equals("modify")){
                        Intent(context, ModifyActivity::class.java).apply {
                            putExtra("data", data)
                            Log.d("test", data.toString())
                        }.run { context.startActivity(this) }
                    }else if(key.equals("join")){
                        Intent(context, JoinActivity::class.java).apply {
                            //TODO 나중에 join에 값보내주는문 쓰기
                            val activity2: JoinActivity = context as JoinActivity
                            putExtra("data", data)
                            activity2.finish()
                        }
                    }
                    val activity: ChoiceBank = context as ChoiceBank
                    activity.finish()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = BankListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bankList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bankList[position], context)
    }
}