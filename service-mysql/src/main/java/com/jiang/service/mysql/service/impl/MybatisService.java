package com.jiang.service.mysql.service.impl;

import com.jiang.service.mysql.entity.User;
import com.jiang.service.mysql.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author liyujiang
 * @date 2020/04/13 17:36
 * @Description
 */
@Service
public class MybatisService implements InitializingBean {

    SqlSessionFactory sqlSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //testSessionCache();
        testGlobalSessionCache();
    }

    private void simpleUse() throws Exception {
        // 1.创建session
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 2.使用mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.getById(1));
    }

    private void testSessionCache() throws Exception {
        // 1.创建session
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        System.out.println(mapper1.getById(1));

        // 第二次将会直接读取缓存
        System.out.println(mapper1.getById(1));

        // 修改，这个时候session1是不知道被修改的，因为mapper1和mapper2各用了缓存
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        mapper2.updatePWById(1, "newbee2");
        sqlSession2.commit();

        System.out.println(mapper1.getById(1));
    }

    private void testGlobalSessionCache() throws Exception {
        // 1.创建session
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.getById(1);
        System.out.println(user1);
        user1.setPassword("jiang");
        // 保证mapper2 可以共享到mapper1的缓存
        /**
         * 1.默认开启
         *   <settings>
         *      <setting name="cacheEnabled" value="true"/>
         *   </settings>
         *
         * 2.增加使用二级缓存
         *   <cache eviction="LRU" size="1024"/>
         *   <select id="getById" resultType="com.jiang.service.mysql.entity.User" useCache="true">
         */
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.getById(1);
        System.out.println(user2);
    }
}
