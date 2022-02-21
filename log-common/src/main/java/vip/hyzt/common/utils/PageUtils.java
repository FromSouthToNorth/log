package vip.hyzt.common.utils;

import com.github.pagehelper.PageHelper;
import vip.hyzt.common.core.page.PageDomain;
import vip.hyzt.common.core.page.TableSupport;
import vip.hyzt.common.utils.sql.SqlUtil;

/**
 * 表格数据处理
 * @author hy
 */
public abstract class PageUtils extends PageHelper {

    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }
}
