package com.via.mall.controller;

import com.via.mall.common.ApiRestResponse;
import com.via.mall.common.Constant;
import com.via.mall.domain.ImoocMallUser;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.service.ImoocMallUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Qingqing.He
 * @date 2020/12/25 16:05
 * 描述：用户控制器
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    ImoocMallUserService userService;

    @GetMapping("/byId")
    public ImoocMallUser personalPage(){
        return userService.selectByPrimaryKey(2);
    }

    /**
     * 注册新用户
     * @param userName
     * @param password
     * @return
     * @throws MallException
     */
    @ApiOperation("注册新用户")
    @PostMapping("/register")
    public ApiRestResponse register(@RequestParam("userName") String userName,
                                    @RequestParam("password") String password ) throws MallException {
        if (StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        if (password.length() < 8){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(userName,password);

        return ApiRestResponse.success();

    }

    /**
     * 普通人员登录
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws MallException
     */
    @ApiOperation("普通人员登录")
    @PostMapping("/login")
    public ApiRestResponse login(@RequestParam("userName")String userName,
                                 @RequestParam("password")String password, HttpSession session) throws MallException {

        if (StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        ImoocMallUser user = userService.login(userName,password);
        //为了安全性，将密码设置为空 即返回user时密码为空
        user.setPassword(null);
        session.setAttribute(Constant.MALL_USER,user);
        return ApiRestResponse.success(user);
    }

    /**
     * 更新个性签名
     * @param session
     * @param signature
     * @return
     */
    @ApiOperation("更新个性签名")
    @PostMapping("/update")
    public ApiRestResponse updateUserInfo(HttpSession session,@RequestParam String signature){
        ImoocMallUser currentUser = (ImoocMallUser) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null){
            return  ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        //为什么要再new一个user呢，直接currentUser.setXxx()会怎样
//        ImoocMallUser user = new ImoocMallUser();
        currentUser.setPersonalizedSignature(signature);
        userService.updateByPrimaryKeySelective(currentUser);

        return ApiRestResponse.success();
    }

    /**
     * 登出，清除session
     * @param session
     * @return
     */
    @ApiOperation("登出，清除session")
    @PostMapping("/logout")
    public ApiRestResponse logout(HttpSession session){
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws MallException
     */
    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    public ApiRestResponse adminLogin(@RequestParam("userName")String userName,
                                 @RequestParam("password")String password, HttpSession session) throws MallException {

        if (StringUtils.isEmpty(userName)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        ImoocMallUser user = userService.login(userName,password);
       //校验管理员
        if (userService.checkAdminRole(user)) {
            //为了安全性，将密码设置为空 即返回user时密码为空
            user.setPassword(null);
            session.setAttribute(Constant.MALL_USER,user);
            return ApiRestResponse.success(user);
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

    }

}
