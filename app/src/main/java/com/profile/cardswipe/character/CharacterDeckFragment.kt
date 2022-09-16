package com.profile.cardswipe.character

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.profile.cardswipe.R
import com.profile.cardswipe.base.extensions.observe
import com.profile.cardswipe.base.viewBinding
import com.profile.cardswipe.databinding.FragmentDeckBinding
import com.profile.cardswipe.dialog.DialogCreator
import com.profile.cardswipe.dialog.DialogRouter
import com.profile.cardswipe.dialog.ProgressDialogFragment
import com.profile.cardswipe.dialog.dialogRouter
import com.profile.lib.CardSwipeListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDeckFragment : Fragment(R.layout.fragment_deck), CardSwipeListener, KoinComponent {
    private val binding by viewBinding(FragmentDeckBinding::bind, true)
    private val viewModel by inject<CharacterDeckViewModel>()
    private val progress = ProgressDialogFragment.newInstance()
    private lateinit var latestToastDialog: Toast
    private lateinit var dialogRouter: DialogRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        dialogRouter = dialogRouter()
        latestToastDialog = Toast.makeText(requireContext(), R.string.left, Toast.LENGTH_SHORT)
        observeViewModel()
        collectViewModel()
    }


    override fun onLeftOutside() {
        latestToastDialog.cancel()
        latestToastDialog.setText(R.string.left)
        latestToastDialog.show()
    }

    override fun onRightOutside() {
        latestToastDialog.cancel()
        latestToastDialog.setText(R.string.right)
        latestToastDialog.show()
    }

    override fun onNoItemLeft() {
        viewModel.getNextCharacters()
    }

    private fun observeViewModel() {
        observe(viewModel.isLoading) {
            changeProgressState(it)
        }

        observe(viewModel.hasError) {
            showErrorMessage(it)
        }
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            viewModel.characterCards.collectLatest {
                binding.cardDeck.addCards(it, this@CharacterDeckFragment)
            }
        }
    }

    private fun changeProgressState(show: Boolean) {
        progress.changeLoadingState(show, dialogRouter)
    }

    private fun showErrorMessage(message: String) {
        DialogCreator()
            .setTitle(R.string.common_error)
            .setMessage(message)
            .setButton(R.string.common_ok)
            .buildAndShow()
    }

    companion object {
        const val TAG = "CharacterDeck"
    }
}