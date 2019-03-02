package com.lwlizhe.video.api.entity.jsoup;

import com.fcannizzaro.jsoup.annotations.interfaces.Attr;
import com.fcannizzaro.jsoup.annotations.interfaces.Html;
import com.fcannizzaro.jsoup.annotations.interfaces.Selector;

/**
 * @author Rabtman
 */
@Selector("main div#app")
public class ScheduleVideo {

  @Html("div.video")
  private String videoHtml;
  @Attr(query = "div.video iframe", attr = "src")
  private String videoUrl;

  public String getVideoHtml() {
    return videoHtml;
  }

  public void setVideoHtml(String videoHtml) {
    this.videoHtml = videoHtml;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  @Override
  public String toString() {
    return "ScheduleVideo{" +
        "videoHtml='" + videoHtml + '\'' +
        ", videoUrl='" + videoUrl + '\'' +
        '}';
  }
}
