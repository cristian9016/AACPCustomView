package com.example.personal.aacpcustomview

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class EditTextWithClear : AppCompatEditText {

    lateinit var clearButtonImage : Drawable

    constructor(context: Context) : super(context) {init()}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {init()}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {init()}

    fun init(){
        clearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_gray,null)!!

        setOnTouchListener { p0, p1 ->
            if(compoundDrawablesRelative[2] != null){
                var isClearButtonClicked = false
                var clearButtonStart = 0
                var clearButtonEnd = 0
                if(layoutDirection == View.LAYOUT_DIRECTION_RTL){
                    clearButtonEnd = clearButtonImage.intrinsicWidth + paddingStart
                    if(p1.x < clearButtonEnd) isClearButtonClicked = true
                }else{
                    clearButtonStart = width - paddingEnd - clearButtonImage.intrinsicWidth
                    if(p1.x > clearButtonStart) isClearButtonClicked = true
                }
                if(isClearButtonClicked){
                    if(p1.action == MotionEvent.ACTION_DOWN){
                        clearButtonImage = ResourcesCompat.getDrawable(resources,R.drawable.ic_close_black,null)!!
                        showClearButton()
                    }
                    if(p1.action == MotionEvent.ACTION_UP){
                        clearButtonImage = ResourcesCompat.getDrawable(resources,R.drawable.ic_close_gray,null)!!
                        text!!.clear()
                        hideClearButton()
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }



        addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showClearButton()
            }
        })
    }

    private fun showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,clearButtonImage,null)
    }

    private fun hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
    }
}
