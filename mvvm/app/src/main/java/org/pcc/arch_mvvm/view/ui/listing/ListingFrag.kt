package org.pcc.arch_mvvm.view.ui.listing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.listing_frag.*
import org.jetbrains.anko.longToast
import org.pcc.arch_mvvm.databinding.ListingFragBinding
import org.pcc.arch_mvvm.view.adapter.ListingAdapter
import org.pcc.arch_mvvm.viewmodel.ListingViewModel

class ListingFrag: Fragment() {
    private lateinit var dataBinding: ListingFragBinding
    private lateinit var adapter: ListingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = ListingFragBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ListingFrag).get(ListingViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewmodel?.fetchListing()

        setAdapter()
        registerObservers()
    }

    private fun registerObservers() {
        dataBinding.viewmodel?.liveListing?.observe(viewLifecycleOwner,
            Observer {
                adapter.refreshListing(it)
            })

        dataBinding.viewmodel?.status?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setAdapter() {
        if (dataBinding.viewmodel != null) {
            adapter = ListingAdapter()
            val layoutManager = LinearLayoutManager(activity)
            listingRecyclerView.layoutManager = layoutManager
            listingRecyclerView.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            listingRecyclerView.adapter = adapter
        }
    }


}