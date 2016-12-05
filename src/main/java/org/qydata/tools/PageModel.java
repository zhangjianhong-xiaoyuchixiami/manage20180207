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
    private Integer totalpage;//总页数
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
    public int getPageSize() {
        int _pageSize = STRAT_PAGESIZE;
        if (pageSize != null) {
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
    public int getLineSize() {
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
    public int getBeginIndex() {
        int _beginIndex = (getPageSize() - 1) * getLineSize();
        return _beginIndex;
    }

    public int getEndIndex() {

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
    public int getCount() {
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
    public int getTotalpage() {

        int _totalpage = 0;
        int count = getCount();
        int linesize = getLineSize();
        if (count % linesize == 0) {
            _totalpage = count / linesize;
        } else {
            _totalpage = count / linesize + 1;
        }
        return _totalpage;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

}
