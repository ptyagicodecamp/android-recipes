package org.pcc.searchinrecyclerview.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import org.pcc.searchinrecyclerview.R;
import org.pcc.searchinrecyclerview.model.TheCard;
import org.pcc.searchinrecyclerview.view.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView mSeachView;
    private RecyclerView mRecyclerView;
    //private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeachView = (SearchView) findViewById(R.id.searchView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set RecyclerView layout as LinearLayout to laid out data in it
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set ItemAnimator for RecyclerView list items
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Set adapter to display contents in View
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, getCards());
        mRecyclerView.setAdapter(adapter);

        mSeachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    //Generate DummyData
    public ArrayList<TheCard> getCards() {
        ArrayList<TheCard> theCards = new ArrayList<TheCard>();

        TheCard card = new TheCard();
        card.setCardId("my_card_1");
        card.setName("This is Card 1");
        theCards.add(card);

        card = new TheCard();
        card.setCardId("my_card_2");
        card.setName("This is Card 2");
        theCards.add(card);

        card = new TheCard();
        card.setCardId("my_card_3");
        card.setName("This is Card 3");
        theCards.add(card);

        card = new TheCard();
        card.setCardId("my_card_4");
        card.setName("This is Card 4");
        theCards.add(card);

        card = new TheCard();
        card.setCardId("my_card_5");
        card.setName("This is Card 5");
        theCards.add(card);

        return theCards;
    }
}
