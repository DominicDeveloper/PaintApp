package com.asadbek.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.asadbek.paintapp.MainActivity.Companion.paint_brush
import com.asadbek.paintapp.MainActivity.Companion.path

// view_display.xml da chaqirildi. sababi ushbu klass orqali chizish mumkin
// ushbu xml ni esa inlcude yordamida acitivity_mainga olindi

class Display: View {
    var params: ViewGroup.LayoutParams? = null
    // MainActivity classida ishlatilishi kerak bo`lgani uchun object ga olindi
    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLUE

    }

    // View classi dokumentatsiyasiga ko`ra constructorlarni yozib chiqildi
    constructor(context: Context):this(context,null){
        init()
    }
    constructor(context: Context,attrs:AttributeSet?):this(context,attrs,0){
        init()
    }
    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        init()
    }
    private fun init(){
        paint_brush.isAntiAlias = true
        paint_brush.color = currentBrush // rang
        paint_brush.style = Paint.Style.STROKE // stil
        paint_brush.strokeJoin = Paint.Join.ROUND // chizish yumaloq xolatda
        paint_brush.strokeWidth = 21f // qalinligi

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    // drag drop dek, sensordagi xarakatga qarab chizishni boshlaydi
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false;
    }

    // chizish uchun
    override fun onDraw(canvas: Canvas) {
        for (i in pathList.indices){
            paint_brush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paint_brush)
            invalidate()
        }
    }
}