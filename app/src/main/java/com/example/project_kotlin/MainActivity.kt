package com.example.project_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.databinding.ActivityMainBinding
import com.example.project_kotlin.screens.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        setBottomNavListener()

        //init the db
        DBHelper.init(applicationContext)
    }

    private fun setBottomNavListener(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.gerechten -> replaceFragment(Gerechten())
                R.id.ingredienten -> replaceFragment(Ingredienten())
                R.id.planning -> replaceFragment(Planning())
                R.id.shoppinglist -> replaceFragment(ShoppingList())
                else -> {
                    replaceFragment(Home())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}