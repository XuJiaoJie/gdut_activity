package com.rdc.gdut_activity.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求工具类, 封装了OkHttp3
 */

public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";
    private static OkHttpUtil sOkHttpUtil;
    private OkHttpClient.Builder mOkHttpClientBuilder;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    private OkHttpUtil(){
        mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new OkHttpCookieJar());
        mOkHttpClient = mOkHttpClientBuilder.build();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtil getInstance(){
        if (sOkHttpUtil == null){
            synchronized (OkHttpUtil.class){
                if (sOkHttpUtil == null){
                    sOkHttpUtil = new OkHttpUtil();
                }
            }
        }
        return sOkHttpUtil;
    }

    /**
     * 异步的Get请求
     */
    public void getAsync(String url, OkHttpResultCallback okHttpResultCallback){
        Request request = new Request.Builder().url(url).build();
        deliveryResult(okHttpResultCallback,request);
    }

    /**
     * 异步的post请求
     */
    public void postAsync(String url, OkHttpResultCallback okHttpResultCallback, Map<String,String> params, Map<String,String> headers){
        Request request = buildPostRequest(url,params,headers);
        deliveryResult(okHttpResultCallback,request);
    }

    /**
     * 构建post请求参数
     */
    private Request buildPostRequest(String url,Map<String,String> params,Map<String,String> headers){
        Headers.Builder headersBuilder = new Headers.Builder();
        Set<Map.Entry<String,String>> headersEntries = headers.entrySet();
        for (Map.Entry<String,String> entry : headersEntries){
            headersBuilder.add(entry.getKey(),entry.getValue());
        }
        Headers requestHeaders = headersBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Set<Map.Entry<String,String>> paramsEntries = params.entrySet();
        for (Map.Entry<String,String> entry : paramsEntries){
            formBodyBuilder.add(entry.getKey(),entry.getValue());
        }
        RequestBody requestBody = formBodyBuilder.build();

        return new Request.Builder()
                .url(url)
                .headers(requestHeaders)
                .post(requestBody)
                .build();
    }


    /**
     * 调用call.enqueue，将call加入调度队列，执行完成后在callback中得到结果
     */
    private void deliveryResult(final OkHttpResultCallback okHttpResultCallback, final Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call,e, okHttpResultCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    switch (response.code()){
                        case 200:
                            Log.e(TAG, "onResponse: "+200 );
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            break;
                        case 302:
                            Log.e(TAG, "onResponse: "+302);
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            break;
                        default:
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            throw new IOException();
                    }
                }catch (IOException e){
                    sendFailedCallback(call,e, okHttpResultCallback);
                }
            }
        });
    }


    /**
     * 调用请求失败对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendFailedCallback(final Call call, final Exception e, final OkHttpResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onError(call,e);
                }
            }
        });
    }
    /**
     * 调用请求成功对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendSuccessCallback(final byte[] bytes, final OkHttpResultCallback okHttpResultCallback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (okHttpResultCallback != null){
                    okHttpResultCallback.onResponse(bytes);
                }
            }
        });
    }

}
