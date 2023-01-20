package com.example.three_modules.app.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.di.activity.ActivityComponent
import com.example.three_modules.app.di.activity.ActivityModule
import com.example.three_modules.app.di.activity.DaggerActivityComponent
import com.example.three_modules.app.presentation.ui.fragments.SettingFragment
import com.example.three_modules.databinding.ActivityMainBinding
import com.example.three_modules.test.TestManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainActivityViewModel


    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(activity = this))
            .build()
    }

    @Inject
    lateinit var testManager: TestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.amFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(binding.amToolbar.toolbar)
        val config = AppBarConfiguration(navController.graph)
        binding.amToolbar.toolbar.setupWithNavController(navController,
            config)
        binding.amToolbar.tbImageButton.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_settingFragment)
        }
    }
}