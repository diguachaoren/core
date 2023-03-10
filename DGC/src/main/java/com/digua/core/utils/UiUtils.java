package com.digua.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

/**
 * Created by Andrew on 5/30/16.
 */
public class UiUtils {

    /**
     * dp转px
     * @param dp
     * @param context
     * @return
     */
    public static float convertDpToPixel(float dp, Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 通过反射修改TabLayout Indicator的宽度
     */
    public static void setIndicatorWidth(final TabLayout tabLayout, int margin) {
        // 了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip属性
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    // 拿到tabView的mTextView属性，tab的字数不固定一定用反射取mTextView
                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                    mTextViewField.setAccessible(true);

                    TextView mTextView = (TextView) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    // 因为我想要的效果是，字多宽线就多宽，所以测量mTextView的宽度
                    int width = 0;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }

                    // 设置tab左右间距，注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = margin;
                    params.rightMargin = margin;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过反射修改TabLayout Indicator的宽度
     * 自定义TabView
     */
    public static void setCustomIndicatorWidth(final TabLayout tabLayout, int margin) {
        // 了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip属性
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    // 拿到tabView的mTextView属性，tab的字数不固定一定用反射取mTextView
                    Field mTextViewField = tabView.getClass().getDeclaredField("mCustomView");
                    mTextViewField.setAccessible(true);

                    View mCustomView = (View) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    // 因为我想要的效果是，字多宽线就多宽，所以测量mTextView的宽度
                    int width = 0;
                    width = mCustomView.getWidth();
                    if (width == 0) {
                        mCustomView.measure(0, 0);
                        width = mCustomView.getMeasuredWidth();
                    }

                    // 设置tab左右间距，注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = margin;
                    params.rightMargin = margin;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 参数：maxLines 要限制的最大行数
     * 参数：content  指TextView中要显示的内容
     */
    public static void setMaxEcplise(final TextView textView, final int maxLines, final String content) {
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.setText(content);
                if (textView.getLineCount() > maxLines) {
                    int lineEndIndex = textView.getLayout().getLineEnd(maxLines - 1);
                    //下面这句代码中：我在项目中用数字3发现效果不好，改成1了
                    String text = content.subSequence(0, lineEndIndex - 3) + "...";
                    textView.setText(text);
                } else {
                    removeGlobalOnLayoutListener(textView.getViewTreeObserver(), this);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi", "ObsoleteSdkInt"})
    private static void removeGlobalOnLayoutListener(ViewTreeObserver obs,
                                                     ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (obs == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16) {
            obs.removeGlobalOnLayoutListener(listener);
        } else {
            obs.removeOnGlobalLayoutListener(listener);
        }
    }



    /**
     * 同步加载图片
     */
    private static Bitmap asyncLoadImage(Context context, String imageUrl) {
        try {
            return Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        //获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        //计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        //取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        //得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }



}
