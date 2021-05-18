package com.home.lh.config.mb;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = null;
        Object updateTime = null;
        if(metaObject.hasGetter("createTime") ){
            createTime = getFieldValByName("createTime", metaObject);
        }
        if(metaObject.hasGetter("updateTime")){
            updateTime = getFieldValByName("updateTime", metaObject);
        }
        LocalDateTime now = LocalDateTime.now();
        if (createTime == null && metaObject.hasSetter("createTime")){
            setFieldValByName("createTime",now, metaObject);
        }
        if (updateTime == null && metaObject.hasSetter("updateTime")){
            setFieldValByName("updateTime",now, metaObject);
        }

    }
 
    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("updateTime")){
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
