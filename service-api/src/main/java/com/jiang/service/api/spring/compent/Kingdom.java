package com.jiang.service.api.spring.compent;

import com.jiang.service.api.spring.service.IGarden;
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
public class Kingdom implements InitializingBean {

    private IGarden garden;

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

    public void setGarden(IGarden garden) {
        this.garden = garden;
    }
}
