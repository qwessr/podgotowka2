package com.example.uikit.edit_text.tabbar;

import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uikit.R;

import java.util.List;

import select.BottomSheetAdapter;

public class TabBarAdapter extends RecyclerView.Adapter<TabBarAdapter.ViewHolder> {

    List<TabBarItem> Items;

    Integer SelectPosition= 0;

    public OnTabClickListner Listner;

    public void setActive(int position) {
        if (SelectPosition == position) return;

        int oldPosition = SelectPosition;
        SelectPosition = position;

        notifyItemChanged(oldPosition);
        notifyItemChanged(SelectPosition);
    }

    public interface OnTabClickListner{
        void  OnTabClick(int position);
    }

    public TabBarAdapter(List<TabBarItem> items){
         Items = items;
    }

    @NonNull
    @Override
    public TabBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.tabbar_item,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabBarAdapter.ViewHolder holder, int position) {

        holder.Title.setText(Items.get(position).Title);
        holder.Image.setImageResource(Items.get(position).IdDrawable);

        Integer Selectcolor = position == SelectPosition ?
                Color.parseColor("#1a6fee") :
                Color.parseColor("#b8c1cc");
        holder.Title.setTextColor(Selectcolor);
        holder.Image.setColorFilter(Selectcolor);

        holder.Parent.setOnClickListener(view -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                Integer OldSelectPosition = SelectPosition;
                SelectPosition = currentPosition;

                notifyItemChanged(OldSelectPosition);
                notifyItemChanged(SelectPosition);

                if (Listner != null) Listner.OnTabClick(currentPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout Parent;
        ImageView Image;
        TextView Title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Image = itemView.findViewById(R.id.imageView);
            Title = itemView.findViewById(R.id.textView);
            Parent = (LinearLayout) Image.getParent();
        }



    }
}
