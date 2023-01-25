package com.example.three_modules.app.presentation.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.di.activity.ActivityComponent
import com.example.three_modules.app.di.activity.ActivityModule
import com.example.three_modules.app.di.activity.DaggerActivityComponent
import com.example.three_modules.databinding.ActivityMainBinding
import com.example.three_modules.test.TestManager
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    private lateinit var mapView: MapView
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView



    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(activity = this))
            .build()
    }

    @Inject
    lateinit var testManager: TestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitInitializer.initialize("3eb038a7-9dc2-4c92-95dc-7fc8bd7239de", this)//MapKit Yandex
        setContentView(R.layout.item_main_recycler)
        mapView = findViewById(R.id.mapview)
        mapView.map.move(
            CameraPosition( Point(47.208739, 38.936695), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null)



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
    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    object MapKitInitializer {
        private var initialized = false

        fun initialize(apiKey: String, context: Context) {
            if (initialized) {
                return
            }
            MapKitFactory.setApiKey(apiKey)
            MapKitFactory.initialize(context)
            initialized = true
        }
    }
}