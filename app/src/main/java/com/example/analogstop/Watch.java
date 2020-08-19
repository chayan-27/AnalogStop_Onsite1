package com.example.analogstop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class Watch extends View {

    private Bitmap mDrawBitmap;

    private boolean mIsInit;
    private int mHeight;
    private int mWidth;
    private int mRadius;
    private int mCentreX;
    private int mCentreY;
    private int mPadding;
    private Paint mPaint;

    private int mMinimum;

    private int mHourHandSize;
    private int mHandSize;
    private double mAngle;


    float minute = 0f;
    float second = 0f;
    public static int delay = 100;
    public static boolean delaytruth = false;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public Watch(Context context) {
        super(context);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     *
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public Watch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute. This constructor of View allows subclasses to use their
     * own base style when they are inflating. For example, a Button class's
     * constructor would call this version of the super class constructor and
     * supply <code>R.attr.buttonStyle</code> for <var>defStyleAttr</var>; this
     * allows the theme's button style to modify all of the base view attributes
     * (in particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     */
    public Watch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute or style resource. This constructor of View allows
     * subclasses to use their own base style when they are inflating.
     * <p>
     * When determining the final value of a particular attribute, there are
     * four inputs that come into play:
     * <ol>
     * <li>Any attribute values in the given AttributeSet.
     * <li>The style resource specified in the AttributeSet (named "style").
     * <li>The default style specified by <var>defStyleAttr</var>.
     * <li>The default style specified by <var>defStyleRes</var>.
     * <li>The base values in this theme.
     * </ol>
     * <p>
     * Each of these inputs is considered in-order, with the first listed taking
     * precedence over the following ones. In other words, if in the
     * AttributeSet you have supplied <code>&lt;Button * textColor="#ff000000"&gt;</code>
     * , then the button's text will <em>always</em> be WHITE, regardless of
     * what is specified in any of the styles.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param defStyleRes  A resource identifier of a style resource that
     *                     supplies default values for the view, used only if
     *                     defStyleAttr is 0 or can not be found in the theme. Can be 0
     *                     to not look for defaults.
     */
    public Watch(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        if (!mIsInit) {
            init();
        }
       /* minute=minute+1f;
        second=second+1f;
*/
        drawCircle(canvas);
        drawCircle1(canvas);
        drawNumbersforseconds(canvas);
        drawNumbersforminutes(canvas);
        drawSecondHand(canvas, second);
        drawMinuteHand(canvas, minute);
        minute = minute + 0.1f;
        second = second + 0.1f;
        if (second == 60f) {
            second = 0f;
            // minute=minute+1f;
        }


        //canvas.drawBitmap(mDrawBitmap, 0, 0, mDrawPaint);
        if (delaytruth) {
            postInvalidateDelayed(delay);
        }


    }

    private void drawCircle(Canvas canvas) {
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 8);
        canvas.drawCircle(mCentreX, mCentreY, mRadius, mPaint);

    }

    private void drawCircle1(Canvas canvas) {
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 8);
        canvas.drawCircle(mCentreX, mCentreY, mRadius - 100, mPaint);

    }

    private void init() {

        mHeight = getHeight();
        mWidth = getWidth();
        mPadding = 20;
        mCentreX = mWidth / 2;
        mCentreY = mHeight / 2;
        mMinimum = Math.min(mHeight, mWidth);
        mRadius = mMinimum / 2 - mPadding;
        mAngle = (float) ((Math.PI / 30) - (Math.PI / 2));
        mPaint = new Paint();
        mHourHandSize = mRadius - mRadius / 16;
        mHandSize = mRadius - mRadius / 4;
        mIsInit = true;
    }

    private void setPaintAttributes(int colour, Paint.Style stroke, int strokeWidth) {
        mPaint.reset();
        mPaint.setColor(colour);
        mPaint.setStyle(stroke);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
    }

    private void drawMinuteHand(Canvas canvas, float location) {
        mPaint.reset();
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 8);
        mAngle = Math.PI * location / 1800 - Math.PI / 2;
        canvas.drawLine(mCentreX, mCentreY, (float) (mCentreX + Math.cos(mAngle) * mHandSize)
                , (float) (mCentreY + Math.sin(mAngle) * mHandSize), mPaint);
    }

    private void drawSecondHand(Canvas canvas, float location) {
        mPaint.reset();
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 8);
        mAngle = Math.PI * location / 30 - Math.PI / 2;
        canvas.drawLine(mCentreX, mCentreY, (float) (mCentreX + Math.cos(mAngle) * mHourHandSize)
                , (float) (mCentreY + Math.sin(mAngle) * mHourHandSize), mPaint);
    }

    public void drawNumbersforseconds(Canvas canvas) {
        mPaint.reset();
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 4);
        mPaint.setTextSize(30f);
        double angle;
        for (int i = 0; i <= 60; i += 1) {
            angle = Math.PI * i / 30 - Math.PI / 2;
            canvas.drawText(String.valueOf(i), (float) (mCentreX + Math.cos(angle) * (mHourHandSize - 10))
                    , (float) (mCentreY + Math.sin(angle) * (mHourHandSize - 10)), mPaint);
        }
    }

    public void drawNumbersforminutes(Canvas canvas) {
        mPaint.reset();
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 1);
        mPaint.setTextSize(20f);
        double angle;
        for (int i = 1; i <= 60; i++) {
            angle = Math.PI * i / 30 - Math.PI / 2;
            canvas.drawText(String.valueOf(i), (float) (mCentreX + Math.cos(angle) * (mHandSize - 10))
                    , (float) (mCentreY + Math.sin(angle) * (mHandSize - 10)), mPaint);
        }
    }

    public void starttimer(){
        delaytruth=true;
        invalidate();
    }

    public void resetTimer(){
        delaytruth=false;
        minute=0f;
        second=0f;
        invalidate();
    }

    public void pausetimer(){
        if(delaytruth){
            delaytruth=false;
        }else{
            delaytruth=true;
            invalidate();
        }
    }


}
