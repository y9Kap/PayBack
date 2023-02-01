package com.example.payback

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.payback.Constants.COST_JOB
import com.example.payback.Constants.COST_SUBWAY
import com.example.payback.Constants.COST_BUS
import com.example.payback.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingClass : ActivityMainBinding
    var intermediate = 0
    var countBus = 1
    var countJob = 1
    var countSubway = 1
    var pref : SharedPreferences? = null
    var memory = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        pref = getSharedPreferences("PAYBACK", Context.MODE_PRIVATE)
        memory = pref?.getInt("result", 0)!!
        bindingClass.textResult.text = "$memory"

        payBack()
    }

    private fun payBack() {
        reset()
        checkingButtonBus()
        checkingButtonSubway()
        checkingButtonJob()
        checkingButtonCheck()
        checkingButtonSave()
    }

    @SuppressLint("SetTextI18n")
    fun checkingButtonBus() {

        bindingClass.buttonBus.setOnClickListener {
            bindingClass.textBus.text = "Автобус ${countBus}х"
            countBus++
            intermediate += COST_BUS
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkingButtonJob() {
        bindingClass.buttonJob.setOnClickListener {
            bindingClass.textJob.text = "Работа ${countJob}х"
            countJob++
            intermediate += COST_JOB
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkingButtonSubway() {
        bindingClass.buttonSubway.setOnClickListener {
            bindingClass.textSubway.text = "Метро ${countSubway}х"
            countSubway++
            intermediate += COST_SUBWAY
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkingButtonCheck() {
        bindingClass.buttonCheck.setOnClickListener {
            if (intermediate != 0) {
                bindingClass.textResult.text = "$memory + $intermediate"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkingButtonSave() {
        bindingClass.buttonSave.setOnClickListener {
            if (intermediate != 0) {
                memory += intermediate
                bindingClass.textBus.text = "Автобус"
                bindingClass.textJob.text = "Работа"
                bindingClass.textSubway.text = "Метро"
                intermediate = 0
                countSubway = 1
                countBus = 1
                countJob = 1
                bindingClass.textResult.text = "$memory"
                saveData()
            }
        }
    }

    private fun reset() {
        bindingClass.buttonReset.setOnClickListener {
            bindingClass.textBus.text = "Автобус"
            bindingClass.textJob.text = "Работа"
            bindingClass.textSubway.text = "Метро"
            bindingClass.textResult.text = "$memory"
            intermediate = 0
            countSubway = 1
            countBus = 1
            countJob = 1
        }
    }

    fun saveData(){
        val editor = pref?.edit()
        editor?.putInt("result", memory)
        editor?.apply()
    }
}