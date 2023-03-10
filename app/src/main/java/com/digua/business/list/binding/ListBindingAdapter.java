package com.digua.business.list.binding;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digua.business.list.adapter.ListAdapter;
import com.digua.business.list.viewmodel.ListViewModel;
import com.digua.core.utils.LogUtils;
import com.digua.repository.net.company.bean.GetAccountFlowListResponse;

import java.util.List;

public class ListBindingAdapter {


    @BindingAdapter("InitMyWebView")
    public static void initMyWebView(WebView webView, String weburl) {

        // 清除缓存
        webView.clearCache(true);

        // fix 图片不显示
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        // init webview settings
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        LogUtils.e("【WebUrl】：" + weburl);
//        webView.loadUrl(weburl);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                // 加载开始
            }



//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                LogUtils.e("【webview-url】" + url);
//                if (parseScheme(url)) {
//                    try {
//                        Uri uri = Uri.parse(url);
//                        Intent intent;
//                        intent = Intent.parseUri(url,
//                                Intent.URI_INTENT_SCHEME);
//                        intent.addCategory("android.intent.category.BROWSABLE");
//                        intent.setComponent(null);
//                        // intent.setSelector(null);
//                        webView.getContext().startActivity(intent);
//
//                    } catch (Exception e) {
//
//                    }
//                } else {
//                    view.loadUrl(url);
//                }
//
//                return true;
//
//            }
        });


    }



    public static boolean parseScheme(String url) {

//        if (url.contains("platformapi/startapp")) {
//
//            return true;
//        } else {
//
//            return false;
//        }
        return true;
    }


    @BindingAdapter(value = {"InitList", "InitListVM"})
    public static void InitList(RecyclerView recyclerView, List<GetAccountFlowListResponse.AccountFlow> list, ListViewModel vm) {
        Context context = recyclerView.getContext();
        ListAdapter adapter = new ListAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.newData(list);
    }




}
