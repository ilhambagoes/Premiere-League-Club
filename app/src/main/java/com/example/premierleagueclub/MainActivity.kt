package com.example.premierleagueclub

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                Toast.makeText(this, "Anda memilih menu about", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
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

        listClubAdapter.setOnItemClickCallback(object : ListClubAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Club) {
                showSelectedClub(data)
            }
        })
    }

    private fun showSelectedClub(club: Club) {
        Toast.makeText(this, "Kamu memilih " + club.name, Toast.LENGTH_SHORT).show()
    }
}