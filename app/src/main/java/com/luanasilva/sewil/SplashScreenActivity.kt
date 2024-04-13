package com.luanasilva.sewil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        handlerCronometroTrocarTela()
    }

    private fun handlerCronometroTrocarTela() {
        Handler(Looper.getMainLooper()).postDelayed({
            fun trocarTela() {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            trocarTela()
        },3000)
    }
}