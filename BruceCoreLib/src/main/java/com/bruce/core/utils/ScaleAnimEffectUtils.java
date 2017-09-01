package com.bruce.core.utils;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 放大动画
 *
 * @author joychang
 */
public class ScaleAnimEffectUtils {
    private long duration;
    private float fromAlpha;
    private float fromXScale;
    private float fromYScale;
    private float toAlpha;
    private float toXScale;
    private float toYScale;

    private static Animation bigAnimation, smallAnimation;

    public ScaleAnimEffectUtils (){

    }

    /**
     * 缩小
     * @param view
     */
    public static void smallAnimation(View view) {
        smallAnimation = new ScaleAnimEffectUtils().ScaleAnimation(1.15f, 1f, 1.15f, 1f);
        view.startAnimation(smallAnimation);
    }

    /**
     * 放大
     * @param view
     */
    public static void scaleAnimation(View view) {
        bigAnimation = new ScaleAnimEffectUtils().ScaleAnimation(1, 1.15f, 1, 1.15f);
        view.startAnimation(bigAnimation);
    }

    /**
     * 初始化数据
     *
     * @param paramFloat1
     * @param paramFloat2
     * @param paramLong1
     * @param paramLong2
     * @return
     */
    public Animation alphaAnimation(float paramFloat1, float paramFloat2,
                                    long paramLong1, long paramLong2) {
        AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramFloat1,
                paramFloat2);
        localAlphaAnimation.setDuration(paramLong1);//设置动画时间
        localAlphaAnimation.setStartOffset(paramLong2);//设置启动时间
        AccelerateInterpolator localAccelerateInterpolator = new AccelerateInterpolator();
        localAlphaAnimation.setInterpolator(localAccelerateInterpolator);
        return localAlphaAnimation;
    }

    /**
     * 平移
     *
     * @param fromXDelta
     * @param toXDelta
     * @param fromYDelta
     * @param toYDelta
     * @return
     */
    public Animation translAnimation(float fromXDelta, float toXDelta,
                                     float fromYDelta, float toYDelta) {
        TranslateAnimation localTranslAnimation = new TranslateAnimation(
                fromXDelta, toXDelta, fromYDelta, toYDelta);
        localTranslAnimation.setDuration(400L);
        localTranslAnimation.setFillAfter(true);
        localTranslAnimation.setInterpolator(new DecelerateInterpolator());//new DecelerateInterpolator(0.2f)减速AccelerateDecelerateInterpolator中间加速
        return localTranslAnimation;

    }

    /**
     * 放大动画
     *
     * @return
     */
    public AnimationSet ScaleAnimation(float fromXScale, float toXScale, float fromYScale, float toYScale) {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(fromXScale, toXScale, fromYScale, toYScale,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(localScaleAnimation);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        return animationSet;
    }

    /**
     * 创建一个动画，返回渐变尺寸伸缩动画对象
     *
     * @return
     */
    public Animation createAnimation() {
        float f1 = this.fromXScale;
        float f2 = this.toXScale;
        float f3 = this.fromYScale;
        float f4 = this.toYScale;
        int i = 1;
        float f5 = 0.5F;
        ScaleAnimation localScaleAnimation = new ScaleAnimation(f1, f2, f3, f4,
                1, 0.5F, i, f5);
        localScaleAnimation.setFillAfter(true);//设置动画后是否保存状态
        AccelerateInterpolator localAccelerateInterpolator = new AccelerateInterpolator();
        localScaleAnimation.setInterpolator(localAccelerateInterpolator);//设置动画启动后几秒开始
        long l = this.duration;
        localScaleAnimation.setDuration(l);
        return localScaleAnimation;
    }

    /**
     * 返回一个透明动画
     *
     * @return
     */
    public Animation createAlphaAnimation() {
        AlphaAnimation mAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        mAnimation.setFillAfter(true);
        mAnimation.setDuration(duration);
        return mAnimation;
    }

    /**
     * 设置横竖参数
     *
     * @param paramFloat1 从x
     * @param paramFloat2 到x
     * @param paramFloat3 从y
     * @param paramFloat4 到y
     * @param paramLong   时间
     */
    public void setAttributs(float paramFloat1, float paramFloat2,
                             float paramFloat3, float paramFloat4, long paramLong) {
        this.fromXScale = paramFloat1;
        this.fromYScale = paramFloat3;
        this.toXScale = paramFloat2;
        this.toYScale = paramFloat4;
        this.duration = paramLong;
    }

    /**
     * 设置透明参数
     *
     * @param fromAlpha 开始透明度
     * @param toAlpha   结果透明度
     * @param duration  时间
     */
    public void setAlphaAttributs(float fromAlpha, float toAlpha, long duration) {
        this.duration = duration;
        this.fromAlpha = fromAlpha;
        this.toAlpha = toAlpha;
    }
}