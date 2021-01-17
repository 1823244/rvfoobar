package pro.ghosh.rvfoobar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactsRVAdapter extends RecyclerView.Adapter<ContactsRVAdapter.ViewHolder> {

    private final Context ctx;
    private ArrayList<Card> cards = new ArrayList<>();

    public ContactsRVAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCardTitle.setText(cards.get(position).getTitle());
        holder.tvCardContent.setText(cards.get(position).getContent());
        Glide.with(ctx)
                .asBitmap()
                .load(cards.get(position).getImageUrl())
                .into(holder.ivCardImage);
        holder.btnCardAction.setOnClickListener(v ->
                Snackbar.make(holder.itemView, cards.get(position).getTitle() + " Clicked", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Dismiss", _v ->
                                Toast.makeText(ctx, "Snackbar Dismissed", Toast.LENGTH_SHORT).show()
                        )
                        .show());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCardTitle;
        private final TextView tvCardContent;
        private final ImageView ivCardImage;
        private final Button btnCardAction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardTitle = itemView.findViewById(R.id.tvCardTitle);
            tvCardContent = itemView.findViewById(R.id.tvCardContent);
            ivCardImage = itemView.findViewById(R.id.ivCardImage);
            btnCardAction = itemView.findViewById(R.id.btnCardAction);
        }
    }
}
