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

import com.scwang.smart.refresh.layout.api.RefreshHeader;
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
public class NHeader extends LinearLayout implements RefreshHeader {
    private ObjectAnimator mAnimation;
    private TextView tvPull;
    private ImageView ivPull;
    private String preText = "";
    private String ingText = "";
    private String endText = "";

    public NHeader(Context context) {
        super(context);
        init(context);
    }

    public NHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        preText = getResources().getString(R.string.refresh_pre);
        ingText = getResources().getString(R.string.refresh_ing);
        endText = getResources().getString(R.string.refresh_end);
        View view = LayoutInflater.from(context).inflate(R.layout.view_refresh_header, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tvPull = view.findViewById(R.id.tv_pull);
        ivPull = view.findViewById(R.id.iv_pull);
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
        tvPull.setText(endText);
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
        switch (newState) {
            case None:
            case PullDownToRefresh:
            case ReleaseToRefresh:
            case RefreshReleased:
                tvPull.setText(preText);
                ivPull.setVisibility(View.GONE);
                break;
            case Refreshing:
                tvPull.setText(ingText);
                ivPull.setVisibility(View.VISIBLE);
                rotate();
                break;
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
}
