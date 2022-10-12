package kr.hs.diowner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kr.hs.diowner.data.AdvertisingResponseData
import kr.hs.diowner.data.OwnerResponseData
import kr.hs.diowner.data.PostAdvertisingData
import kr.hs.diowner.data.ProductData
import kr.hs.diowner.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private var calendar = Calendar.getInstance()
    private var result: Long = 0
    private var check_event: String = "null"
    private var check_category: String = "null"

    private val StNameEt: EditText by lazy {
        findViewById(R.id.StName_ET)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingListener()
        settingEventSpinner()
        settingCategorySpinner()
        setTimeArray()
        showdate()
    }

    //TODO 주요상품 등록 구현
    //TODO 사진등록 구현
    private fun setTimeArray() {
        binding.etTime.addTextChangedListener(object : TextWatcher {
            var length = 0
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                length = binding.etTime.text.length
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val check = length > binding.etTime.text.length
                binding.etTime.setSelection(binding.etTime.text.length)
                var str: String = binding.etTime.text.toString()

                if (str.length == 11 && check) {
                    binding.etTime.setText(str.substring(0, 10))
                } else if (str.length == 8 && check) {
                    binding.etTime.setText(str.substring(0, 5))
                } else if (str.length == 3 && check) {
                    binding.etTime.setText(str.substring(0, 2))
                } else if (str.length == 3 && !check) {
                    binding.etTime.setText(str.substring(0,2) + ":"+str.substring(2,3))
                } else if(str.length == 6 && !check) {
                    binding.etTime.setText(str.substring(0,5) + " ~ " + str.substring(5,6))
                } else if(str.length == 11 && !check) {
                    binding.etTime.setText(str.substring(0,10)+ ":" + str.substring(10,11))
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun showdate() {
        val calenderConstraintBuilder = CalendarConstraints.Builder()
        calenderConstraintBuilder.setValidator(DateValidatorPointForward.now())
        val sharedPreference = getSharedPreferences("setDate", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        binding.firstDayLayout.setOnClickListener {
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

        binding.endDayLayout.setOnClickListener {
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

    private fun settingEventSpinner() {
        val item = resources.getStringArray(R.array.eventList)

        val myAdapter = ArrayAdapter(this, R.layout.spinner_textview, item)
        binding.eventSpinner.adapter = myAdapter

        binding.eventSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        check_event = "null"
                    }
                    1 -> {
                        check_event = "NEW"
                    }
                    2 -> {
                        check_event = "ORIGIN"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun settingCategorySpinner() {
        val item = resources.getStringArray(R.array.categoryList)

        val myAdapter = ArrayAdapter(this, R.layout.spinner_textview, item)
        binding.categorySpinner.adapter = myAdapter

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            check_category = "null"
                        }
                        1 -> {
                            check_category = "FOOD"
                        }
                        2 -> {
                            check_category = "BEAUTY"
                        }
                        3 -> {
                            check_category = "PLANT"
                        }
                        4 -> {
                            check_category = "EXERCISE"
                        }
                        5 -> {
                            check_category = "INTERIOR"
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    private fun settingListener() {
        binding.registerBtn.setOnClickListener(this)
        binding.etTime.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.registerBtn -> {
                CheckRegister()
            }
            binding.etTime -> {
                binding.etTime.setSelection(binding.etTime.text.length)
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
        } else if (check_event == "null") {
            Toast.makeText(this, "이벤트를 선택해 주세요.", Toast.LENGTH_SHORT).show()
        } else if (check_category == "null") {
            Toast.makeText(this, "카테고리를 선택해 주세요.", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("test_check", "pass")


            val postAdvertisingData = PostAdvertisingData(
                binding.businessNumET.text.toString(),
                check_category,
                binding.mailET.text.toString(),
                binding.tvEndDay.text.toString(),
                binding.storeEt.text.toString(),
                binding.representET.text.toString(),
                arrayListOf(ProductData("test", 1000), ProductData("test", 1000)),
                binding.tvStartDay.text.toString(),
                "상점 이름",
                binding.StNameET.text.toString(),
                check_event
            )
            postAdvertising(postAdvertisingData)
        }
    }

    private fun postAdvertising(postAdvertisingData: PostAdvertisingData) {
        RetrofitBuilder.api.postAdvertising(postAdvertisingData).enqueue(object :
            Callback<AdvertisingResponseData> {
            override fun onResponse(
                call: Call<AdvertisingResponseData>,
                response: Response<AdvertisingResponseData>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    val intent = Intent(applicationContext, MyActivity::class.java)
                    Toast.makeText(applicationContext, "등록 성공!!", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<AdvertisingResponseData>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }
}
