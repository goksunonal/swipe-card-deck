package com.profile.lib

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import com.profile.lib.databinding.CardTemplateBinding
import com.profile.lib.util.dp
import com.profile.lib.view.SwipeMaterialCardView


class CardDeckLayout : FrameLayout {
    private val addedViews = mutableListOf<SwipeMaterialCardView>()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun addCards(list: List<Card>) {
        list.forEach { card ->
            this.post {
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding: CardTemplateBinding = CardTemplateBinding.inflate(inflater)
                val params = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.CENTER
                params.marginStart = 40.dp
                params.marginEnd = 40.dp
                binding.card = card
                (binding.root as SwipeMaterialCardView).setCardSwipeListener(object :
                    CardSwipeListener {
                    override fun onLeftOutside() {
                        removeView()
                    }

                    override fun onRightOutside() {
                        removeView(false)
                    }

                })
                addedViews.add(binding.root as SwipeMaterialCardView)
                addView(binding.root, params)
            }
        }
    }

    fun removeView(isLeft: Boolean = true) {
        val lastView = addedViews.last()
        val hide = TranslateAnimation(
            lastView.translationX,
            if (isLeft) -width.toFloat() else width.toFloat(),
            0f,
            0f
        )
        hide.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                lastView.isAnimationOn = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                removeView(addedViews.last())
                addedViews.removeLast()
                lastView.isAnimationOn = true
                invalidate()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        hide.duration = 500
        lastView.startAnimation(hide)
    }
}