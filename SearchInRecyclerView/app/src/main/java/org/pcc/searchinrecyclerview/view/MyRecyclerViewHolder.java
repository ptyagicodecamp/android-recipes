package org.pcc.searchinrecyclerview.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pcc.searchinrecyclerview.R;

/**
 * Created by ptyagi on 9/8/17.
 */
//Handles displaying data in UI Views
public class MyRecyclerViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

    TextView tvCardId;
    TextView tvCardName;

    CardItemClickListener cardItemClickListener;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);

        this.tvCardId = (TextView) itemView.findViewById(R.id.tvCardId);
        this.tvCardName = (TextView) itemView.findViewById(R.id.tvCardName);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.cardItemClickListener.onItemClick(view, getLayoutPosition());

    }

    public void setCardItemClickListener(CardItemClickListener listener) {
        this.cardItemClickListener = listener;
    }
}
