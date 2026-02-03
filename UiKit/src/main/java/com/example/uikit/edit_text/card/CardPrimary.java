package com.example.uikit.edit_text.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.uikit.R;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.Button.BthSmall;
import com.example.uikit.edit_text.common.Item;

public class CardPrimary extends CardCustom {
    public BthSmall BthSmall;
    TextView tvName, tvCategory, tvPrice;
    boolean Basket = false;

    public CardPrimary(@NonNull Context context) {
        super(context);
        init();
    }

    public CardPrimary(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardPrimary(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void init() {
        super.init();
    }

    public void init(Item item) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.card_primary, Frame, false);

        BthSmall = view.findViewById(R.id.bthSmall);
        tvName = view.findViewById(R.id.tvName);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvPrice = view.findViewById(R.id.tvPrice);


        BthSmall.init(0);
        BthSmall.init("Добавить", BthCustom.TypeButton.PRIMAPRY);

        tvName.setText(item.Name);
        tvCategory.setText(item.Category);
        tvPrice.setText(item.Price + " Р");

        BthSmall.Bth.setOnClickListener(v -> onBasket());

        Frame.addView(view);
    }

    public void onBasket() {
        Basket = !Basket;

        if (BthSmall != null && BthSmall.Bth != null) {
            if (Basket) {
                BthSmall.init(0);
                BthSmall.init("Убрать", BthCustom.TypeButton.SECONDARY);
                Frame.requestLayout();
            } else {
                BthSmall.init(0);
                BthSmall.init("Добавить", BthCustom.TypeButton.PRIMAPRY);
                Frame.requestLayout();
            }
        }
    }
}