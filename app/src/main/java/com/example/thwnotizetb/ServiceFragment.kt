package com.example.thwnotizetb

import androidx.fragment.app.Fragment


/**
 * Constants Values
 */
const val LOCATION_REQUEST = 100
const val LOCATION_PERMISSION_REQUEST = 101

class ServiceFragment: Fragment(){

  /* private lateinit var locationViewModel: LocationViewModel
    private var isGPSEnabled = false

    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    /**
     * onCreate of activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Instance of LocationViewModel
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)


        //Check weather Location/GPS is ON or OFF
        LocationUtil(this).turnGPSOn(object :
            LocationUtil.OnLocationOnListener {

            override fun locationStatus(isLocationOn: Boolean) {
                this@MainActivity.isGPSEnabled = isLocationOn
            }
        })
    }
    /**
     * Observe LocationViewModel LiveData to get updated location
     */
    private fun observeLocationUpdates() {
        locationViewModel.getLocationData.observe(this, Observer {
            longitude.text = it.longitude.toString()
            latitude.text = it.latitude.toString()
            info.text = getString(R.string.location_successfully_received)
        })
    }


    /**
     * onStart lifecycle of activity
     */
    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }


    /**
     * Initiate Location updated by checking Location/GPS settings is ON or OFF
     * Requesting permissions to read location.
     */
    private fun startLocationUpdates() {
        when {
            !isGPSEnabled -> {
                info.text = getString(R.string.enable_gps)
            }



*/

}