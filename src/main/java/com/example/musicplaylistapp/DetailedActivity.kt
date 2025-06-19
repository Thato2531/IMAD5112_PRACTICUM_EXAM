package com.example.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailedActivity : AppCompatActivity() {

    private lateinit var songTitles: Array<String>
    private lateinit var artists: Array<String>
    private lateinit var ratings: IntArray
    private lateinit var comments: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        // Instead of retrieving from Intent, assign sample data directly:
        songTitles = arrayOf("Moth balls", "Messy", "Sing about me/I'm dying of thirsty" , "Mask off")
        artists = arrayOf("PARTYNEXTDOOR & Drake", "Quavo & Takeoff", "Kendrick Lamar" , "Future")
        ratings = intArrayOf(5, 4, 5, 3)
        comments = arrayOf("Classic song", "Amazing!", "Love this song" , "Good song!")


        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val btnAvgRating = findViewById<Button>(R.id.btnAvgRating)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val textViewDisplay = findViewById<TextView>(R.id.textViewDisplay)

        btnDisplay.setOnClickListener {
            val builder = StringBuilder()
            var hasSongs = false
            for (i in songTitles.indices) {
                if (songTitles[i].isNotEmpty()) {
                    hasSongs = true
                    builder.append("Song: ${songTitles[i]}\n")
                    builder.append("Artist: ${artists[i]}\n")
                    builder.append("Rating: ${ratings[i]}\n")
                    builder.append("Comments: ${comments[i]}\n\n")
                }
            }
            if (hasSongs) {
                textViewDisplay.text = builder.toString()
            } else {
                textViewDisplay.text = "No songs in playlist."
            }
        }

        btnAvgRating.setOnClickListener {
            var sum = 0
            var count = 0
            for (rating in ratings) {
                if (rating > 0) {
                    sum += rating
                    count++
                }
            }
            val average = if (count > 0) sum.toDouble() / count else 0.0
            Toast.makeText(this, "Average Rating: %.2f".format(average), Toast.LENGTH_LONG).show()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}