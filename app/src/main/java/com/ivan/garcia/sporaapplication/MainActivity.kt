package com.ivan.garcia.sporaapplication

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.ivan.garcia.sporaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView4.setImageResource(R.drawable.android_studio)
        binding.imageView5.setImageResource(R.drawable.android_studio_svg)
        binding.imageView6.setImageResource(R.drawable.weather_icon)

        binding.textView.text = getText(R.string.app_name)
        binding.textView.setText(R.string.app_name)

        val helloStr: String = getString(R.string.hello)
        println("the value of the string hello is: $helloStr")
        binding.textView6.text = helloStr;

        val name = "Mario"
        val age = 20
        val amount = 20.33333
        binding.textView7.text = getString(R.string.string_format_ex, name, age, amount, true)

        val text: String = getString(R.string.format_checkbox_tyc)
        val styledText: Spanned = Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        binding.textView8.text = styledText

        val persons = resources.getStringArray(R.array.persons)
        println(persons.contentToString())
    }
}