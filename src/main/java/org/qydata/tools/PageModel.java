package org.qydata.tools;

import java.util.List;

/**
 * Created by jonhn on 2016/11/10.
 */
public class PageModel<T> {

    private static final Integer MAX_LINESIZE = 20;
    private static final Integer STRAT_PAGESIZE = 1;
    private String pageSize;//当前页
    private String lineSize;//每页条数
    private Integer count;//总条数
    private Integer totlePage;//总页数
    private Integer beginIndex;//起始索引
    private Integer endIndex;//结束索引
    private List<T> list;//集合

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页数
     *
     * @return
     */
    public Integer getPageSize() {
        int _pageSize = STRAT_PAGESIZE;
        if (pageSize != null && Integer.parseInt(pageSize)>0) {
            try {
                _pageSize = Integer.parseInt(pageSize);
            } catch (Exception ex) {
            }
        }
        return _pageSize;
    }

    public void setLineSize(String lineSize) {
        this.lineSize = lineSize;
    }

    /**
     * 每页多少条
     *
     * @return
     */
    public Integer getLineSize() {
        int _lineSize = MAX_LINESIZE;
        if (lineSize != null) {
            try {
                _lineSize = Integer.parseInt(lineSize);
            } catch (Exception ex) {
            }

        }
        return _lineSize;
    }

    /**
     * 获取第一条数据在表中的索引
     *
     * @return
     */
    public Integer getBeginIndex() {
        int _beginIndex = (getPageSize() - 1) * getLineSize();
        return _beginIndex;
    }

    public Integer getEndIndex() {

        return getBeginIndex()+getLineSize();

    }

    public List<T> getList() {
        return list;
    }

    /**
     * 获取总条数
     *
     * @return
     */
    public Integer getCount() {
        int _count = 0;
        if (count != null) {
            try {
                _count = count;
            } catch (Exception ex) {
            }
        }
        return _count;
    }

    /**
     * 共多少页
     *
     * @return
     */
    public Integer getTotlePage() {

        Integer totalPage= null;
        Integer count = this.getCount();
        Integer lineSize = this.getLineSize();
        if (count%lineSize == 0) {
            totalPage = (count/lineSize);
        } else {
            totalPage = (count/lineSize) + 1;
        }
        return totalPage;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotlePage(Integer totlePage) {
        this.totlePage = totlePage;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
