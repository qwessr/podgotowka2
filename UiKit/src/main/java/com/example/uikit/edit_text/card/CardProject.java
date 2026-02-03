package com.example.uikit.edit_text.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.Button.BthSmall;
import com.example.uikit.edit_text.common.Project;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CardProject  extends  CardCustom {

    TextView tvName, tvTime;

    public BthSmall bthSmall;
    public CardProject(@NonNull Context context) {
        super(context);
    }

    public CardProject(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardProject(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(){
        super.init();
    }

    public void init(Project project)
    {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.card_project, null);

        tvName = view.findViewById(R.id.tvName);
        tvTime = view.findViewById(R.id.tvTime);
        bthSmall = view.findViewById(R.id.bthSmall);

        tvName.setText(project.Name);

        Date now = new Date();
        long DiffInMillis = now.getTime() - project.DateStart.getTime();
        int Days = (int) TimeUnit.DAYS.convert(DiffInMillis, TimeUnit.MILLISECONDS);

        tvTime.setText("Прошло"+ Days+" дня");
        bthSmall.init("Открыть", BthCustom.TypeButton.PRIMAPRY);

        Frame.addView(view);
    }
}
