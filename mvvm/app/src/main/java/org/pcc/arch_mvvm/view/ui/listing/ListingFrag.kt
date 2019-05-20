package org.pcc.arch_mvvm.view.ui.listing


import android.os.Bundle
import android.util.Log
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
    private lateinit var viewDataBinding: ListingFragBinding
    private lateinit var listingAdapter: ListingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = ListingFragBinding.inflate(inflater, container, false).apply {
            listingVM = ViewModelProviders.of(this@ListingFrag).get(ListingViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.listingVM?.fetchListing()

        setAdapter()
        registerObservers()
    }

    private fun registerObservers() {
        viewDataBinding.listingVM?.liveListing?.observe(viewLifecycleOwner,
            Observer {
                listingAdapter.refreshListing(it)
            })

        viewDataBinding.listingVM?.status?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setAdapter() {
        if (viewDataBinding.listingVM != null) {
            listingAdapter = ListingAdapter()
            val layoutManager = LinearLayoutManager(activity)
            listingRecyclerView.layoutManager = layoutManager
            listingRecyclerView.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            listingRecyclerView.adapter = listingAdapter
        }
    }


}