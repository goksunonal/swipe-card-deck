package com.profile.cardswipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.profile.cardswipe.databinding.FragmentDeckBinding
import com.profile.lib.CardSwipeListener

class CardDeckFragment : Fragment(R.layout.fragment_deck) {
    private val binding by viewBinding(FragmentDeckBinding::bind, true)
    private val viewModel by viewModels<MainViewModel>()
    private val dialogFragment = ProgressDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.characterCards.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> it.data?.let { cardList ->
                    dialogFragment.dismiss()
                    binding.cardDeck.addCards(cardList, object : CardSwipeListener {
                        override fun onLeftOutside() {
                            //Toast.makeText(requireContext(), "Left", Toast.LENGTH_SHORT).show()
                        }

                        override fun onRightOutside() {
                            //Toast.makeText(requireContext(), "Right", Toast.LENGTH_SHORT).show()
                        }

                        override fun onNoItemLeft() {
                            viewModel.getCharacters()
                        }
                    })
                }
                is Resource.Loading -> dialogFragment.show(childFragmentManager, "aa")
                else -> {
                    dialogFragment.dismiss()
                }
            }
        }
    }
}