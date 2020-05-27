package com.example.houzboat

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission

class PermissionsHelper(private val activity: Activity) {

    fun hasUserPermission(): Boolean {
        val permissionArray = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (!isPermissionGranted(permissionArray)) {
            requestPermissions(activity, permissionArray, 200)
        }
        return isPermissionGranted(permissionArray)
    }

    private fun isPermissionGranted(permissionArray: Array<String>): Boolean {
        val permissionsToCheck = mutableListOf<Int>()
        permissionArray.map {
            permissionsToCheck.add(checkSelfPermission(activity, it))
        }
        return permissionsToCheck.all {
            it == PackageManager.PERMISSION_GRANTED
        }
    }
}