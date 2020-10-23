package com.example.core3

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface RowClickListner {
    fun onRowCellClickListener(data:CityModel)
}



class MainActivity : AppCompatActivity(),RowClickListner {

    private var CityList = mutableListOf<CityModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        fillList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = CityAdapter(CityList, this )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



    }

    private fun fillList(){
        fun Resources.getRawTextFile(@RawRes id: Int) =
            openRawResource(R.raw.au_locations).bufferedReader().use { it.readText() }
        val dataFile = resources.getRawTextFile(R.raw.au_locations)
        val dataToList: List<String> = dataFile.split("\n")

        dataToList.forEach { row ->
            val rowData = row.split(",").toTypedArray()

//            Log.i("City",rowData[0]);
//            Log.i("Latitude ",rowData[1]);
//            Log.i("Longitude ",rowData[2]);
//            Log.i("Timezone ",rowData[3]);

            val city=rowData[0]
            val latitude =rowData[1]
            val longitude =rowData[2]
            val timezone =rowData[3]
            val imagepath:Int
            if(timezone.equals("Australia/Melbourne")){
                imagepath = R.drawable.heart_fill
                Log.i("TRUE",timezone)
            }else{
                imagepath = R.drawable.heart_empty
                Log.i("FALSE",timezone)
            }

            val singleCity =  CityModel(city,latitude,longitude,timezone,imagepath)
            CityList.add(singleCity)

        }

        val sortedList:MutableList<CityModel> = CityList.sortedBy{it.timezone_location} as MutableList<CityModel>
        CityList = sortedList;
    }

    override fun onRowCellClickListener(data: CityModel) {
        Toast.makeText(this,"Longitude:" +data.longitude+", Latitude:"+data.latitude, Toast.LENGTH_SHORT).show()
    }
}