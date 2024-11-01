package com.example.premierleagueclub

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListClubs(): ArrayList<Club> {
        val dataName = resources.getStringArray(R.array.data_name_club)
        val dataDescription = resources.getStringArray(R.array.data_description_club)
        val dataLogo = resources.obtainTypedArray(R.array.data_logo_club)
        val dataStadium = resources.getStringArray(R.array.data_stadium)
        val dataKeyPlayer = resources.getStringArray(R.array.data_key_player)
        val dataManager = resources.getStringArray(R.array.data_manager)
        val dataFoundedYear = resources.getStringArray(R.array.data_founded_year)
        val dataTrophies = resources.getStringArray(R.array.data_trophies)
        val dataWebsite = resources.getStringArray(R.array.data_website)

        val listClub = ArrayList<Club>()

        for (i in dataName.indices) {
            val club = Club(
                name = dataName[i],
                description = dataDescription[i],
                photo = dataLogo.getResourceId(i, -1),
                stadium = dataStadium[i],
                keyPlayer = dataKeyPlayer[i],
                manager = dataManager[i],
                foundedYear = dataFoundedYear[i],
                trophies = dataTrophies[i],
                website = dataWebsite[i]
            )
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
        val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
        intentToDetail.putExtra(DetailActivity.EXTRA_DATA, club)
        startActivity(intentToDetail)
    }
}