package com.profile.cardswipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.profile.cardswipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (supportFragmentManager.isDestroyed.not()) {
            supportFragmentManager.commit {
                this.replace(R.id.container, CardDeckFragment(), "tag")
            }
        }
    }
}