package com.example.premierleagueclub

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val ivClubLogo: ImageView = findViewById(R.id.club_logo)
        val tvClubDescription: TextView = findViewById(R.id.club_description)
        val tvClubStadium: TextView = findViewById(R.id.club_stadium)
        val tvKeyPlayer: TextView = findViewById(R.id.key_player)
        val tvManager: TextView = findViewById(R.id.manager)
        val tvFoundedYear: TextView = findViewById(R.id.founded_year)
        val tvTrophies: TextView = findViewById(R.id.trophies)
        val tvWebsite: TextView = findViewById(R.id.website)

        val club = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Club::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (club != null) {
            supportActionBar?.title = club.name
            ivClubLogo.setImageResource(club.photo)
            tvClubDescription.text = club.description
            tvClubStadium.text = club.stadium
            tvKeyPlayer.text = "Pemain Kunci : ${club.keyPlayer}"
            tvManager.text = "Manager : ${club.manager}"
            tvFoundedYear.text = "Tahun Berdiri : ${club.foundedYear}"
            tvTrophies.text = "Jumlah Trofi : ${club.trophies} Trofi"
            tvWebsite.text = club.website
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}