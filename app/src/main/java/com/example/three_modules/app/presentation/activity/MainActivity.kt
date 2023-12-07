package com.example.three_modules.app.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.App
import com.example.three_modules.app.di.activity.ActivityComponent
import com.example.three_modules.app.di.activity.ActivityModule
import com.example.three_modules.app.di.activity.DaggerActivityComponent
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.app.presentation.ui.fragments.settings.viewmodel.SettingViewModel
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListener
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListenerManager
import com.example.three_modules.databinding.ActivityMainBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import kotlinx.android.synthetic.main.fragment_coin.*
import kotlinx.android.synthetic.main.fragment_town.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity() : AppCompatActivity() {

//    @Inject 
//    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var mapView: MapView
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    private val settingViewModel: SettingViewModel by viewModels {
        (application as App).appComponent.provideViewModelFactory()
    }

    lateinit var activityComponent: ActivityComponent


    @Inject
    lateinit var toolbarListenerManager: ToolbarListenerManager

    private val listener = object : ToolbarListener {
        override fun changeToolbarButtonState(isSaveButton: Boolean) {
            binding.amToolbar.tbImageButton.setImageResource(
                if (isSaveButton)
                R.drawable.ic_save else R.drawable.ic_settings
            )
        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.initialize(this)
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as App).appComponent)
            .activityModule(ActivityModule(activity = this))
            .build()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        toolbarListenerManager.addListener(listener = listener)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.amFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(binding.amToolbar.toolbar)
        val config = AppBarConfiguration(navController.graph)
        binding.amToolbar.toolbar.setupWithNavController(
            navController,
            config
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.weatherFragment2) {
                binding.amToolbar.tbImageButton.visibility = View.GONE
            }
            if (destination.id == R.id.coinFragment) {
                binding.amToolbar.tbImageButton.visibility = View.GONE
            }
            if (destination.id == R.id.cityFragment) {
                binding.amToolbar.tbImageButton.visibility = View.GONE
            }
            if (destination.id == R.id.mainFragment) {
                binding.amToolbar.tbImageButton.visibility = View.VISIBLE
                binding.amToolbar.tbImageButton.setImageResource(R.drawable.ic_settings)
                binding.amToolbar.tbImageButton.setOnClickListener {
                    navController.navigate(R.id.action_mainFragment_to_settingFragment)
                }
            }
            if (destination.id == R.id.settingFragment) {
                binding.amToolbar.tbImageButton.setOnClickListener {
                    lifecycleScope.launch {
                        Toast.makeText(applicationContext, "Cохранено", Toast.LENGTH_SHORT).show()
                        toolbarListenerManager.saveModules()
                    }
                }

            }
        }
        binding.amToolbar.toolbar.setTitleTextColor(android.graphics.Color.WHITE)
    }


    override fun onDestroy() {
        super.onDestroy()
        toolbarListenerManager.removeListener(listener = listener)
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
}