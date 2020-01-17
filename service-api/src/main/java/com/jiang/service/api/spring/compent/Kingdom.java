package com.jiang.service.api.spring.compent;

import com.jiang.service.api.spring.service.IGarden;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by liyujiang on 2019/12/21.
 */
@Slf4j
@Component
@Data
public class Kingdom implements InitializingBean {

    private IGarden garden;

    private String name;

    Kingdom() {
      log.info("Kingdom constructor...");
    }

    @PostConstruct
    public void post() {
        log.info("Kingdom post...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Kingdom afterPropertiesSet...");
        log.info("garden:" + garden.toString());
    }

    public String showGarden() {
        return garden.toString();
    }

    @Override
    public String toString() {
        return "garden:" + garden + ", name:" + name;
    }
}
