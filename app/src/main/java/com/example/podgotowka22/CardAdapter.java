package com.example.podgotowka22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uikit.R;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.Button.BthSmall;
import com.example.uikit.edit_text.common.Item;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private final List<Item> items;

    public CardAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_primary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item element = items.get(position);

        holder.tvName.setText(element.Name);
        holder.tvCategory.setText(element.Category);
        holder.tvPrice.setText(element.Price + " Р");

        holder.bthSmall.init(0);

        updateButtonState(holder.bthSmall, element.isAdded);

        if (holder.bthSmall.Bth != null) {
            holder.bthSmall.Bth.setOnClickListener(v -> {
                element.isAdded = !element.isAdded;
                updateButtonState(holder.bthSmall, element.isAdded);
            });
        }
    }

    private void updateButtonState(BthSmall btn, boolean isAdded) {
        if (isAdded) {
            btn.init("Убрать", BthCustom.TypeButton.SECONDARY);
        } else {
            btn.init("Добавить", BthCustom.TypeButton.PRIMAPRY);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvCategory;
        BthSmall bthSmall;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            bthSmall = itemView.findViewById(R.id.bthSmall);
        }
    }
}