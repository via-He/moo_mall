package com.via.mall.controller;

import com.github.pagehelper.PageInfo;
import com.via.mall.common.ApiRestResponse;
import com.via.mall.common.Constant;
import com.via.mall.domain.ImoocMallCategory;
import com.via.mall.domain.ImoocMallUser;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.request.AddCategoryReq;
import com.via.mall.request.UpdateCategoryReq;
import com.via.mall.service.ImoocMallCategoryService;
import com.via.mall.service.ImoocMallUserService;
import com.via.mall.vo.ImoocMallCategoryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Qingqing.He
 * @date 2020/12/30 14:34
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    ImoocMallUserService userService;

    @Resource
    ImoocMallCategoryService categoryService;

    /**
     * 添加商品目录
     * @param session
     * @param addCategoryReq
     * @return
     */
    @ApiOperation("添加商品目录")
    @PostMapping("/admin/add")
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq){
        if(addCategoryReq.getName() == null || addCategoryReq.getType() == null ||
            addCategoryReq.getOrderNum() == null || addCategoryReq.getParentId() == null){
            return ApiRestResponse.error(MallExceptionEnum.PARAM_NOT_NULL);
        }

        ImoocMallUser currentUser = (ImoocMallUser) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        //是管理员，执行操作
        if (adminRole){
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }


    @ApiOperation("后台更新商品目录")
    @PostMapping("/admin/update")
    public ApiRestResponse updateCategory(HttpSession session,
                                          @Valid @RequestBody UpdateCategoryReq updateCategoryReq){
        if( updateCategoryReq.getId() == null){
            return ApiRestResponse.error(MallExceptionEnum.PARAM_NOT_NULL);
        }

        ImoocMallUser currentUser = (ImoocMallUser) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        //是管理员，执行操作
        if (adminRole){
            ImoocMallCategory category = new ImoocMallCategory();
            BeanUtils.copyProperties(updateCategoryReq,category);
            categoryService.update(category);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台删除商品目录")
    @PostMapping("/admin/delete")
    public ApiRestResponse deleteCategory(@RequestParam Integer id){
        categoryService.deleteByPrimaryKey(id);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台商品目录列表")
    @PostMapping("/admin/list")
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台商品目录列表")
    @PostMapping("/list")
    public ApiRestResponse listCategoryForCustomer(){
        List<ImoocMallCategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }


}
