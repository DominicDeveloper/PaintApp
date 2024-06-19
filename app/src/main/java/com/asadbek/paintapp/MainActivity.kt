package com.asadbek.paintapp

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asadbek.paintapp.Display.Companion.colorList
import com.asadbek.paintapp.Display.Companion.currentBrush
import com.asadbek.paintapp.Display.Companion.pathList
import com.asadbek.paintapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        var path = Path()
        var paint_brush = Paint()
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBlack.setOnClickListener {
            Toast.makeText(this,"CLicked",Toast.LENGTH_SHORT).show()
            paint_brush.color  = Color.BLACK
            currentColor(paint_brush.color)
        }
        binding.btnBlue.setOnClickListener {
            Toast.makeText(this,"CLicked",Toast.LENGTH_SHORT).show()
            paint_brush.color  = Color.BLUE
            currentColor(paint_brush.color)
        }
        binding.btnPurple.setOnClickListener {
            Toast.makeText(this,"CLicked",Toast.LENGTH_SHORT).show()
            paint_brush.color  = Color.MAGENTA
            currentColor(paint_brush.color)
        }
        binding.btnYellow.setOnClickListener {
            Toast.makeText(this,"CLicked",Toast.LENGTH_SHORT).show()
            paint_brush.color  = Color.YELLOW
            currentColor(paint_brush.color)
        }

        binding.btnCleaner.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }

    }
    private fun currentColor(color:Int){
        currentBrush = color
        path = Path()
    }

}