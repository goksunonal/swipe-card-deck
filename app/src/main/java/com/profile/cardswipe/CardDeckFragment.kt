package com.profile.cardswipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.profile.cardswipe.databinding.FragmentDeckBinding
import com.profile.lib.CardSwipeListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CardDeckFragment : Fragment(R.layout.fragment_deck), CardSwipeListener {
    private val binding by viewBinding(FragmentDeckBinding::bind, true)
    private val viewModel by viewModels<MainViewModel>()
    private val progress = ProgressDialogFragment.newInstance()
    private lateinit var dialogRouter: DialogRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        dialogRouter = dialogRouter()

        observeViewModel()
    }


    override fun onLeftOutside() {
        //Toast.makeText(requireContext(), "Left", Toast.LENGTH_SHORT).show()
    }

    override fun onRightOutside() {
        //Toast.makeText(requireContext(), "Right", Toast.LENGTH_SHORT).show()
    }

    override fun onNoItemLeft() {
        viewModel.getNextCharacters()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.characterCards.collectLatest {
                when (it) {
                    is Resource.Success -> it.data?.let { cardList ->
                        progress.changeLoadingState(false, dialogRouter)
                        binding.cardDeck.addCards(cardList, this@CardDeckFragment)
                    }
                    is Resource.Loading -> progress.changeLoadingState(true, dialogRouter)
                    else -> {
                        progress.changeLoadingState(false, dialogRouter)
                        DialogCreator()
                            .setTitle("Hata")
                            .setMessage(it.message!!)
                            .setButton(R.string.common_ok)
                            .buildAndShow()
                    }
                }
            }
        }
    }
}