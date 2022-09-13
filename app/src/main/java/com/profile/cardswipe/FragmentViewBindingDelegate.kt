package com.profile.cardswipe

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T,
    val bindLifecycleOwner: Boolean = false
) :
    ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(FragmentLifeCycleObserver())
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also {
            this.binding = it
        }
    }

    inner class FragmentLifeCycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
                lifecycleOwner.lifecycle.addObserver(ViewLifeCycleObserver())
                if (bindLifecycleOwner) {
                    (binding as? ViewDataBinding)?.lifecycleOwner = fragment.viewLifecycleOwner
                }
            }
        }
    }

    inner class ViewLifeCycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            binding = null
        }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T,
    bindLifecycleOwner: Boolean
) =
    FragmentViewBindingDelegate(this, viewBindingFactory, bindLifecycleOwner)