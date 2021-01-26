package com.via.mall.service.impl;

import com.via.mall.common.Constant;
import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.mapper.ImoocMallProductMapper;
import com.via.mall.vo.CartVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.via.mall.mapper.ImoocMallCartMapper;
import com.via.mall.domain.ImoocMallCart;
import com.via.mall.service.ImoocMallCartService;

import java.util.List;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallCartServiceImpl implements ImoocMallCartService{

    @Resource
    private ImoocMallCartMapper imoocMallCartMapper;

    @Resource
    private ImoocMallProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imoocMallCartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImoocMallCart record) {
        return imoocMallCartMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallCart record) {
        return imoocMallCartMapper.insertSelective(record);
    }

    @Override
    public ImoocMallCart selectByPrimaryKey(Integer id) {
        return imoocMallCartMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallCart record) {
        return imoocMallCartMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallCart record) {
        return imoocMallCartMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CartVO> list(Integer userId){
        List<CartVO> cartVOS = imoocMallCartMapper.selectList(userId);
        for (int i = 0; i < cartVOS.size(); i++) {
            CartVO cartVO = cartVOS.get(i);
            cartVO.setTotalPrice(cartVO.getPrice()*cartVO.getQuantity());

        }
        return cartVOS;
    }

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count){
        validProduct(productId,count);
        ImoocMallCart cart = imoocMallCartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
           //该商品之前不在购物车，新增一个记录
            cart = new ImoocMallCart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.Cart.CHECKED);
            imoocMallCartMapper.insertSelective(cart);
        }else {

            //该商品已经在购物车，则数量相加
            count = cart.getQuantity()+count;
            ImoocMallCart newCart = new ImoocMallCart();
            newCart.setQuantity(count);
            newCart.setId(cart.getId());
            newCart.setProductId(cart.getProductId());
            newCart.setUserId(cart.getUserId());
            newCart.setSelected(Constant.Cart.CHECKED);
            imoocMallCartMapper.updateByPrimaryKeySelective(newCart);
        }
        return this.list(userId);
    }

    private void validProduct(Integer productId, Integer count) {
        ImoocMallProduct product = productMapper.selectByPrimaryKey(productId);
        //判断商品是否存在，商品是否上架
        if (product == null || product.getStatus().equals(Constant.SaleStatus.NOT_SALE)) {
            throw new MallException(MallExceptionEnum.NOT_SALE);
        }

        //判断商品库存
        if (count > product.getStock()){
            throw new MallException(MallExceptionEnum.NOT_STOCK);
        }
    }

    @Override
    public List<CartVO> update(Integer userId, Integer productId, Integer count){
        validProduct(productId,count);
        ImoocMallCart cart = imoocMallCartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //该商品之前不在购物车，无法更新
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }else {

            //该商品已经在购物车，则更新数量
            ImoocMallCart newCart = new ImoocMallCart();
            newCart.setQuantity(count);
            newCart.setId(cart.getId());
            newCart.setProductId(cart.getProductId());
            newCart.setUserId(cart.getUserId());
            newCart.setSelected(Constant.Cart.CHECKED);
            imoocMallCartMapper.updateByPrimaryKeySelective(newCart);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> delete(Integer userId, Integer productId){
        ImoocMallCart cart = imoocMallCartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //该商品之前不在购物车，无法删除
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }else {

            //该商品已经在购物车，则删除
            imoocMallCartMapper.deleteByPrimaryKey(cart.getId());
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected){
        ImoocMallCart cart = imoocMallCartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //该商品之前不在购物车，无法选中
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }else {

            //该商品已经在购物车，则选中/不选中
            imoocMallCartMapper.selectOrNot(userId,productId,selected);
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectAllOrNot(Integer userId,Integer selected){
        imoocMallCartMapper.selectOrNot(userId,null,selected);
        return this.list(userId);
    }
}
