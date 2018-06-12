package com.lwlizhe.basemodule.event.message;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class BaseMessage<T> {

    // 事件类型（添加监听者、移除监听者、分发事件）
    public interface EventType {

        int EVENT_ACTIVITY = 0;
//        int EVENT_REMOVE_LISTENER = 1;
//        int EVENT_DISTRIBUTE = 2;

    }

    // 事件类型(EventType)
    private int tag;
    // 消息本身的tag
    private String messageTag;
    // 消息本身的内容
    private T messageData;

    public BaseMessage(int eventTag, String messageTag, T messageData) {

        this.tag = eventTag;
        this.messageTag = messageTag;
        this.messageData = messageData;

    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMessageTag() {
        return messageTag;
    }

    public void setMessageTag(String messageTag) {
        this.messageTag = messageTag;
    }

    public T getMessageData() {
        return messageData;
    }

    public void setMessageData(T messageData) {
        this.messageData = messageData;
    }
}
