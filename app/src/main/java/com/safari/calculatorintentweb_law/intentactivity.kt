package com.safari.calculatorintentweb_law

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class intentactivity : AppCompatActivity() {
     var buttonSms: Button ?= null
     var buttonEmail: Button ?= null
    var buttonShare: Button ?= null
    var buttonCamera: Button ?= null
     var buttonStk: Button ?= null
     var buttonCall: Button ?= null
     var buttonDial: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        buttonSms = findViewById(R.id.btn_sms)
        buttonEmail = findViewById(R.id.btn_email)
        buttonShare = findViewById(R.id.btn_share)
        buttonCamera = findViewById(R.id.btn_camera)
        buttonStk = findViewById(R.id.btn_stk)
        buttonCall = findViewById(R.id.btn_call)
        buttonDial = findViewById(R.id.btn_dial)

        buttonSms!!.setOnClickListener {
            val uri = Uri.parse("smsto:0715950987")

            val intent = Intent(Intent.ACTION_SENDTO, uri)

            intent.putExtra("Wassup nigga", "Pop out at yo party")

            startActivity(intent)
        }

        buttonEmail!!.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")

            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")

            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        buttonShare!!.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)
        }

        buttonCamera!!.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(takePictureIntent, 1)

        }

        buttonStk!!.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")

            simToolKitLaunchIntent?.let { startActivity(it) }

        }

        buttonDial!!.setOnClickListener {
            val phone = "+34666777888"

            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))

            startActivity(intent)
        }

        buttonCall!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"))

            if (ContextCompat.checkSelfPermission(
                    this@intentactivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@intentactivity,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }

        }
    }
}
