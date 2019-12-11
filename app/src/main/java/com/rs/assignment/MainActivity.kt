package com.rs.assignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rs.assignment.API.APICALL
import com.rs.assignment.API.GetDataService
import com.rs.assignment.adapters.AlbumsAdapter
import com.rs.assignment.models.Album
import com.rs.assignment.models.AlbumJsonResponse
import com.rs.assignment.models.CustomModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView

    private lateinit var adapter: AlbumsAdapter
    private var sortedMap = HashMap<String, ArrayList<String>>()
    private var customModelList = ArrayList<CustomModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = list_of_items
        getAlbumsList()

    }


    private fun getAlbumsList() {


        progressBar.visibility = View.VISIBLE

        val service: GetDataService =
            APICALL.getRetrofitInstance().create(GetDataService::class.java)


        service.albumsList.enqueue(object : Callback<AlbumJsonResponse> {
            override fun onFailure(call: Call<AlbumJsonResponse>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()


            }

            override fun onResponse(
                call: Call<AlbumJsonResponse>,
                response: Response<AlbumJsonResponse>
            ) {

                Toast.makeText(
                    this@MainActivity,
                    "Loading Data...",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.visibility = View.GONE

                var dataList = response.body()!!.data
                var sortedDataList = sortBasedOnArtist(dataList)
                sortedMap = getSortedMap(sortedDataList)
                customModelList.clear()
                for (entry in sortedMap.entries) {
                    var artist = entry.key
                    var songList = ArrayList<String>()
                    entry.value.forEach {
                        Log.e("Value", it)
                        songList.add(it)
                    }
                    customModelList.add(CustomModel(artist, songList))
                }
                generateAlbumList(customModelList)

            }

        })


    }

    private fun getSortedMap(sortedDataList: List<Album>): HashMap<String, ArrayList<String>> {
        var pos = 0
        var keyPos = 0
        var songList = ArrayList<String>()
        do {
            if (sortedMap[sortedDataList[pos].artist] == null) {
                keyPos = pos
                songList.clear()
                songList.add(sortedDataList[keyPos].name)
                sortedMap.put(sortedDataList[keyPos].artist, songList)
            } else {
                songList.add(sortedDataList[pos].name)
                sortedMap.put(sortedDataList[keyPos].artist, songList)
            }
            pos++
        } while (sortedDataList.size > pos)

        return sortedMap
    }

    private fun sortBasedOnArtist(data: List<Album>): List<Album> {

        var sortedList = data.sortedWith(compareBy { it.artist })

        return sortedList

    }

    private fun sortBasedOnAlbum(data: List<Album>): List<Album> {

        var sortedList = data.sortedWith(compareBy { it.album })

        return sortedList

    }

    private fun generateAlbumList(
        data: ArrayList<CustomModel>
    ) {


        adapter = AlbumsAdapter(data)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


    }

}
