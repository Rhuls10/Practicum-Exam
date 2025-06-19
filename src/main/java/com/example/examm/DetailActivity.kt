package com.example.examm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve arrays from intent
        val songTitles = intent.getStringArrayExtra("songTitles") ?: emptyArray()
        val artists = intent.getStringArrayExtra("artists") ?: emptyArray()
        val ratings = intent.getIntArrayExtra("ratings") ?: intArrayOf()
        val comments = intent.getStringArrayExtra("comments") ?: emptyArray()

        // Display song list using a loop
        val tvSongList = findViewById<TextView>(R.id.tvSongList)
        val songDetails = StringBuilder()
        for (i in songTitles.indices) {
            songDetails.append("Song: ${songTitles[i]}, Artist: ${artists[i]}, Rating: ${ratings[i]}, Comment: ${comments[i]}\n")
        }
        tvSongList.text = songDetails.toString()

        // Button to calculate and display average rating
        val btnAverageRating = findViewById<Button>(R.id.btnAverageRating)
        val tvAverageRating = findViewById<TextView>(R.id.tvAverageRating)
        btnAverageRating.setOnClickListener {
            if (ratings.isNotEmpty()) {
                val average = ratings.average()
                tvAverageRating.text = "Average Rating: %.2f".format(average)
            } else {
                tvAverageRating.text = "No ratings available"
            }
        }

        // Button to return to main screen
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}

