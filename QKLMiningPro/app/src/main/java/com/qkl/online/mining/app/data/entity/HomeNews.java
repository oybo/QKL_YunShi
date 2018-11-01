package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/8/27 13:35
 */
public class HomeNews extends BaseBean {


    /**
     * totalCount : 0
     * pageSize : 1
     * totalPage : 0
     * currPage : 1
     * list : [{"articleId":1,"groupId":1001,"groupName":"新闻公告","title":"系统开启注册公告","imgUrl":"https://mmbiz.qpic.cn/mmbiz_jpg/oq1PymRl9D6bL81E4tSj2ia2ZpK3QVcuqACWvCOTo0nYLia4Ode83n4fDSsQNVqgibbaPcMK36E349ibibu0JAE0Tsw/640?tp=webp&wxfrom=5&wx_lazy=1","summary":"系统开启注册","content":"处暑无三日，新凉直万金。白头更世事，青草印禅心","author":"管理员","postTime":1535362411,"sort":1,"addTime":1535362411,"updateTime":1535362411}]
     */

    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<ListEntity> list;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        /**
         * articleId : 1
         * groupId : 1001
         * groupName : 新闻公告
         * title : 系统开启注册公告
         * imgUrl : https://mmbiz.qpic.cn/mmbiz_jpg/oq1PymRl9D6bL81E4tSj2ia2ZpK3QVcuqACWvCOTo0nYLia4Ode83n4fDSsQNVqgibbaPcMK36E349ibibu0JAE0Tsw/640?tp=webp&wxfrom=5&wx_lazy=1
         * summary : 系统开启注册
         * content : 处暑无三日，新凉直万金。白头更世事，青草印禅心
         * author : 管理员
         * postTime : 1535362411
         * sort : 1
         * addTime : 1535362411
         * updateTime : 1535362411
         */

        private int articleId;
        private int groupId;
        private String groupName;
        private String title;
        private String imgUrl;
        private String summary;
        private String content;
        private String author;
        private int postTime;
        private int sort;
        private int addTime;
        private int updateTime;
        private String url;

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setPostTime(int postTime) {
            this.postTime = postTime;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getArticleId() {
            return articleId;
        }

        public int getGroupId() {
            return groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public String getTitle() {
            return title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getSummary() {
            return summary;
        }

        public String getContent() {
            return content;
        }

        public String getAuthor() {
            return author;
        }

        public int getPostTime() {
            return postTime;
        }

        public int getSort() {
            return sort;
        }

        public int getAddTime() {
            return addTime;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
