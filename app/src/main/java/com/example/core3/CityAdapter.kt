package com.example.core3
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CityAdapter(private val CityList: List<CityModel>, private val rowClickListner: RowClickListner) :
        RecyclerView.Adapter<CityAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val generateRow = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,
                parent, false)
        return ExampleViewHolder(generateRow)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = CityList[position]
        holder.rowImage.setImageResource(currentItem.imagePath)
        holder.rowCity.text = currentItem.city
        holder.rowTimeZone.text = currentItem.timezone_location
        holder.itemView.setOnClickListener {
            rowClickListner.onRowCellClickListener(currentItem)
        }
    }
    override fun getItemCount() = CityList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rowImage: ImageView = itemView.findViewById(R.id.iv_heart)
        val rowCity: TextView = itemView.findViewById(R.id.tv_city)
        val rowTimeZone: TextView = itemView.findViewById(R.id.tv_timezone)
    }
}