package pro.ghosh.rvfoobar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("Card Uno", "Lorem Ipsum", "https://source.unsplash.com/3mIM_MxpnfY"));
        cards.add(new Card("Card Duos", "Lorem Ipsum", "https://source.unsplash.com/C6y2ajVXKyI"));
        cards.add(new Card("Card Tres", "Lorem Ipsum", "https://source.unsplash.com/uN47f6df8IM"));
        cards.add(new Card("Card Quadros", "Lorem Ipsum", "https://source.unsplash.com/PTPrhgCEc8Q"));

        ContactsRVAdapter contactsAdapter = new ContactsRVAdapter(this);
        contactsAdapter.setCards(cards);

        RecyclerView rvCards = findViewById(R.id.rvCards);
        rvCards.setAdapter(contactsAdapter);
        rvCards.setLayoutManager(new LinearLayoutManager(this));
    }
}