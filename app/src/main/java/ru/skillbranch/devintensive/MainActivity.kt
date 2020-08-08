package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Sheldon

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var tvQuestion: TextView
    lateinit var ivSheldon: ImageView
    lateinit var ivSend: ImageView
    lateinit var etAnswer: EditText
    var sheldon = Sheldon()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity:", "onCreate")
        tvQuestion = tv_text
        ivSheldon = iv_sheldon
        ivSend = iv_send
        etAnswer = et_answer

        tvQuestion.text = sheldon.askQuestion()
        ivSend.setOnClickListener(this)


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

    override fun onClick(p0: View?) {
        val answer = etAnswer.text.toString()
        etAnswer.text.clear()
        val (result, color) = sheldon.checkAnswer(answer)
        val (r,g,b) = color
        tvQuestion.text = result
        ivSheldon.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
    }
}