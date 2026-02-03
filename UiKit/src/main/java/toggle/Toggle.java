package toggle;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.example.uikit.R;

public class Toggle extends FrameLayout {
    private boolean isChecked = false;
    private View thumb;
    private float maxTranslationX = 0f;
    private boolean isInitialized = false;
    private Drawable bgOn;
    private Drawable bgOff;

    public Toggle(Context context) {
        super(context);
        init();
    }

    public Toggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bgOn = getResources().getDrawable(R.drawable.toggle_bg_on, null);
        bgOff = getResources().getDrawable(R.drawable.toggle_bg_off, null);

        LayoutInflater.from(getContext()).inflate(R.layout.ui_toggle, this, true);
        thumb = findViewById(R.id.thumb);

        setBackground(bgOff);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isInitialized && getWidth() > 0 && thumb != null && thumb.getWidth() > 0) {
                    int padding = getPaddingStart() + getPaddingEnd();
                    maxTranslationX = getWidth() - thumb.getWidth() - padding;
                    isInitialized = true;
                    applyThumbPosition();

                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        setOnClickListener(v -> toggle());
    }

    private void toggle() {
        isChecked = !isChecked;
        applyState();
    }

    private void applyState() {
        setBackground(isChecked ? bgOn : bgOff);

        if (isInitialized) {
            applyThumbPosition();
        }
    }

    private void applyThumbPosition() {
        if (thumb == null) return;

        float targetX = isChecked ? maxTranslationX : 0f;
        thumb.animate()
                .translationX(targetX)
                .setDuration(150)
                .start();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        if (this.isChecked == checked) return;
        this.isChecked = checked;
        applyState();
    }
}