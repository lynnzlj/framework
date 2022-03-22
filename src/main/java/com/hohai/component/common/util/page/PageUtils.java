package com.hohai.component.common.util.page;

import com.github.pagehelper.PageHelper;
import com.hohai.component.common.util.SqlUtils;
import com.hohai.component.common.util.StringUtils;

/**
 * @author Lynn
 * @date 2022/3/17 14:42
 * @description 分页工具类
 **/


public class PageUtils extends PageHelper{

    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
