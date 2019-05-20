package org.pcc.arch_mvvm.view.adapter

import android.util.Log
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_data.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.pcc.arch_mvvm.BR
import org.pcc.arch_mvvm.R
import org.pcc.arch_mvvm.model.RowData

class ListingViewHolder
constructor(private val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root) {

    private val image = itemView.image
    fun init(rowData :RowData) {
        dataBinding.setVariable(BR.rowData, rowData)
        dataBinding.executePendingBindings()

        Glide.with(itemView.context)
            .load(rowData.owner.avatar_url)
            .into(image)

        itemView.onClick {
            val bundle = bundleOf("url" to rowData.owner.html_url)
            Navigation.findNavController(itemView).navigate(R.id.action_listingFrag_to_detailFrag, bundle)
        }
    }
}