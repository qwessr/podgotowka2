package com.example.uikit.edit_text.BottomSheet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uikit.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CustomBottomSheet {

    public static BottomSheetDialog Show(Context context, View custom_view, String tittle) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_default, null);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        ImageView bthClose = view.findViewById(R.id.bthClose);
        FrameLayout frame = view.findViewById(R.id.frame);

        bthClose.setOnClickListener(v -> dialog.dismiss());

        if (tittle == null || tittle.isEmpty()) {
            tvTitle.setVisibility(View.GONE);
            bthClose.setVisibility(View.GONE);
        } else {
            tvTitle.setText(tittle);
            tvTitle.setVisibility(View.VISIBLE);
            bthClose.setVisibility(View.VISIBLE);
        }

        if (custom_view.getParent() != null) {
            ((ViewGroup) custom_view.getParent()).removeView(custom_view);
        }
        frame.addView(custom_view);

        dialog.setContentView(view);

        try {
            View bottomSheet = (View) view.getParent();
            bottomSheet.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog.show();
        return dialog;
    }
}