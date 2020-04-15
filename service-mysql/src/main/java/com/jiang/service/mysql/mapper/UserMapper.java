package com.jiang.service.mysql.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.service.mysql.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liyujiang
 * @date 2020/04/13 16:44
 * @Description
 */
@DS("slave")
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    User getById(@Param("id") Integer id);

    /**
     * 根据id修改密码
     *
     * @param id
     * @param password
     * @return
     */
    Integer updatePWById(@Param("id") Integer id, @Param("password") String password);
}
