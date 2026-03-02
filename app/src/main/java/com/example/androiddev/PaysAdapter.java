package com.example.androiddev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaysAdapter extends RecyclerView.Adapter<PaysAdapter.PaysVH> {

    private final ArrayList<PaysItem> items;
    private int selectedPos = -1;

    public PaysAdapter(ArrayList<PaysItem> items) {
        this.items = items;
    }

    public int getSelectedPos() { return selectedPos; }
    public PaysItem getItem(int pos) { return items.get(pos); }

    public void setSelectedPos(int newPos) {
        int oldPos = selectedPos;
        selectedPos = newPos;
        if (oldPos != -1) notifyItemChanged(oldPos);
        if (newPos != -1) notifyItemChanged(newPos);
    }

    public void addItem(PaysItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void updateTitle(int pos, String newTitle) {
        items.get(pos).setTitle(newTitle);
        notifyItemChanged(pos);
    }

    public void removeItem(int pos) {
        items.remove(pos);
        notifyItemRemoved(pos);
        selectedPos = -1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaysVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pays, parent, false);
        return new PaysVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaysVH holder, int position) {
        PaysItem item = items.get(position);

        holder.txtPays.setText(item.getTitle());
        holder.imgPays.setImageResource(item.getImageResId());

        holder.itemView.setBackgroundResource(
                (position == selectedPos) ? android.R.color.darker_gray : android.R.color.transparent
        );

        holder.itemView.setOnClickListener(v -> setSelectedPos(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PaysVH extends RecyclerView.ViewHolder {
        TextView txtPays;
        ImageView imgPays;

        public PaysVH(@NonNull View itemView) {
            super(itemView);
            txtPays = itemView.findViewById(R.id.txt_pays);
            imgPays = itemView.findViewById(R.id.img_pays);
        }
    }
}
