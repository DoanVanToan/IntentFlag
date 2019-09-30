package com.example.customview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class A : AppCompatActivity() {
    val nextActivity: Int by lazy {
        intent.getIntExtra("data", 1)
    }

    companion object {
        fun getIntent(context: Context, nextActivity: Int, action: String? = null): Intent {
            return Intent(context, A::class.java).apply {
                putExtra("data", nextActivity)
                action?.let {
                    setAction(action)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.action != null && intent.action == "CLOSE") {
            finish()
        }
        setContentView(R.layout.activity_main)
        sample.setOnClickListener {
            when (nextActivity) {
                1 -> startActivity(Intent(this@A, B::class.java))
                2 -> startActivity(Intent(this@A, C::class.java))
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d("new", "onNewIntent $intent")
        super.onNewIntent(intent)
    }
}
