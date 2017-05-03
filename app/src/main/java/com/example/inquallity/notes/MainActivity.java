package com.example.inquallity.notes;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Inquallity on 03-May-17.
 */

public class MainActivity extends Activity {

    public void onClick(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.startAnimation(new ViewAnimation());
    }

    public class ViewAnimation extends Animation {
        int centerX, centerY;

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(5000);
            setFillAfter(true);
            setInterpolator(new LinearInterpolator());
            centerX = width/2;
            centerY = height/2;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final Matrix matrix = t.getMatrix();
            matrix.setScale(interpolatedTime, interpolatedTime);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        Button bAddNewNote = (Button) findViewById(R.id.bAddNewNote);
        bAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
