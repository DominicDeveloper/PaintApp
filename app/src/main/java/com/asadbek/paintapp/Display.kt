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

class Display: View {
    var params: ViewGroup.LayoutParams? = null
    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLUE

    }
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
        paint_brush.color = currentBrush
        paint_brush.style = Paint.Style.STROKE
        paint_brush.strokeJoin = Paint.Join.ROUND
        paint_brush.strokeWidth = 21f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

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

    override fun onDraw(canvas: Canvas) {
        for (i in pathList.indices){
            paint_brush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paint_brush)
            invalidate()
        }
    }
}