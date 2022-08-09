package kr.hs.diowner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kr.hs.diowner.databinding.ActivityRegisterBinding
import java.util.*
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private var calendar = Calendar.getInstance()
    private var result: Long = 0

    private val StNameEt: EditText by lazy {
        findViewById(R.id.StName_ET)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingListener()
        showdate()
    }

    //TODO 주요상품 등록 구현
    //TODO 사진등록 구현
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
                binding.tvStartDay.text =
                    "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${
                        calendar.get(Calendar.DAY_OF_MONTH)
                    }"
                editor.putLong("first_date", calendarMilli)
                editor.commit()
                this.result = sharedPreference.getLong("first_date", 0)
            }
            datePicker.show(supportFragmentManager, datePicker.toString())
        }

        binding.endDayBtn.setOnClickListener {
            val calenderConstraintBuilder2 = CalendarConstraints.Builder()
            calenderConstraintBuilder2.setValidator(DateValidatorPointForward.from(result + 1))
            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("select_end_day")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calenderConstraintBuilder2.build())

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                calendar
                calendar.time = Date(it)
                val calendarMilli = calendar.timeInMillis
                binding.tvEndDay.text =
                    "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${
                        calendar.get(Calendar.DAY_OF_MONTH)
                    }"
            }
            datePicker.show(supportFragmentManager, datePicker.toString())
        }
    }

    private fun settingListener() {
        binding.registerBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.registerBtn -> {
                CheckRegister()
            }
        }
    }

    private fun CheckRegister() {
        if (StNameEt.text.isNullOrBlank()) {
            Log.d("test_check", "error1")
            StNameEt.requestFocus()
            Toast.makeText(this, "광고 제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (!Pattern.matches("^(?:\\d{10})\$", binding.businessNumET.text.toString())) {
            binding.businessNumET.requestFocus()
            Toast.makeText(this, "사업자 등록번호를 바르게 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.representET.text.isNullOrBlank()) {
            binding.representET.requestFocus()
            Toast.makeText(this, "대표자명을 입력하여 주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.mailET.text.isNullOrBlank()) {
            binding.mailET.requestFocus()
            Toast.makeText(this, "e-mail을 입력하여 주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.tvStartDay.text.isNullOrBlank() || binding.tvEndDay.text.isNullOrBlank()) {
            binding.tvStartDay.requestFocus()
            Toast.makeText(this, "광고 표시기간을 설정해 주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.storeEt.text.isNullOrBlank()) {
            binding.storeEt.requestFocus()
            Toast.makeText(this, "상점 설명을 적어주세요.", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("test_check", "pass")
            val intent = Intent(this, MyActivity::class.java)
            Toast.makeText(this, "등록 성공!!", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
    }
}
