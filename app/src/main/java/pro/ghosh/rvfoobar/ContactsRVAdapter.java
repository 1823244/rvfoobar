package pro.ghosh.rvfoobar;

import android.content.Context;
import android.util.Log;
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
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactsRVAdapter extends RecyclerView.Adapter<ContactsRVAdapter.ViewHolder> {

    private final Context ctx;
    private ArrayList<Card> cards = new ArrayList<>();
    private final String CONTACTS_RV_DEBUG = "CONTACTS_RV_DEBUG";

    public ContactsRVAdapter(Context context) {
        this.ctx = context;
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
        holder.cvParent.setOnClickListener(v ->
                Log.d(CONTACTS_RV_DEBUG, cards.get(position).getTitle() + " Clicked")
        );
        holder.tvCardTitle.setText(cards.get(position).getTitle());
        holder.tvCardContent.setText(cards.get(position).getContent());
        Glide.with(ctx)
                .asBitmap()
                .load(cards.get(position).getImageUrl())
                .into(holder.ivCardImage);
        holder.btnCardAction.setOnClickListener(v ->
                Snackbar.make(
                        holder.itemView,
                        cards.get(position).getTitle() + " Clicked",
                        Snackbar.LENGTH_INDEFINITE
                )
                        .setAction("Dismiss", _v ->
                                Toast.makeText(ctx, "Snackbar Dismissed", Toast.LENGTH_SHORT)
                                        .show()
                        )
                        .setActionTextColor(ctx.getColor(R.color.teal_200))
                        .show()
        );
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCardTitle;
        private final TextView tvCardContent;
        private final ImageView ivCardImage;
        private final Button btnCardAction;
        private final MaterialCardView cvParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardTitle = itemView.findViewById(R.id.tvCardTitle);
            tvCardContent = itemView.findViewById(R.id.tvCardContent);
            ivCardImage = itemView.findViewById(R.id.ivCardImage);
            btnCardAction = itemView.findViewById(R.id.btnCardAction);
            cvParent = itemView.findViewById(R.id.cvParent);
        }
    }
}
