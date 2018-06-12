package com.lwlizhe.novelvideoapp;

import android.app.Application;

import com.lwlizhe.basemodule.base.BaseApplication;
import com.lwlizhe.basemodule.di.module.EventModule;
import com.lwlizhe.basemodule.di.module.GlobeConfigModule;
import com.lwlizhe.basemodule.di.module.ImageModule;
import com.lwlizhe.basemodule.http.GlobeHttpHandler;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.common.di.component.DaggerAppComponent;
import com.lwlizhe.novelvideoapp.common.di.module.service.ServiceModule;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class AppApplication extends BaseApplication {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .globeConfigModule(getGlobeConfigModule())//全局配置
                .serviceModule(new ServiceModule())//需自行创建
                .imageModule(new ImageModule())
                .eventModule(new EventModule())
                .build();

        mActivityManager.setRxEventBus(mAppComponent.eventBus());

    }

    @Override
    protected GlobeConfigModule getGlobeConfigModule() {
        return GlobeConfigModule
                .buidler()
                .baseurl("https://api.github.com")
                .globeHttpHandler(new GlobeHttpHandler() {// 这里可以提供一个全局处理http响应结果的处理类,
                    // 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                        //这里可以先客户端一步拿到每一次http请求的结果,可以解析成json,做一些操作,如检测到token过期后
                        //重新请求token,并重新执行请求
//                        try {
//                            if (!TextUtils.isEmpty(httpResult)) {
//                                JSONArray array = new JSONArray(httpResult);
//                                JSONObject object = (JSONObject) array.get(0);
//                                String login = object.getString("login");
//                                String avatar_url = object.getString("avatar_url");
//                                Timber.tag(TAG).w("result ------>" + login + "    ||   avatar_url------>" + avatar_url);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            return response;
//                        }


                        //这里如果发现token过期,可以先请求最新的token,然后在拿新的token放入request里去重新请求
                        //注意在这个回调之前已经调用过proceed,所以这里必须自己去建立网络请求,如使用okhttp使用新的request去请求
                        // create a new request and modify it accordingly using the new token
//                    Request newRequest = chain.request().newBuilder().header("token", newToken)
//                            .build();

//                    // retry the request
//                    response.body().close();
                        //如果使用okhttp将新的请求,请求成功后,将返回的response  return出去即可

                        //如果不需要返回新的结果,则直接把response参数返回出去
                        return response;
                    }

                    // 这里可以在请求服务器之前可以拿到request,做一些操作比如给request统一添加token或者header
                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        //如果需要再请求服务器之前做一些操作,则重新返回一个做过操作的的requeat如增加header,不做操作则返回request

                        //return chain.request().newBuilder().header("token", tokenId)
//                .build();
                        return request;
                    }
                })
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
