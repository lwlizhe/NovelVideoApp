package com.lwlizhe.novelvideoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lwlizhe.novelvideoapp.novel.api.NovelNetService
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelCategoryEntity
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelReCommendEntity
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novel_main)


//        val retrofit = Retrofit.Builder().baseUrl(GlobeConstance.NOVEL_BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
////                .addConverterFactory(ScalarsConverterFactory.create())//使用Scalars
//                .addConverterFactory(GsonConverterFactory.create())//使用Gson
//                .build()
//        val service = retrofit.create(TestService::class.java)
//
//        service.novelReCommend.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<String> {
//            override fun onSubscribe(s: Subscription) {
//                s.request(java.lang.Long.MAX_VALUE)
//            }
//
//            override fun onNext(NovelReCommendEntitys: List<NovelReCommendEntity>) {
//                Logger.d(NovelReCommendEntitys.size)
//            }
//
//            override fun onError(t: Throwable) {
//                Logger.e(t.toString())
//            }
//
//            override fun onComplete() {
//                Logger.d("finish")
//            }
//        })


    }
}
