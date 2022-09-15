package com.profile.cardswipe

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class DialogRouter(private val manager: FragmentManager) {
    fun findScreen(tag: String): Fragment? {
        return manager.findFragmentByTag(tag)
    }

    fun showScreen(screen: DialogFragment, tag: String) {
        fun DialogFragment.show() {
            if (!manager.isDestroyed) {
                showWithStateLoss(manager, tag)
            }
        }
        screen.show()
    }
}

fun DialogFragment.showWithStateLoss(manager: FragmentManager, tag: String) {
    val transaction = manager.beginTransaction()
    transaction.add(this, tag)
    transaction.commitAllowingStateLoss()
}

fun Fragment.dialogRouter(): DialogRouter {
    return DialogRouter(childFragmentManager)
}

fun FragmentActivity.dialogRouter(): DialogRouter {
    return DialogRouter(supportFragmentManager)
}