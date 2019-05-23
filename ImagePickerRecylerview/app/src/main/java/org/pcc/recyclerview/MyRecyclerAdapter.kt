package org.pcc.recyclerview

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyRecyclerAdapter(context: Context, items: ArrayList<Uri>): RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
    val mContext = context
    val mItems = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageURI(mItems.get(position))
        holder.title.setText("Image title goes here")
        holder.description.setText("Image description goes here")
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class MyViewHolder(mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        val imageView = mView.findViewById<ImageView>(R.id.image)
        val title = mView.findViewById<TextView>(R.id.title)
        val description = mView.findViewById<TextView>(R.id.desc)

    }

}