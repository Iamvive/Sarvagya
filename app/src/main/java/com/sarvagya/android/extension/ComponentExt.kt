package com.sarvagya.android.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sarvagya.android.R
import com.sarvagya.android.ui.home.HomeActivity

fun AppCompatActivity.navigateToActivity(
    destinationActivity: Class<*>,
    bundleKey: String? = null,
    bundle: Bundle? = null,
    shouldFinish: Boolean,
) {
    startActivity(
        Intent(this, destinationActivity)
            .putExtra(bundleKey, bundle)
    )
    if (shouldFinish) this.finish()
}

fun Fragment.attachWithReplace(activity: FragmentActivity,containerId: Int = R.id.mainContainer, backStackTag: String? = null, args: Bundle? = null) {
    this.arguments = args
    val transaction = activity.supportFragmentManager.beginTransaction()
    transaction.replace(containerId, this)
    backStackTag?.let { transaction.addToBackStack(backStackTag) }
    transaction.commit()
}

fun Fragment.attachWithAdd(containerId: Int, backStackTag: String?, args: Bundle?) {
    this.arguments = args
    val transaction = this.requireActivity().supportFragmentManager.beginTransaction()
    transaction.add(containerId, this)
    backStackTag?.let { transaction.addToBackStack(backStackTag) }
    transaction.commit()
}
