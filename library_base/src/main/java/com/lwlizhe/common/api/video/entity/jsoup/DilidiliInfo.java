package com.lwlizhe.common.api.video.entity.jsoup;


import com.fcannizzaro.jsoup.annotations.interfaces.Attr;
import com.fcannizzaro.jsoup.annotations.interfaces.Items;
import com.fcannizzaro.jsoup.annotations.interfaces.Selector;
import com.fcannizzaro.jsoup.annotations.interfaces.Text;
import com.lwlizhe.common.api.video.entity.BaseMultiItemData;

import java.util.List;

@Selector("main")
public class DilidiliInfo {

  public static final int TYPE_BANNER = 0;
  public static final int TYPE_WEEK = 1;
  public static final int TYPE_RECENT = 2;
  public static final int TYPE_RECOMMEND = 3;

  //本季新番
  /*@Text("div div div.mainMenu ul li:last-of-type a span")
  private String scheduleNewName;
  @Attr(flowableExec = "div div div.mainMenu ul li:last-of-type a", attr = "href")
  private String scheduleNewLink;*/
  @Items
  private List<ScheudleBanner> scheduleBanners; //轮播栏信息
  @Items
  private List<ScheduleRecommend> scheduleRecommends; //近期推荐
  @Items
  private List<ScheduleWeek> scheduleWeek;  //追番时间表
  @Items
  private List<ScheduleRecent> scheduleRecents;  //最近更新

  public List<ScheduleWeek> getScheduleWeek() {
    return scheduleWeek;
  }

  public void setScheduleWeek(List<ScheduleWeek> scheduleWeek) {
    this.scheduleWeek = scheduleWeek;
  }

  public List<ScheudleBanner> getScheduleBanners() {
    return scheduleBanners;
  }

  public void setScheduleBanners(
      List<ScheudleBanner> scheduleBanners) {
    this.scheduleBanners = scheduleBanners;
  }

  public List<ScheduleRecommend> getScheduleRecommends() {
    return scheduleRecommends;
  }

  public void setScheduleRecommends(
      List<ScheduleRecommend> ScheduleRecommends) {
    this.scheduleRecommends = ScheduleRecommends;
  }

  public List<ScheduleRecent> getScheduleRecents() {
    return scheduleRecents;
  }

  public void setScheduleRecents(
      List<ScheduleRecent> scheduleRecents) {
    this.scheduleRecents = scheduleRecents;
  }

  /*public String getScheduleNewName() {
    return scheduleNewName;
  }

  public void setScheduleNewName(String scheduleNewName) {
    this.scheduleNewName = scheduleNewName;
  }

  public String getScheduleNewLink() {
    return scheduleNewLink;
  }

  public void setScheduleNewLink(String scheduleNewLink) {
    this.scheduleNewLink = scheduleNewLink;
  }*/

  @Override
  public String toString() {
    return "DilidiliInfo{" +
        "scheduleBanners=" + scheduleBanners +
        ", ScheduleRecommends=" + scheduleRecommends +
        ", scheduleWeek=" + scheduleWeek +
        ", scheduleRecents=" + scheduleRecents +
        '}';
  }

  @Selector("div.swiper-wrapper a.swiper-slide")
  public static class ScheudleBanner extends BaseMultiItemData{

    @Attr(query = "img", attr = "src")
    private String imgUrl;
    @Text("div")
    private String name;
    @Attr(query = "a", attr = "href")
    private String animeLink;

    public String getImgUrl() {
      return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAnimeLink() {
      return animeLink;
    }

    public void setAnimeLink(String animeLink) {
      this.animeLink = animeLink;
    }

    @Override
    public String toString() {
      return "ScheudleBanner{" +
          "imgUrl='" + imgUrl + '\'' +
          ", name='" + name + '\'' +
          ", animeLink='" + animeLink + '\'' +
          '}';
    }

    @Override
    public int getType() {
      return TYPE_BANNER;
    }
  }

  @Selector("div.edit_list ul li")
  public static class ScheduleRecommend extends BaseMultiItemData{

    @Attr(query = "a div", attr = "style")
    private String imgUrl;
    @Text("a p")
    private String name;
    @Attr(query = "a", attr = "href")
    private String animeLink;

    public String getImgUrl() {
      try {
        return imgUrl.substring(imgUrl.lastIndexOf("(") + 1, imgUrl.lastIndexOf(".") + 4);
      } catch (Exception e) {
        e.printStackTrace();
        return "";
      }
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAnimeLink() {
      return animeLink;
    }

    public void setAnimeLink(String animeLink) {
      this.animeLink = animeLink;
    }

    @Override
    public String toString() {
      return "ScheduleRecommend{" +
          "imgUrl='" + imgUrl + '\'' +
          ", name='" + name + '\'' +
          ", animeLink='" + animeLink + '\'' +
          '}';
    }

    @Override
    public int getType() {
      return TYPE_RECOMMEND;
    }
  }

  @Selector("div#newId.new_update_list ul li")
  public static class ScheduleRecent extends BaseMultiItemData{

    @Attr(query = "a div", attr = "style")
    private String imgUrl;
    @Text("a h3")
    private String name;
    @Text("a h4")
    private String desc;
    @Attr(query = "a", attr = "href")
    private String animeLink;

    public String getImgUrl() {
      try {
        return imgUrl.substring(imgUrl.lastIndexOf("(") + 1, imgUrl.lastIndexOf(".") + 4);
      } catch (Exception e) {
        e.printStackTrace();
        return "";
      }
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public String getAnimeLink() {
      return animeLink;
    }

    public void setAnimeLink(String animeLink) {
      this.animeLink = animeLink;
    }


    @Override
    public String toString() {
      return "ScheduleRecent{" +
          "imgUrl='" + imgUrl + '\'' +
          ", name='" + name + '\'' +
          ", desc='" + desc + '\'' +
          ", animeLink='" + animeLink + '\'' +
          '}';
    }

    @Override
    public int getType() {
      return TYPE_RECENT;
    }
  }

//  public interface BaseMultiItemData{
//     public int getType();
//  }

}
