package com.lwlizhe.basemodule.event.message;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class ActivityMessage<T> extends BaseMessage<T>{


    public ActivityMessage(String messageTag, T messageData) {
        super(EventType.EVENT_ACTIVITY, messageTag, messageData);
    }




}
