package jp.tinyport.hellounity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import jp.tinyport.hellounity.databinding.MainActivityBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, UnityPlayerActivity::class.java))
        }
    }
}
