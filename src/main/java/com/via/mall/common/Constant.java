package com.via.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Qingqing.He
 * @date 2020/12/29 16:19
 * 描述：常量值
 */
@Component
public class Constant {
    public static final String MALL_USER = "mall_user";
    public static final String SALT = "csfFEW,LPLE3;';]";

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    /**
     * 定义支持的排序
     */
    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc","price asc");
    }


    /**
     * 商品上下架状态
     */
    public interface SaleStatus{
        int NOT_SALE=0;
        int SALE=1;
    }

    /**
     * 商品选中状态
     */
    public interface Cart{
        int UN_CHECKED=0;
        int CHECKED=1;
    }
}
