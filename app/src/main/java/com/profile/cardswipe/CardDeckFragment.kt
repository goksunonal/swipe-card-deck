package com.profile.cardswipe

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.profile.cardswipe.databinding.FragmentDeckBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class CardDeckFragment : Fragment(R.layout.fragment_deck) {
    private val binding by viewBinding(FragmentDeckBinding::bind, true)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = MovieListAdapter()
        binding.recyclerView.adapter = adapter
        val layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        layoutManager.reverseLayout = true
//        layoutManager.stackFromEnd = true
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(OverlapDecoration())
        lifecycleScope.launch {
            viewModel.getCharacters().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }

        binding.recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val childView: View? = rv.findChildViewUnder(e.x, e.y)
                childView?.let {
                    if (rv.getChildAdapterPosition(it) != 0) return false
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT or ItemTouchHelper.UP
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // of the item at that position.
                val position = viewHolder.absoluteAdapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(position)
            }
        }).attachToRecyclerView(binding.recyclerView)
    }
}

private class OverlapDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition: Int = parent.getChildAdapterPosition(view)
        if (itemPosition == 0) {
            outRect.set(0, 0, 0, 0)
        } else {
            outRect.set(0, 0, 0, overlap)
        }
    }

    companion object {
        private const val overlap = -1200
    }
}

class BackwardsDrawingOrderCallback : RecyclerView.ChildDrawingOrderCallback {
    override fun onGetChildDrawingOrder(childCount: Int, i: Int) = childCount - i - 1
}
