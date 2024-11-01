package com.example.premierleagueclub

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ivClubLogo: ImageView = findViewById(R.id.club_logo)
        val tvClubName: TextView = findViewById(R.id.club_name)
        val tvClubDescription: TextView = findViewById(R.id.club_description)
        val tvClubStadium: TextView = findViewById(R.id.club_stadium)
        val tvKeyPlayer: TextView = findViewById(R.id.key_player)
        val tvManager: TextView = findViewById(R.id.manager)
        val tvFoundedYear: TextView = findViewById(R.id.founded_year)
        val tvTrophies: TextView = findViewById(R.id.trophies)
        val tvWebsite: TextView = findViewById(R.id.website)

        val club  = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Club::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (club != null) {
            ivClubLogo.setImageResource(club.photo)
            tvClubName.text = club.name
            tvClubDescription.text = club.description
            tvClubStadium.text = club.stadium
            tvKeyPlayer.text = club.keyPlayer
            tvManager.text = club.manager
            tvFoundedYear.text = club.foundedYear
            tvTrophies.text = club.trophies
            tvWebsite.text = club.website
        }
    }
}