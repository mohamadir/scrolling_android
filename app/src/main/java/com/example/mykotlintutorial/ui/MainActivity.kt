package com.example.mykotlintutorial.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.GestureDetectorCompat
import com.example.mykotlintutorial.R
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    private var direction = 0

    var middle : Int = 502
    var left: Int = 0
    var right: Int = 1200

    private var isScrolling = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mykotlintutorial.R.layout.activity_main)
        var text_width: Int =  ( resources.getDimension(com.example.mykotlintutorial.R.dimen.text_width)).toInt()
        middle = text_width * 2
        right = text_width * 4
        left = 0

      horizontalScroll.setOffsets(left,middle,right)

    }



}
