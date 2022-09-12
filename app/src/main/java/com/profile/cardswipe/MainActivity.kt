package com.profile.cardswipe

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.profile.lib.CardDeckLayout

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel(RickMortyApi())
    lateinit var recyclerView: CardDeckLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        val buttonPrev = findViewById<Button>(R.id.button_prev)
        val buttonNext = findViewById<Button>(R.id.button_next)

        viewModel.getCharactersModel(true)
        viewModel.liveData.observe(this) {
            recyclerView.addCards(it)
        }

        buttonNext.setOnClickListener {
            viewModel.getCharactersModel(true)
        }

        buttonPrev.setOnClickListener {
            viewModel.getCharactersModel(false)
        }
    }
}