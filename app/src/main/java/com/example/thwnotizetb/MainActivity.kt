package com.example.thwnotizetb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

   /* private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
       // setupNavigation()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //setSupportActionBar(binding.toolbar)

        val navController : NavController = findNavController(R.id.nav_graph)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_etb -> true
            R.id.action_datapicker -> true
            R.id.action_archiv -> true
            R.id.action_diensta -> true
            R.id.action_impressum -> true
            R.id.action_links -> true
            R.id.action_service -> true
            R.id.action_notiz -> true
            R.id.action_tresor -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
