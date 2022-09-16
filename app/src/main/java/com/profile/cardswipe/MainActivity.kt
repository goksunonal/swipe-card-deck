package com.profile.cardswipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.profile.cardswipe.base.viewBinding
import com.profile.cardswipe.character.CharacterDeckFragment
import com.profile.cardswipe.character.characterDeckModule
import com.profile.cardswipe.databinding.ActivityMainBinding
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (supportFragmentManager.isDestroyed.not()) {
            supportFragmentManager.commit {
                loadKoinModules(characterDeckModule)
                this.replace(R.id.container, CharacterDeckFragment(), CharacterDeckFragment.TAG)
            }
        }
    }
}