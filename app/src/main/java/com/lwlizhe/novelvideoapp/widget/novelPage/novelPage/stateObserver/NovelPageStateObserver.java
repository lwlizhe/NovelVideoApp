package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class NovelPageStateObserver {

    private static NovelPageStateObserver mInstance;

    private List<NovelPageStateListener> mObservers = new ArrayList<>();

    public static final String STATE_LOADING="state_loading";
    public static final String STATE_LOADING_FINISH="state_loading_finish";
    public static final String STATE_NORMAL="state_normal";
    public static final String STATE_NO_PRE="state_no_pre";
    public static final String STATE_NO_NEXT="state_no_next";
    public static final String STATE_REQUEST_CHAPTER="state_request_chapter";

    public static NovelPageStateObserver getInstance() {
        if (mInstance == null) {
            synchronized (NovelPageStateObserver.class) {
                if (mInstance == null) {
                    mInstance = new NovelPageStateObserver();
                }
            }
        }

        return mInstance;
    }

    public void addListener(NovelPageStateListener observer) {

        mObservers.add(observer);

    }
    public void removeListener(NovelPageStateListener observer) {

        mObservers.remove(observer);

    }

    public void setNovelPageState(String state,Object... args) {
        switch (state) {

            case STATE_REQUEST_CHAPTER:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onRequestNewChapter((long)args[0],(long)args[1]);
                }
                break;
            case STATE_NO_PRE:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onPreDisable();
                }
                break;
            case STATE_NO_NEXT:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onNextDisable();
                }
                break;

            case STATE_LOADING:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onLoading();
                }
                break;
            case STATE_LOADING_FINISH:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onLoadingFinish();
                }
                break;
            case STATE_NORMAL:
            default:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onNormal();
                }
                break;
        }
    }

    public void onDestroy(){
        if(mInstance!=null){
            mInstance=null;
        }
    }
}
