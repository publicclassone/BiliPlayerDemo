package com.qianfeng.demo.biliplayer.Bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class LianZaiDatas {

    /**
     * aid : 3147973
     * author : 哔哩哔哩番剧
     * coins : 1115
     * create : 2015-10-31 19:56
     * credit : 0
     * description : #05 皇女的体验
     * duration : 24:00
     * favorites : 1341
     * mid : 928123
     * pic : http://i2.hdslb.com/320_200/video/5b/5b560f3f1e6411eedb77712624e9959b.jpg
     * play : 742798
     * pubdate : 1446292612
     * review : 3953
     * subtitle :
     * title : 【10月】落第骑士英雄谭 05【bilibili正版】
     * typeid : 33
     * video_review : 53486
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
        private String coins;
        private String create;
        private String description;
        private String duration;
        private String favorites;
        private String pic;
        private String play;
        private String title;

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setCoins(String coins) {
            this.coins = coins;
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

        public String getCoins() {
            return coins;
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
