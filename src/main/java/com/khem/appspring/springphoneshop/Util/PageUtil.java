package com.khem.appspring.springphoneshop.Util;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    int PAGE_SIZE_DEFAULT = 2;
    int PAGE_NUMBER_DEFAULT = 1;
    String PAGE_SIZE = "_limit";
    String PAGE_NUMBER = "_page";

    static Pageable getPageable(Map<String, String> param) {

        int pageSize = MapUtils.getInteger(param, PAGE_SIZE, PAGE_NUMBER_DEFAULT);
        int pageNumber = MapUtils.getInteger(param, PAGE_NUMBER, PAGE_SIZE_DEFAULT);
        if (pageNumber < 0) {
            pageNumber = PAGE_NUMBER_DEFAULT;
        }
        if (pageSize < 0) {
            pageSize = PAGE_SIZE_DEFAULT;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return pageable;
    };

}