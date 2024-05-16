package com.ehsieh2.letswatchtv

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.RatingBar
import android.widget.Toast
import android.content.Context

fun showRateAppDialog(context: Context) {
    // Inflate the dialog with custom view
    val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_rate_app, null)
    val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)

    AlertDialog.Builder(context)
        .setView(dialogView)
        .setTitle("Rate Us")
        .setMessage("If you enjoy using our app, please take a moment to rate it. Thank you for your support!")
        .setPositiveButton("Submit") { dialog, which ->
            val rating = ratingBar.rating
            Toast.makeText(context, "Thanks for your feedback! Rating: $rating", Toast.LENGTH_LONG).show()
            // Here, handle the rating value as needed, perhaps sending it to a server or analytics
        }
        .setNeutralButton("Remind Me Later", null)
        .setNegativeButton("No, Thanks") { dialog, which ->
            dialog.dismiss()
        }
        .show()
}
