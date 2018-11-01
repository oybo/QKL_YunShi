package com.qkl.online.mining.app.data.entity;

import java.util.List;

/**
 * author：oyb on 2018/9/4 04:09
 */
public class BannerBean extends BaseBean {

    /**
     * totalCount : 0
     * pageSize : 2
     * totalPage : 0
     * currPage : 1
     * list : [{"id":1,"image":"https://mmbiz.qpic.cn/mmbiz_jpg/g6EIJ2D8r6W3Bhc6wvXl8XLAyBtGul0NTxuR1JicRWkDwbD7IuVhRU7emH1RaicRO4ujDey34wJUfNk71V9U4xibQ/640?tp=webp&wxfrom=5&wx_lazy=1","title":"标题1","url":"https://mmbiz.qpic.cn/mmbiz_jpg/g6EIJ2D8r6W3Bhc6wvXl8XLAyBtGul0NTxuR1JicRWkDwbD7IuVhRU7emH1RaicRO4ujDey34wJUfNk71V9U4xibQ/640?tp=webp&wxfrom=5&wx_lazy=1","sort":1},{"id":2,"image":"https://mmbiz.qpic.cn/mmbiz_jpg/oq1PymRl9D6bL81E4tSj2ia2ZpK3QVcuqqw4zInsXxVencnd6n7f3NjhZ9cfwpONAnHPGKR54C9C20UavvPevuA/640?tp=webp&wxfrom=5&wx_lazy=1","title":"标题2","url":"https://mmbiz.qpic.cn/mmbiz_jpg/oq1PymRl9D6bL81E4tSj2ia2ZpK3QVcuqqw4zInsXxVencnd6n7f3NjhZ9cfwpONAnHPGKR54C9C20UavvPevuA/640?tp=webp&wxfrom=5&wx_lazy=1","sort":2}]
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
         * id : 1
         * image : https://mmbiz.qpic.cn/mmbiz_jpg/g6EIJ2D8r6W3Bhc6wvXl8XLAyBtGul0NTxuR1JicRWkDwbD7IuVhRU7emH1RaicRO4ujDey34wJUfNk71V9U4xibQ/640?tp=webp&wxfrom=5&wx_lazy=1
         * title : 标题1
         * url : https://mmbiz.qpic.cn/mmbiz_jpg/g6EIJ2D8r6W3Bhc6wvXl8XLAyBtGul0NTxuR1JicRWkDwbD7IuVhRU7emH1RaicRO4ujDey34wJUfNk71V9U4xibQ/640?tp=webp&wxfrom=5&wx_lazy=1
         * sort : 1
         */

        private int id;
        private String image;
        private String title;
        private String url;
        private int sort;

        public void setId(int id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public int getSort() {
            return sort;
        }
    }
}
