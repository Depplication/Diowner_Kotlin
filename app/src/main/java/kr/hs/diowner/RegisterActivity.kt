package kr.hs.diowner

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ActionMode
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import kr.hs.diowner.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        val items = resources.getStringArray(R.array.itemList)

        val myAapter = object : ArrayAdapter<String>(this, R.layout.item_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)

                if (position == count) {
                    (v.findViewById<View>(R.id.tvItemSpinner) as TextView).text = ""
                    (v.findViewById<View>(R.id.tvItemSpinner) as TextView).hint = getItem(count)
                }

                return v
            }

            override fun getCount(): Int {
                return super.getCount() - 1
            }

        }

        myAapter.addAll(items.toMutableList())

        myAapter.add("---선택해주세요---")

        binding.eventSp.adapter = myAapter

        binding.eventSp.setSelection(myAapter.count)

        binding.eventSp.dropDownVerticalOffset = dipToPixels(45f).toInt()
        binding.eventSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> {
                        Toast.makeText(this@RegisterActivity, "Open event", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(this@RegisterActivity, "sale event", Toast.LENGTH_SHORT).show()
                    }
                    2 -> Toast.makeText(this@RegisterActivity, "store event", Toast.LENGTH_SHORT).show()
                    else -> {
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }
        showdate()
    }

    private fun showdate() {
        binding.firstDayBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                binding.tvStartDay.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }
        binding.endDayBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                binding.tvEndDay.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }
}
