package com.example.discover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.discover.R
import com.example.discover.databinding.ActivityMainBinding
import com.example.discover.viewModels.NewsViewModel
import com.example.discover.viewModels.NewsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, findNavController(R.id.navHostFragment))



    }

}