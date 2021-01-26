package com.via.mall.controller;

import com.github.pagehelper.PageInfo;
import com.via.mall.common.ApiRestResponse;
import com.via.mall.common.Constant;
import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.request.AddProductReq;
import com.via.mall.request.UpdateProductReq;
import com.via.mall.service.ImoocMallProductService;
import com.via.mall.vo.ImoocMallCategoryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

/**
 * @author Qingqing.He
 * @date 2021/1/7 15:28
 * 描述：后台商品管理
 */
@RestController
@RequestMapping("/product")
public class ProductAdminController {

    @Resource
    ImoocMallProductService productService;

    @PostMapping("/admin/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq){

        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    /**
     * 后台更新商品
     * @param updateProductReq
     * @return
     */
    @PostMapping("/admin/update")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq){

        ImoocMallProduct product = new ImoocMallProduct();
        BeanUtils.copyProperties(updateProductReq,product);
        productService.update(product);
        return ApiRestResponse.success();
    }

    /**
     * 后台商品批量上下架
     * @param ids
     * @param sellStatus
     * @return
     */
    @PostMapping("/admin/batchUpdateSellStatus")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids,
                                         @RequestParam Integer sellStatus){

       productService.batchUpdateSellStatus(ids,sellStatus);
        return ApiRestResponse.success();
    }

    /**
     * 后台商品删除
     * @param productId
     * @return
     */
    @PostMapping("/admin/delete")
    public ApiRestResponse deleteProduct(@RequestParam("productId") Integer productId){

        productService.deleteByPrimaryKey(productId);
        return ApiRestResponse.success();
    }

    /**
     * 后台商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("后台商品列表")
    @PostMapping("/admin/list")
    public ApiRestResponse listProductForAdmin(@RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        PageInfo pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    /**
     * 图片上传
     * @param servletRequest
     * @param file
     * @return
     */
    @PostMapping("/admin/upload")
    public ApiRestResponse upload(HttpServletRequest servletRequest, @RequestParam("file")MultipartFile file){
        //获取原始名字
        String fileName = file.getOriginalFilename();
        //截取原名后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名uuid
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        //生成目标文件
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()){
                throw new MallException(MallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            //将传进来的file写入空的目标文件中
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse.success(getHost(new URI(servletRequest.getRequestURI()+""))+
                                               "/images/"+newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(MallExceptionEnum.UPLOAD_FAILED);
        }
    }

    /**
     * 获取返回的路径地址
     * @param uri
     * @return
     */
    public URI getHost(URI uri){
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(),uri.getUserInfo(),uri.getHost(),
                                   uri.getPort(),null,null,null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }


}
