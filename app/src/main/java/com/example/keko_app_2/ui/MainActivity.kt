package com.example.keko_app_2.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.keko_app_2.R
import com.example.keko_app_2.databinding.ActivityMainBinding
import com.example.keko_app_2.ui.fragment.DetailScrollingFragment
import com.example.keko_app_2.ui.fragment.LargeFragment
import com.example.keko_app_2.ui.fragment.MediumFragment
import com.example.keko_app_2.ui.fragment.SmallFragment

const val ID = R.id.container

class MainActivity : AppCompatActivity() {

    var flag: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val container = binding.container
        container.setOnClickListener {

            removeBackStack()
            getDetailtScreen()
            flag = false
        }

        listFragment()


    }

    override fun onBackPressed() {
        if (flag == true) {
            onDestroy()
            return
        } else {
            removeBackStack()
            listFragment()
        }

    }

    fun listFragment() {

        for (i in 1..16) {
            when {
                i % 2 == 0 -> {
                    addFragment(SmallFragment())
                }
                i % 5 == 0 -> {
                    addFragment(MediumFragment())
                }
                else -> {
                    addFragment(LargeFragment())
                }
            }
        }
        flag = true

    }

    fun addFragment(frg: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(ID, frg)
            .addToBackStack(null)
            .commit()
    }

    fun getDetailtScreen() {
        supportFragmentManager
            .beginTransaction()
            .add(ID, DetailScrollingFragment())
            .addToBackStack(null)
            .commit()

    }

    fun removeBackStack() = supportFragmentManager.popBackStack(
        null,
        FragmentManager.POP_BACK_STACK_INCLUSIVE
    )


}

