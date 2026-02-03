package com.example.uikit.edit_text.common;

public class Item
{
    public String Name;

    public String Category;

    public Integer Price;
    public boolean isAdded;
    public Item(String name, String category, Integer price)
    {
        Name = name;
        Category = category;
        Price = price;
        this.isAdded = false;
    }

}
