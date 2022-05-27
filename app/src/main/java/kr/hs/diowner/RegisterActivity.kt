package kr.hs.diowner

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kr.hs.diowner.databinding.ActivityRegisterBinding
import java.util.*
import kotlin.properties.Delegates

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var calendar = Calendar.getInstance()
    private var result : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, MyActivity::class.java)
            startActivity(intent)
            finish()
        }
        setSpinner()
        showdate()
    }

    private fun showdate() {
        val calenderConstraintBuilder = CalendarConstraints.Builder()
        calenderConstraintBuilder.setValidator(DateValidatorPointForward.now())
        val sharedPreference = getSharedPreferences("setDate", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        binding.firstDayBtn.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("select_first_day")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calenderConstraintBuilder.build())

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                calendar
                calendar.time = Date(it)
                val calendarMilli = calendar.timeInMillis
                binding.tvStartDay.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
                editor.putLong("first_date", calendarMilli)
                editor.commit()
                Log.d("Die_Millis", "$calendarMilli")
                this.result = sharedPreference.getLong("first_date", 0)
            }
            datePicker.show(supportFragmentManager, datePicker.toString())
        }

        binding.endDayBtn.setOnClickListener {
            val calenderConstraintBuilder2 = CalendarConstraints.Builder()
            calenderConstraintBuilder2.setValidator(DateValidatorPointForward.from(result+1))
            Log.d("test", this.result.toString())
            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("select_end_day")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calenderConstraintBuilder2.build())

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                calendar
                calendar.time = Date(it)
                val calendarMilli = calendar.timeInMillis
                binding.tvEndDay.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
                }
            datePicker.show(supportFragmentManager, datePicker.toString())
        }
    }
    private fun setSpinner(){
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
                    0 -> Log.d("test", position.toString())

                    1 -> Log.d("test", position.toString())

                    2 -> Log.d("test", position.toString())
                    else -> {
                        Log.d("test_error", position.toString())
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
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
