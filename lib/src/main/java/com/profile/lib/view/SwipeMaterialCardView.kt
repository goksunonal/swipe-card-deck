package com.profile.lib.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.material.card.MaterialCardView
import com.profile.lib.CardSwipeListener

class SwipeMaterialCardView : MaterialCardView {
    private var lastX = 0
    private var lastY = 0
    private var defaultLeft: Float = 0f
    private var defaultRight: Float = 0f
    private var defaultTop: Float = 0f
    private var defaultBottom: Float = 0f
    private var cardSwipeListener: CardSwipeListener? = null
    var isAnimationOn = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setCardSwipeListener(listener: CardSwipeListener) {
        this.cardSwipeListener = listener
    }
}