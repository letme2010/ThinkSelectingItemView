
package com.example.thinkselectmode;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DemoContentView extends FrameLayout {

    public DemoContentView(Context aContext) {
        super(aContext);
        setBackgroundColor(Color.argb(20, 0, 0, 250));

        {
            ImageView icon = new ImageView(this.getContext());
            icon.setImageResource(R.drawable.ic_launcher);
            icon.setBackgroundColor(Color.RED);
            FrameLayout.LayoutParams iconLP = new FrameLayout.LayoutParams(60, 60);
            iconLP.gravity = Gravity.LEFT;
            this.addView(icon, iconLP);
        }

        {
            ImageView icon = new ImageView(this.getContext());
            icon.setImageResource(R.drawable.ic_launcher);
            icon.setBackgroundColor(Color.GREEN);
            FrameLayout.LayoutParams iconLP = new FrameLayout.LayoutParams(60, 60);
            iconLP.gravity = Gravity.RIGHT;
            this.addView(icon, iconLP);
        }

        {
            FrameLayout textViewContain = new FrameLayout(this.getContext());
            textViewContain.setPadding(60, 0, 60, 0);

            TextView textView = new TextView(this.getContext());
            textView.setText("http://baidu.com#as;dlfke;lkas;dlfas;dfja;sldjfasd;fasdfefasdfasdf");
            textView.setEllipsize(TruncateAt.END);
            textView.setSingleLine();
            textView.setGravity(Gravity.CENTER_VERTICAL);

            FrameLayout.LayoutParams textViewLP = new FrameLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            textViewContain.addView(textView, textViewLP);

            FrameLayout.LayoutParams textViewContainLP = new FrameLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            this.addView(textViewContain, textViewContainLP);
        }

        LinearLayout.LayoutParams thisLP = new LinearLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT);
        thisLP.gravity = Gravity.RIGHT;

    }

}
