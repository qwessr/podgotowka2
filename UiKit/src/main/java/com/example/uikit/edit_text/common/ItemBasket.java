package com.example.uikit.edit_text.common;

public class ItemBasket extends Item{
    public Integer Count;
    public ItemBasket(String name, String category, Integer price, Integer count) {
        super(name, category, price);

        this.Count = count;
    }
}
