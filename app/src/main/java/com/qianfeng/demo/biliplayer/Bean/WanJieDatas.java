package com.qianfeng.demo.biliplayer.Bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class WanJieDatas {

    /**
     * aid : 2696815
     * author : 搬
     * coins : 156
     * create : 2015-08-07 15:15
     * credit : 0
     * description : 【bilibili正版放送】电视动画《境界线上的地平线》改编自日本轻小说家川上稔原作的同名轻小说。2011年2月10日，电击文库正式宣布《境界线上的地平线》TV动画化决定。《境界线上的地平线》于2011年4月9日召开制作发表会。电视动画分为2期播出，第1期于2011年10月1日首播，而第2期《境界线上的地平线Ⅱ》则于2012年7月7日首播，各13集。
     * duration : 316:10
     * favorites : 3138
     * mid : 928123
     * pic : http://i0.hdslb.com/320_200/video/27/2744b0fcb787fcf01c1f9d39fbade0a2.jpg
     * play : 93019
     * pubdate : 1438931727
     * review : 386
     * subtitle :
     * title : 【合集】境界线上的地平线Ⅱ（第二季）【bilibili正版】
     * typeid : 32
     * video_review : 8806
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String author;
        private String create;
        private String description;
        private String duration;
        private String  favorites;
        private String pic;
        private String play;
        private String title;

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setCreate(String create) {
            this.create = create;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public void setFavorites(String favorites) {
            this.favorites = favorites;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public String getCreate() {
            return create;
        }

        public String getDescription() {
            return description;
        }

        public String getDuration() {
            return duration;
        }

        public String getFavorites() {
            return favorites;
        }

        public String getPic() {
            return pic;
        }

        public String getPlay() {
            return play;
        }

        public String getTitle() {
            return title;
        }
    }
}
