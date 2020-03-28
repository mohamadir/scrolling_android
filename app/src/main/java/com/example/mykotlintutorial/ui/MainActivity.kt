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
    var right: Int = 1089

    private var isScrolling = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mykotlintutorial.R.layout.activity_main)
       // var text_width: Int =  ( resources.getDimension(com.example.mykotlintutorial.R.dimen.text_width)).toInt()


        horizontalScroll.setOnScrollChangeListener()  { view: View, i: Int, i1: Int, i2: Int, i3: Int ->

            if (isScrolling)  {
                return@setOnScrollChangeListener
            }

           if (direction == 0 ) { // left

               if (i > i2) { // right

                   horizontalScroll.isEnableScrolling = false
                   isScrolling = true
                   Handler().postDelayed({
                       isScrolling = false
                       direction = 1
                       horizontalScroll.isEnableScrolling = true
                   }, 450)
                   horizontalScroll.post {
                       scrollList(i , middle)
//                       horizontalScroll.smoothScrollTo(middle, 0)
                   }

               }


           } else if (direction == 1) {// center
               if (i > i2) { // right
                   horizontalScroll.isEnableScrolling = false
                   isScrolling = true
                   Handler().postDelayed({
                       isScrolling = false
                       direction = 2
                       horizontalScroll.isEnableScrolling = true
                   },450)
                   horizontalScroll.post {
                       scrollList(i , right)
                   }
               } else {  // left
                   isScrolling = true
                   horizontalScroll.isEnableScrolling = false
                     Handler().postDelayed({
                         isScrolling = false
                         direction = 0
                         horizontalScroll.isEnableScrolling = true
                     },450)
                     horizontalScroll.post {
                         scrollList(i , left)

                     }
               }

           } else { // right
               if (i < i2) { // left

                   horizontalScroll.isEnableScrolling = false
                   isScrolling = true
                   Handler().postDelayed({
                       direction = 1
                       isScrolling = false
                       horizontalScroll.isEnableScrolling = true
                   },450)
                   horizontalScroll.post {
                       scrollList(i2 , middle)
                   }
               }

           }







        }

    }


    fun scrollList (from: Int, to : Int) {
        val realSmoothScrollAnimation =
            ValueAnimator.ofInt(from, to)
        realSmoothScrollAnimation.duration = 400
        realSmoothScrollAnimation.addUpdateListener { animation ->
            val scrollTo = animation.animatedValue as Int
            horizontalScroll.smoothScrollTo(scrollTo, 0)
        }

        realSmoothScrollAnimation.start()
    }



}
