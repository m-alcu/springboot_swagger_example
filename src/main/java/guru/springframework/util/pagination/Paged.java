package guru.springframework.util.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Instantiates a new paged.
 * 
 * @param <C>
 */
public class Paged<C> {

    /** The total size. */
    private Integer totalSize;

    /** The offset. */
    private Integer offset;

    /** The limit. */
    private Integer limit;

    /** The list. */
    private List<C> list = new ArrayList<>();

    /**
     * @return the totalSize
     */
    public Integer getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the list
     */
    public List<C> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<C> list) {
        this.list = list;
    }


}
