package org.pcc.arch_mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.pcc.arch_mvvm.databinding.ListingFragBinding
import org.pcc.arch_mvvm.databinding.RowDataBinding
import org.pcc.arch_mvvm.model.RowData

class ListingAdapter : RecyclerView.Adapter<ListingViewHolder>() {
    var listing: List<RowData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = RowDataBinding.inflate(inflater, parent, false)
        return ListingViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return listing.size
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.init(listing[position])
    }

    fun refreshListing(list: List<RowData>) {
        this.listing = list
        notifyDataSetChanged()
    }

}