package org.pcc.searchinrecyclerview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import org.pcc.searchinrecyclerview.R;
import org.pcc.searchinrecyclerview.model.TheCard;

import java.util.ArrayList;

/**
 * Created by ptyagi on 9/8/17.
 */
//model data display configuration happens here
public class MyRecyclerViewAdapter
        extends RecyclerView.Adapter<MyRecyclerViewHolder>
        implements Filterable {

    private Context mContext;
    private MyRecyclerViewHolder viewHolder;
    private SearchFilter searchFilter;

    ArrayList<TheCard> theCards, filteredCardList;

    public MyRecyclerViewAdapter(Context context, ArrayList<TheCard> cards) {
        this.mContext = context;
        this.theCards = cards;
        this.filteredCardList = cards;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, null);
        viewHolder = new MyRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.tvCardId.setText(theCards.get(position).getCardId());
        holder.tvCardName.setText(theCards.get(position).getName());

        holder.setCardItemClickListener(new CardItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Toast.makeText(mContext, theCards.get(pos).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.theCards.size();
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null) {
            searchFilter = new SearchFilter(filteredCardList, this);
        }
        return searchFilter;
    }
}
