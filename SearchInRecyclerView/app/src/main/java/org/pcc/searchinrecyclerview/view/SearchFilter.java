package org.pcc.searchinrecyclerview.view;

import android.widget.Filter;

import org.pcc.searchinrecyclerview.model.TheCard;

import java.util.ArrayList;

/**
 * Created by ptyagi on 9/8/17.
 */
//Filters data as per search term entered
public class SearchFilter extends Filter {
    private MyRecyclerViewAdapter adapter;
    private ArrayList<TheCard> filteredCardList;

    public SearchFilter(ArrayList<TheCard> filteredCardList, MyRecyclerViewAdapter myRecyclerViewAdapter) {
        this.filteredCardList = filteredCardList;
        this.adapter = myRecyclerViewAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();

        if (charSequence != null && charSequence.length() > 0) {
            charSequence = charSequence.toString().toLowerCase();
            ArrayList<TheCard> filteredCards = new ArrayList<TheCard>();

            for (int i=0; i < filteredCardList.size(); i++) {
                if (filteredCardList.get(i).getCardId().toLowerCase().contains(charSequence)
                        ||
                        filteredCardList.get(i).getName().toLowerCase().contains(charSequence)   ) {
                    filteredCards.add(filteredCardList.get(i));
                }
            }

            filterResults.count = filteredCards.size();
            filterResults.values = filteredCards;
        } else {
            filterResults.count = filteredCardList.size();
            filterResults.values = filteredCardList;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.theCards = (ArrayList<TheCard>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
