package com.profile.cardswipe

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

class ProgressDialogFragment private constructor() : DialogFragment(R.layout.progress_layout) {
    companion object {
        private var shown = false

        fun newInstance(): ProgressDialogFragment {
            shown = false
            return ProgressDialogFragment()
        }
    }

    init {
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun changeLoadingState(shouldShow: Boolean, dialogRouter: DialogRouter) {
        if (shouldShow && !shown) {
            show(dialogRouter, ProgressDialogFragment::class.java.simpleName)
        } else if (!shouldShow && shown) {
            dismissAllowingStateLoss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        shown = false
    }

    private fun show(router: DialogRouter, tag: String) {
        shown = true
        router.showScreen(this, tag)
    }
}