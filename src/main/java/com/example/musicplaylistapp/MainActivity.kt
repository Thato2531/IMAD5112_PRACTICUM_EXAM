package com.example.musicplaylistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare arrays for song details
    private val songTitles = arrayOf("", "", "", "")
    private val artists = arrayOf("", "", "", "")
    private val ratings = arrayOf(0, 0, 0, 0)
    private val comments = arrayOf("", "", "", "")

    private var currentIndex = 0 // Next index to insert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAdd.setOnClickListener {
            if (currentIndex < 4) {
                promptForSongDetails(currentIndex)
            } else {
                Toast.makeText(this, "Playlist full", Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("songTitles", songTitles)
            intent.putExtra("artists", artists)
            intent.putExtra("ratings", ratings)
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finish()
        }
    }

    private fun promptForSongDetails(index: Int) {
        // Sequentially prompt for each detail
        promptForInput("Enter Song Title") { songTitle ->
            promptForInput("Enter Artist Name") { artistName ->
                promptForRating("Enter Rating (1-5)") { rating ->
                    promptForInput("Enter Comments") { comment ->
                        // Save data
                        songTitles[index] = songTitle
                        artists[index] = artistName
                        ratings[index] = rating
                        comments[index] = comment
                        currentIndex++
                        Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun promptForInput(
        title: String,
        callback: (String) -> Unit
    ) {
        val editText = android.widget.EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                val input = editText.text.toString()
                callback(input)
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun promptForRating(
        title: String,
        callback: (Int) -> Unit
    ) {
        val numberPicker = android.widget.NumberPicker(this)
        numberPicker.minValue = 1
        numberPicker.maxValue = 5

        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setView(numberPicker)
            .setPositiveButton("OK") { _, _ ->
                val rating = numberPicker.value
                callback(rating)
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }
}