package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class NovelPageStateObserver {

    private static NovelPageStateObserver mInstance;

    private List<NovelPageStateListener> mObservers = new ArrayList<>();

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

    public void setNovelPageState(int state,Object... args) {
        switch (state) {

            case 2:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onRequestNewChapter((long)args[0],(long)args[1]);
                }
                break;
            case -1:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onPreDisable();
                }
                break;
            case 1:
                for (NovelPageStateListener observer : mObservers) {
                    observer.onNextDisable();
                }
                break;
            case 0:
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
