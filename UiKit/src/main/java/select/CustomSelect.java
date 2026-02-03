package select;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uikit.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CustomSelect extends ConstraintLayout {
    public TextView textView;

    TextView spinner;

    int SelectIndex;

    public CustomSelect(@NonNull Context context) {
        super(context);
        init();
    }
    public CustomSelect(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CustomSelect(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /// Инициализация компонента
    public void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.select_defualt,this,true);
        spinner = findViewById(R.id.spinner);
        textView= findViewById(R.id.textView);
    }

    public void init(String[] items, String title, String hint, Integer index) {
        if (title.isEmpty()) textView.setVisibility(View.GONE);
        else {
            textView.setText(title);
            textView.setVisibility(View.VISIBLE);
        }
        if(index == null)
        {
            spinner.setText(hint);
            spinner.setTextColor(Color.parseColor("#939396"));
        }
        else
        {
            spinner.setText(items[index]);
            spinner.setTextColor(Color.parseColor("#000000"));
        }
        spinner.setOnClickListener(v->ShowBottomSheet(items));
    }

     public void ShowBottomSheet(String[] items)
     {
         BottomSheetDialog Dialog = new BottomSheetDialog(this.getContext());

         if (Dialog.getWindow() != null) {
             Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         }
         View ViewSelectBottomSheet= LayoutInflater.from(this.getContext()).inflate
                 (R.layout.select_bottom_sheet,null);
         RecyclerView recyclerView=ViewSelectBottomSheet.findViewById(R.id.recyleView);
         recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

         BottomSheetAdapter Adapter = new BottomSheetAdapter(
                 items,
                 new BottomSheetAdapter.OnItemClickListener()
                 {
                     @Override
                     public void onItemClick(int position)
                     {
                         SelectIndex = position;

                         spinner.setText(items[position]);
                         spinner.setTextColor(Color.parseColor("#000000"));

                         Dialog.dismiss();
                     }
                 });
         recyclerView.setAdapter(Adapter);
         Dialog.setContentView(ViewSelectBottomSheet);
         Dialog.show();
     }

     public int getIndex()
     {
         return SelectIndex;
     }
}
