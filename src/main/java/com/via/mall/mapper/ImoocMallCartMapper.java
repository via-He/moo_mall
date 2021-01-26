package com.via.mall.mapper;

import com.via.mall.domain.ImoocMallCart;
import com.via.mall.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
public interface ImoocMallCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallCart record);

    int insertSelective(ImoocMallCart record);

    ImoocMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallCart record);

    int updateByPrimaryKey(ImoocMallCart record);

    List<CartVO> selectList(@Param("userId") Integer userId);

    ImoocMallCart selectCartByUserIdAndProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);

    Integer selectOrNot(@Param("userId")Integer userId, @Param("productId")Integer productId,
                        @Param("selected")Integer selected);
}