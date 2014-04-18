
package com.example.thinkselectmode;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contain = new FrameLayout(this);
        contain.setBackgroundColor(Color.GRAY);

        final DemoSelectableItemVeiw itemView = new DemoSelectableItemVeiw(this);
        contain.addView(itemView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        final Button button = new Button(this);
        button.setText("SWITCH");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View aV) {
                itemView.switchSelectingMode();
            }
        });

        FrameLayout.LayoutParams buttonLP = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        buttonLP.gravity = Gravity.CENTER;
        contain.addView(button, buttonLP);

        this.setContentView(contain);

    }
}
