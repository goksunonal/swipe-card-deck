package com.profile.cardswipe

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.profile.lib.Card
import com.profile.lib.CardDeckLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<CardDeckLayout>(R.id.recyclerView)
        val list = mutableListOf<Card>()
        list.add(Card(com.profile.lib.R.drawable.person_example, "a", "g", Location(LOCATION_SERVICE)))
        list.add(Card(com.profile.lib.R.drawable.person_example_2, "b", "h", Location(LOCATION_SERVICE)))
        list.add(Card(com.profile.lib.R.drawable.person_example, "c", "i", Location(LOCATION_SERVICE)))
        list.add(Card(com.profile.lib.R.drawable.person_example_2, "d", "g", Location(LOCATION_SERVICE)))
        list.add(Card(com.profile.lib.R.drawable.person_example, "e", "a", Location(LOCATION_SERVICE)))
        list.add(Card(com.profile.lib.R.drawable.person_example_2, "f", "a", Location(LOCATION_SERVICE)))
        recyclerView.addCards(list)
    }
}