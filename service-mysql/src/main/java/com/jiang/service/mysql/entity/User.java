package com.jiang.service.mysql.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author liyujiang
 * @date 2020/04/13 16:43
 * @Description
 */
@TableName("t_user")
@Data
@ToString
public class User {
    Long id;
    @TableField("user_sex")
    Integer userSex;
    @TableField("nick_name")
    String nickName;
    String password;
}
