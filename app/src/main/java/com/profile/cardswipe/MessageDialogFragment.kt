package com.profile.cardswipe

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.profile.lib.binding.setVisibility

class MessageDialogFragment : DialogFragment(R.layout.dialog_box) {

    companion object {
        const val DIALOG_FRAGMENT_TAG = "SYSMIC"
        const val RESULT_TAG = "RESULT"
    }

    private lateinit var config: DialogConfig
    private lateinit var router: DialogRouter

    init {
        isCancelable = false
    }

    fun setConfigurations(config: DialogConfig, router: DialogRouter) {
        this.config = config
        this.router = router
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (::config.isInitialized) {

            view.findViewById<ImageView>(R.id.logo).apply {
                setVisibility(config.icon != null)
                setImageDrawable(config.icon)
            }

            view.findViewById<TextView>(R.id.title).apply {
                setVisibility(config.title.isNotBlank())
                text = config.title
            }

            view.findViewById<TextView>(R.id.body).apply {
                setVisibility(config.message.isNotBlank())
                text = config.message
                requestFocus()
            }

            view.findViewById<Button>(R.id.button).apply {
                val buttonConfig = config.button
                this.text = buttonConfig.text
                this.setOnClickListener {
                    buttonConfig.listener.invoke()
                }
            }

            isCancelable = config.isCancellable
        } else {
            dismissAllowingStateLoss()
        }
    }

    fun show() {
        router.showScreen(this, DIALOG_FRAGMENT_TAG)
    }

    class Builder(private val provider: Context, private val router: DialogRouter) {
        private lateinit var instance: MessageDialogFragment

        private var title: String = ""
        private var titleIcon: Drawable? = null
        private var body: String = ""
        private var icon: Drawable? = ContextCompat.getDrawable(provider, R.drawable.ic_warning)
        private var cancellable: Boolean = false
        private var button: ButtonConfig = ButtonConfig(provider.getString(R.string.common_ok)) {}
        private var dismissOnAction: Boolean = true

        fun setButton(
            @StringRes resource: Int,
            listener: (() -> Unit)? = null
        ): Builder {
            button = ButtonConfig(provider.getString(resource)) {
                if (dismissOnAction) {
                    instance.dismissAllowingStateLoss()
                }
                listener?.invoke()
            }
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setTitle(@StringRes resource: Int): Builder {
            this.title = provider.getString(resource)
            return this
        }

        fun setIcon(icon: Drawable): Builder {
            this.icon = icon
            return this
        }

        fun setIcon(@DrawableRes resource: Int): Builder {
            this.icon =
                (ContextCompat.getDrawable(provider, resource) ?: ContextCompat.getDrawable(
                    provider,
                    R.drawable.ic_warning
                ))!!
            return this
        }

        fun setMessage(@StringRes resource: Int): Builder {
            this.body = provider.getString(resource)
            return this
        }

        fun setMessage(body: String): Builder {
            this.body = body
            return this
        }

        fun setCancellable(cancellable: Boolean): Builder {
            this.cancellable = cancellable
            return this
        }

        fun setDismissOnAction(dismissOnAction: Boolean): Builder {
            this.dismissOnAction = dismissOnAction
            return this
        }

        fun build(): MessageDialogFragment {
            instance = MessageDialogFragment()
            instance.setConfigurations(
                DialogConfig(
                    title,
                    titleIcon,
                    icon,
                    body,
                    button,
                    cancellable,
                    dismissOnAction
                ),
                router
            )
            return instance
        }

        fun buildAndShow() {
            build().show()
        }
    }
}

fun FragmentActivity.DialogCreator(): MessageDialogFragment.Builder {
    return MessageDialogFragment.Builder(this, DialogRouter(supportFragmentManager))
}

fun Fragment.DialogCreator(): MessageDialogFragment.Builder {
    return MessageDialogFragment.Builder(requireContext(), DialogRouter(childFragmentManager))
}