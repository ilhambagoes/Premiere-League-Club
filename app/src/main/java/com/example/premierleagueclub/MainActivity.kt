package com.example.premierleagueclub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvClub: RecyclerView
    private val list = ArrayList<Club>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvClub = findViewById(R.id.rv_club)
        rvClub.setHasFixedSize(true)

        list.addAll(getListClubs())
        showRecyclerList()
    }

    private fun getListClubs(): ArrayList<Club> {
        val dataName = resources.getStringArray(R.array.data_name_club)
        val dataDescription = resources.getStringArray(R.array.data_description_club)
        val dataLogo = resources.obtainTypedArray(R.array.data_logo_club)

        val listClub = ArrayList<Club>()

        for (i in dataName.indices) {
            val club = Club(dataName[i], dataDescription[i], dataLogo.getResourceId(i, -1))
            listClub.add(club)
        }

        return listClub
    }

    private fun showRecyclerList() {
        rvClub.layoutManager = LinearLayoutManager(this)
        val listClubAdapter = ListClubAdapter(list)
        rvClub.adapter = listClubAdapter
    }
}