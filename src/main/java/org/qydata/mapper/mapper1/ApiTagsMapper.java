package org.qydata.mapper.mapper1;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/9/14.
 */
public interface ApiTagsMapper {

    public Integer addApiTag(Map<String, Object> map);

    public Integer deleteApiTag(String id);
}
