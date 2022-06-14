package com.simple.app.refresh;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.simple.app.R;

/**
 * @author: yangkui
 * @Date: 2022/4/22
 * @Description:
 */
@SuppressLint("RestrictedApi")
public class NFooter extends LinearLayout implements RefreshFooter {
    private ObjectAnimator mAnimation;
    private TextView tvPull;
    private TextView tvNoMore;
    private ImageView ivPull;
    private String preText = "";
    private String ingText = "";
    private String endText = "";
    protected boolean mNoMoreData = false;
    protected int mFinishDuration = 500;

    public NFooter(Context context) {
        super(context);
        init(context);
    }

    public NFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NFooter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        preText = getResources().getString(R.string.refresh_pre);
        ingText = getResources().getString(R.string.refresh_ing);
        endText = getResources().getString(R.string.refresh_end);
        View view = LayoutInflater.from(context).inflate(R.layout.view_refresh_footer, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tvPull = view.findViewById(R.id.tv_pull);
        ivPull = view.findViewById(R.id.iv_pull);
        tvNoMore = view.findViewById(R.id.tv_no_more);
        addView(view);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (!mNoMoreData) {
            tvPull.setText(endText);
            ivPull.setVisibility(View.GONE);
            tvPull.setVisibility(View.GONE);
            tvNoMore.setVisibility(View.VISIBLE);
            return mFinishDuration;
        }
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (!mNoMoreData) {
            switch (newState) {
                case None:
                case PullUpToLoad:
                    tvPull.setText(preText);
                    ivPull.setVisibility(View.GONE);
                    tvPull.setVisibility(View.VISIBLE);
                    tvNoMore.setVisibility(View.GONE);
                    break;
                case Loading:
                    tvPull.setText(ingText);
                    ivPull.setVisibility(View.VISIBLE);
                    tvPull.setVisibility(View.GONE);
                    tvNoMore.setVisibility(View.GONE);
                    rotate();
                    break;
            }
        }
    }

    private void rotate() {
        if (mAnimation == null) {
            mAnimation = ObjectAnimator.ofFloat(ivPull, "rotation", 360f);
            mAnimation.setRepeatCount(ObjectAnimator.INFINITE);
            mAnimation.setRepeatMode(ValueAnimator.RESTART);
            mAnimation.setInterpolator(new LinearInterpolator());
            mAnimation.setDuration(800);
            mAnimation.start();
        }
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData;
            if (noMoreData) {
                ivPull.setVisibility(View.GONE);
                tvPull.setVisibility(View.GONE);
                tvNoMore.setVisibility(View.VISIBLE);
            } else {
                ivPull.setVisibility(View.GONE);
                tvPull.setVisibility(View.VISIBLE);
                tvNoMore.setVisibility(View.GONE);
            }
        }
        return true;
    }
}
