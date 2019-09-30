package com.example.biometric

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var executor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this


        val biometricPrompt =
            BiometricPrompt(activity, executor, object : AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    TODO("Called when a biometric is recognized.")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    TODO("Called when a biometric is valid but not recognized.")
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    when (errorCode) {
                        BiometricPrompt.ERROR_NEGATIVE_BUTTON -> {
                            // User clicked negative button
                        }
                        else -> TODO("Called when unrecoverable error has been encountered an the operation is completed")
                    }
                }
            })


        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("title")
            .setSubtitle("Sub Title")
            .setDescription("Description")
            .setNegativeButtonText("OK")
            .build()

        authenticateButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}
