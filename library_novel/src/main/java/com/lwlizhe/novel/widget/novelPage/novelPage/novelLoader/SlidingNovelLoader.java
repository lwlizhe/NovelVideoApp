package com.lwlizhe.novel.widget.novelPage.novelPage.novelLoader;

import android.view.MotionEvent;

import com.lwlizhe.novel.widget.novelPage.novelPage.NovelPage;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class SlidingNovelLoader extends BaseNovelLoader {

    private int mMoveX = 0;
    private int mMoveY = 0;

    public SlidingNovelLoader(NovelPage targetPageView) {
        super(targetPageView);
    }

    @Override
    protected void onPageTouch(MotionEvent event) {

        calTouchPoint(event);
//        mBitmapManager.onTouch(event);
        mPageAnimationBitmapLoader.onTouch(event);
    }

    private void calTouchPoint(MotionEvent event){

        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mMoveX = 0;
                mMoveY = 0;
                break;
            case MotionEvent.ACTION_MOVE:

                //移动刚开始，生成view
                if (mMoveX == 0 && mMoveY ==0) {
                    //判断翻得是上一页还是下一页
                    if (x - mStartX > 0){
                        mBitmapManager.drawPre();
                    }else{
                        mBitmapManager.drawNext();
                    }
                }

                mMoveX = x;
                mMoveY = y;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(!isMoving){
                    if (x < mPageWidth / 2){
                        mBitmapManager.drawPre();
                    }else{
                        mBitmapManager.drawNext();
                    }
                }


                break;
        }

    }

}
