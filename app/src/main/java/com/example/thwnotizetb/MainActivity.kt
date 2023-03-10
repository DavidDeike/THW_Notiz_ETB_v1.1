package com.example.thwnotizetb

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.thwnotizetb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
       // setupNavigation()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController : NavController = findNavController(R.id.nav_host_fragment_content_main)
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
    }

   //override fun onSupportNavigateUp(): Boolean {
   //     return navigateUp(
   //         Navigation.findNavController(
     //           this,
       //         androidx.activity.R.id.view_tree_on_back_pressed_dispatcher_owner
         //   ), drawerLayout
        //)
    //}

  /*  @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawable.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        val navController =
            Navigation.findNavController(this, androidx.activity.R.id.nav_host_fragment)

        setupActionBarWithNavController(this, navController, drawerLayout)


        navigationView.setNavigationItemSelectedListener { menuItem -> menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }


        setupWithNavController(navigationView, navController)
    }*/
}