package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Sheldon

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var tvQuestion: TextView
    lateinit var ivSheldon: ImageView
    lateinit var ivSend: ImageView
    lateinit var etAnswer: EditText
    lateinit var sheldonObj: Sheldon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity:", "onCreate")
        tvQuestion = tv_text
        ivSheldon = iv_sheldon
        ivSend = iv_send
        etAnswer = et_answer
        etAnswer.imeOptions = EditorInfo.IME_ACTION_DONE

        val savedStatus = savedInstanceState?.getString(getString(R.string.key_status)) ?: Sheldon.STATUS.NORMAL.name
        val savedQuestion = savedInstanceState?.getString(getString(R.string.key_question)) ?: Sheldon.QUESTION.NAME.name

        sheldonObj = Sheldon(status = Sheldon.STATUS.valueOf(savedStatus), question = Sheldon.QUESTION.valueOf(savedQuestion))
        val (r,g,b) = Sheldon.STATUS.valueOf(savedStatus).color
        ivSheldon.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)



        tvQuestion.text = sheldonObj.askQuestion()
        ivSend.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        hideKeyboard(p0)
        val answer = etAnswer.text.toString()
        etAnswer.text.clear()
        val (result, color) = sheldonObj.checkAnswer(answer)
        val (r,g,b) = color
        tvQuestion.text = result
        ivSheldon.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(getString(R.string.key_status), sheldonObj.status.name)
        outState.putString(getString(R.string.key_question), sheldonObj.question.name)
        Log.d("M_MainActivity:", "${getString(R.string.key_question)} : ${sheldonObj.question.name}")
        Log.d("M_MainActivity:", "${getString(R.string.key_status)} : ${sheldonObj.status.name}")

    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity:", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity:", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity:", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity:", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity:", "onDestroy")
    }


}