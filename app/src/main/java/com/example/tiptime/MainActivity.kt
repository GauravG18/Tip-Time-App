package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

     private fun calculateTip() {
         val stringInTextField=binding.costOfService.text.toString()
         val cost=stringInTextField.toDoubleOrNull()
         if(cost==null){
             binding.tipAmount.text=""
             return
         }
         val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
             R.id.option_twenty_percent -> 0.20
             R.id.option_eighteen_percent -> 0.18
             else -> 0.15
         }

         var tip=tipPercentage*cost
//         val roundUp=binding.roundUpSwitch.isChecked
         if(binding.roundUpSwitch.isChecked){
             tip=kotlin.math.ceil(tip)  //  ceil is for round pff
         }

         NumberFormat.getCurrencyInstance()
         val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
         binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}