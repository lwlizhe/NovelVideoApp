package com.lwlizhe.novel.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.basemodule.event.message.ActivityMessage;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.utils.CustomDateUtils;
import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.GlobeConstance;


import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.common.widget.ExpandTextView;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novel.api.entity.NovelDetailEntity;
import com.lwlizhe.novel.base.CommonActivity;
import com.lwlizhe.novel.di.component.DaggerNovelChapterComponent;
import com.lwlizhe.novel.di.module.NovelChapterModule;
import com.lwlizhe.novel.mvp.contract.activity.NovelChapterContract;
import com.lwlizhe.novel.mvp.presenter.activity.NovelChapterPresenter;
import com.lwlizhe.novel.mvp.ui.fragment.NovelDetailChapterFragment;


import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.lwlizhe.novel.mvp.ui.activity.NovelReadActivity.NOVEL_CHAPTER_LIST;
import static com.lwlizhe.novel.mvp.ui.activity.NovelReadActivity.NOVEL_ID;
import static com.lwlizhe.novel.mvp.ui.activity.NovelReadActivity.NOVEL_QUICK_START;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class NovelChapterActivity extends CommonActivity<NovelChapterPresenter> implements NovelChapterContract.View {

    public static final String NOVEL_DATA_ITEM_OBJ_ID="novel_data_item_obj_id";

    private TextView mTvwNovelName;
    private TextView mTvwNovelAuthor;
    private TextView mTvwNovelSubscribeCount;
    private TextView mTvwHitCount;
    private TextView mTvwLastUpdateTime;
    private TextView mTvwQuickRead;

    private RelativeLayout mRltHeader;

    private ImageView mIvwAvatar;

    private AppBarLayout mAppBarLayout;

    private ExpandTextView mTvwInstruction;

    private FloatingActionButton mFabRead;

    private ViewPager mViewPager;

    private NovelDetailChapterFragment mChapterFragment;

    private long novelId;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerNovelChapterComponent.builder().appComponent(appComponent).novelChapterModule(new NovelChapterModule(this)).build().inject(this);
    }


    @Override
    protected View initRootView() {
        return LayoutInflater.from(NovelChapterActivity.this).inflate(R.layout.activity_novel_chapter, null, false);
    }

    @Override
    protected void initView() {

        mRltHeader = findViewById(R.id.llt_head_layout);

        mTvwQuickRead=findViewById(R.id.tvw_quickRead);

        mTvwNovelName=findViewById(R.id.tvw_novel_name);
        mTvwNovelAuthor=findViewById(R.id.tvw_novel_author);
        mTvwNovelSubscribeCount=findViewById(R.id.tvw_novel_subscribe_count);
        mTvwHitCount=findViewById(R.id.tvw_hit_count);
        mTvwLastUpdateTime=findViewById(R.id.tvw_last_update_time);

        mAppBarLayout=findViewById(R.id.app_bar_layout);
        mTvwInstruction=findViewById(R.id.tvw_instruction);

        mFabRead=findViewById(R.id.fab);

        mViewPager=findViewById(R.id.viewPager);

        mIvwAvatar=findViewById(R.id.ivw_novel_avatar);

    }

    @Override
    protected void initData() {
        novelId = getIntent().getIntExtra(NOVEL_DATA_ITEM_OBJ_ID, 0);
        mPresenter.initData(novelId);
    }

    @Override
    public void setData(NovelDetailEntity data) {

        mTvwInstruction.setMinVisibleLines(2);
        mTvwInstruction.setContent(data.getIntroduction());

        mImageLoader.loadImage(this,GlideImageConfig
                .builder()
                .url(data.getCover())
                .refererUrl(GlobeConstance.CONSTANCE_URL.DMZJ_IMG_REFERER_URL)
                .imageView(mIvwAvatar)
                .build());

        mImageLoader.loadImage(this,GlideImageConfig
                .builder()
                .url(data.getCover())
                .refererUrl(GlobeConstance.CONSTANCE_URL.DMZJ_IMG_REFERER_URL)
                .transformation(RequestOptions.bitmapTransform(new BlurTransformation(10)))
                .target(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mRltHeader.setBackground(resource);
                    }
                })
                .build());

        mIvwAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("click");
            }
        });

        mTvwNovelName.setText(data.getName());
        mTvwNovelAuthor.setText(data.getAuthors());
        mTvwNovelSubscribeCount.setText(String.valueOf(data.getSubscribe_num()));
        mTvwHitCount.setText(String.valueOf(data.getHot_hits()));
        mTvwLastUpdateTime.setText(CustomDateUtils.transformTimeStampToData(data.getLast_update_time()));

        List<Fragment> mFragmentList=new ArrayList<>();

        mChapterFragment = new NovelDetailChapterFragment();
        mChapterFragment.setNovelId(novelId);

        mFragmentList.add(mChapterFragment);

        BaseFragmentPagerAdapter mFragmentAdapter=new BaseFragmentPagerAdapter(getSupportFragmentManager());
        mFragmentAdapter.addData(mFragmentList);

        mViewPager.setAdapter(mFragmentAdapter);

    }

    @Override
    protected void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()- UiUtils.dp2px(50)) {

                    mTvwQuickRead.setVisibility(View.VISIBLE);

                } else {

                    mTvwQuickRead.setVisibility(View.GONE);

                }
            }
        });
        mFabRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<NovelChapterEntity> chapterList = mChapterFragment.getChapterList();

                if(chapterList==null||chapterList.size()==0){
                    Toast.makeText(NovelChapterActivity.this,"章节未加载完成,请稍后再试",Toast.LENGTH_SHORT).show();
                    return;
                }

//                Intent intent=new Intent(NovelChapterActivity.this,NovelReadActivity.class);
//                intent.putExtra(NOVEL_ID,novelId);
//                intent.putExtra(NOVEL_QUICK_START,true);
//                intent.putExtra(NOVEL_CHAPTER_LIST, (Serializable) chapterList);

                Bundle params = new Bundle();
                params.putLong(NOVEL_ID,novelId);
                params.putBoolean(NOVEL_QUICK_START,true);
                params.putSerializable(NOVEL_CHAPTER_LIST, (Serializable) chapterList);

                ARouter.getInstance().build(GlobeConstance.CONSTANCE_ROUTER.ROUTER_NOVEL_READ).with(params).navigation();

//                launchActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

        mAppComponent.eventBus().post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT,intent));

    }

    @Override
    public void killMyself() {

    }

    @Override
    public AppCompatActivity getContext() {
        return this;
    }


}
