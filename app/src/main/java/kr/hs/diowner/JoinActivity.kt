package kr.hs.diowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {
    val RuleCheck : CheckBox by lazy {
        findViewById(R.id.check)
    }
    val JoinBtn : Button by lazy {
        findViewById(R.id.Join_Btn)
    }
    val StName_ET : EditText by lazy{
        findViewById(R.id.StName_ET)
    }
    val Name_ET : EditText by lazy{
        findViewById(R.id.Name_ET)
    }
    val IdEt : EditText by lazy{
        findViewById(R.id.Id_ET)
    }
    val PwEt : EditText by lazy {
        findViewById(R.id.Pw_ET)
    }
    val RPwEt : EditText by lazy {
        findViewById(R.id.RPw_ET)
    }
    val PwToggle : ImageView by lazy {
        findViewById(R.id.Pw_Toggle)
    }
    val PhoneNum : EditText by lazy {
        findViewById(R.id.PhoneNum_ET)
    }
    val AddressEt : EditText by lazy {
        findViewById(R.id.Address_ET)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        PwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        RPwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        settingListener()

        val sv : ScrollView? = findViewById(R.id.scroll)
        val tv_rule = findViewById<TextView>(R.id.tv_rule)
        val tv_check = findViewById<TextView>(R.id.tv_check)
        tv_rule.setMovementMethod(ScrollingMovementMethod()) // 스크롤 가능하게 해주는 부분
        tv_rule?.setOnTouchListener { v, event ->
            sv?.requestDisallowInterceptTouchEvent(true)//부모 scroll 권한 빼는 부분)
            return@setOnTouchListener false
        }
        tv_rule?.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrolly ->
            if (!tv_rule.canScrollVertically(1)) {
                RuleCheck.isEnabled = true
                if (RuleCheck.isEnabled){
                    tv_check.setOnClickListener {
                        if(RuleCheck.isChecked){
                            RuleCheck.isChecked = false
                        }else{
                            RuleCheck.isChecked = true
                        }
                    }
                }
                //Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun settingListener(){
        PwToggle.setOnClickListener(this)
        JoinBtn.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when(view){
            PwToggle -> {
                if (PwToggle.tag.equals("0")) {
                    PwToggle.tag = "1"
                    PwToggle.setImageResource(R.drawable.view)

                    PwEt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    RPwEt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    PwToggle.tag = "0"
                    PwToggle.setImageResource(R.drawable.hidden)

                    PwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    RPwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                }
                PwEt.setSelection(PwEt.text.length)
            }
            JoinBtn -> {
                CheckJoin()
            }

        }
    }
    private fun CheckJoin() {
        if (!Pattern.matches("^(?=.*[A-Za-z]).{5,11}.\$", IdEt.text.toString())) {
            IdEt.requestFocus()
            Toast.makeText(this, "아이디는 6~12자 사이여야합니다.", Toast.LENGTH_SHORT).show()
        }else if(!RuleCheck.isChecked){
            RuleCheck.requestFocus()
            Toast.makeText(this, "이용약관에 동의 하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
        }        else if(StName_ET.text.isNullOrBlank()){
            StName_ET.requestFocus()
            Toast.makeText(this, "상점명란이 비었습니다", Toast.LENGTH_SHORT).show()
        }else if (Name_ET.text.isNullOrBlank()){
            Name_ET.requestFocus()
            Toast.makeText(this, "대표자명란이 비었습니다", Toast.LENGTH_SHORT).show()
        }else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{7,14}.\$", PwEt.text.toString())) {
            PwEt.requestFocus()
            Toast.makeText(this, "비밀번호는 8~15자 문자와 숫자, 특수문자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT).show()
        }else if(PwEt.text.toString() != RPwEt.text.toString()){
            RPwEt.requestFocus()
            Toast.makeText(this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show()
        } else if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$", PhoneNum.text.toString())) {
            PhoneNum.requestFocus()
            Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
        } else if(AddressEt.text.isNullOrBlank()){
            AddressEt.requestFocus()
            Toast.makeText(this, "주소란이 비었습니다.", Toast.LENGTH_SHORT).show()
        } else if(false){
            //TODO 인증번호 확인
        } else {
            //서버에 포스트...
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(this,"회원가입 성공!", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
    }
}