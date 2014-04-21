
package com.example.thinkselectmode;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public abstract class SelectableItemViewWrapper<V extends View> extends LinearLayout {

    private static final int SELECT_VIEW_ID = 998568;

    private static final long SWITCH_IN_SELECTING_MODE_DURATION = 300;

    private static final long SWITCH_OUT_SELECTING_MODE_DURATION = 300;

    private V mContentView;

    private boolean mIsSelectingMode = false;

    public SelectableItemViewWrapper(Context aContext) {
        super(aContext);

        this.setGravity(Gravity.RIGHT);
        this.setOrientation(LinearLayout.HORIZONTAL);

        this.addView(this.getContentView(), this.createContentViewLP());

    }

    public V getContentView() {
        if (null == this.mContentView) {
            this.mContentView = this.createContentView();
        }
        return this.mContentView;
    }

    protected abstract V createContentView();

    protected abstract int getContentViewHeight();

    private LinearLayout.LayoutParams createContentViewLP() {
        LinearLayout.LayoutParams ret = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                this.getContentViewHeight());
        ret.gravity = Gravity.RIGHT;
        return ret;
    }

    public void switchSelectingMode() {
        this.setSelectingMode(!this.mIsSelectingMode);
    }

    public boolean getSelectingMode() {
        return this.mIsSelectingMode;
    }

    public void setSelectingMode(boolean aBool) {
        if (this.mIsSelectingMode == aBool) {
            return;
        }

        this.mIsSelectingMode = aBool;

        this.onSelectingModeChanged();
    }

    private static class CheckBox extends View {

        public CheckBox(Context aContext) {
            super(aContext);
        }

    }

    private void onSelectingModeChanged() {
        if (this.mIsSelectingMode) {
            final View selectView = new CheckBox(this.getContext());
            selectView.setId(SELECT_VIEW_ID);
            selectView.setVisibility(View.GONE);
            selectView.setBackgroundColor(Color.argb(200, 200, 100, 0));
            LinearLayout.LayoutParams selectViewLP = new LinearLayout.LayoutParams(
                    this.getSelectViewWidth(), LayoutParams.MATCH_PARENT);
            this.addView(selectView, 0, selectViewLP);

            final Animation selectViewAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    -1.0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f);
            selectViewAnim.setFillBefore(true);
            selectViewAnim.setFillAfter(true);
            selectViewAnim.setDuration(SWITCH_IN_SELECTING_MODE_DURATION);
            selectViewAnim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation aAnimation) {
                    selectView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation aAnimation) {
                }

                @Override
                public void onAnimationEnd(Animation aAnimation) {
                }
            });

            selectView.setAnimation(selectViewAnim);

            Animation itemViewAnim = new ResizeAnimation(this.getContentView().getWidth(), this
                    .getContentView().getWidth() - this.getSelectViewWidth(), this.getContentView());
            itemViewAnim.setDuration(SWITCH_IN_SELECTING_MODE_DURATION);
            this.getContentView().setAnimation(itemViewAnim);

            AnimationSet animationSet = new AnimationSet(false);
            animationSet.addAnimation(selectViewAnim);
            animationSet.addAnimation(itemViewAnim);

            animationSet.startNow();

        } else {

            Animation itemViewAnim = new ResizeAnimation(this.getContentView().getWidth(), this
                    .getContentView().getWidth() + this.getSelectViewWidth(), this.getContentView());
            itemViewAnim.setDuration(SWITCH_OUT_SELECTING_MODE_DURATION);
            itemViewAnim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation aAnimation) {
                }

                @Override
                public void onAnimationRepeat(Animation aAnimation) {
                }

                @Override
                public void onAnimationEnd(Animation aAnimation) {
                    getContentView().getHandler().post(new Runnable() {

                        @Override
                        public void run() {
                            final View selectView = SelectableItemViewWrapper.this
                                    .findViewById(SELECT_VIEW_ID);
                            SelectableItemViewWrapper.this.removeView(selectView);
                        }
                    });
                }
            });

            this.getContentView().startAnimation(itemViewAnim);

        }
    }

    private int getSelectViewWidth() {
        return 60;
    }

}
