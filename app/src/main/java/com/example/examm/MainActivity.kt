package com.example.examm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    // Declare and initialize arrays
    private val songTitles = mutableListOf<String>("Care" , "Shot for Me" , "Rain On Me" , "Clap & Tap")
    private val artists = mutableListOf<String>("Sonder" , "Drake" , "Ricky Rick" , "Kelvin Momo")
    private val ratings = mutableListOf<Int>(4 , 1 , 2 , 3)
    private val comments =
        mutableListOf<String>("Calm" , "Rap" , "Best Love song" , "Lovely Beat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val etSongTitle = findViewById<EditText>(R.id.etSongTitle)
        val etArtist = findViewById<EditText>(R.id.etArtist)
        val etRating = findViewById<EditText>(R.id.etRating)
        val etComment = findViewById<EditText>(R.id.etComment)
        val btnAddPlaylist = findViewById<Button>(R.id.btnAddPlaylist)
        val btnDetailedView = findViewById<Button>(R.id.btnDetailedView)

        // Add to Playlist button
        btnAddPlaylist?.setOnClickListener {
            val title = etSongTitle?.text.toString().trim()
            val artist = etArtist?.text.toString().trim()
            val ratingText = etRating?.text.toString().trim()
            val comment = etComment?.text.toString().trim()

            if (title.isEmpty() || artist.isEmpty() || ratingText.isEmpty() || comment.isEmpty()) {
                Toast.makeText(this@MainActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rating = ratingText.toIntOrNull()
            if (rating == null || rating !in 1..5) {
                Toast.makeText(this@MainActivity, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            songTitles.add(title)
            artists.add(artist)
            ratings.add(rating)
            comments.add(comment)

            Toast.makeText(this@MainActivity, "Song added to playlist", Toast.LENGTH_SHORT).show()
            etSongTitle?.text?.clear()
            etArtist?.text?.clear()
            etRating?.text?.clear()
            etComment?.text?.clear()
        }

        // Detailed View button
        btnDetailedView?.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("songTitles", songTitles.toTypedArray())
            intent.putExtra("artists", artists.toTypedArray())
            intent.putExtra("ratings", ratings.toIntArray())
            intent.putExtra("comments", comments.toTypedArray())
            startActivity(intent)
        }
    }
}