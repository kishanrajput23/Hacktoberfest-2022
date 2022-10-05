package dev.septiyadi.fundamentalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToast = findViewById(R.id.btToast) as Button

        btnToast.setOnClickListener {
            Toast.makeText(this@MainActivity, "This is toast with Kotlin", Toast.LENGTH_LONG).show()
        }
    }
}