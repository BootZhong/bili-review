package com.zfg.learn.dao;

import com.zfg.learn.model.po.Dynamic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicMapper {
    public void insertBatch(@Param("dynamicList") List<Dynamic> dynamic);

    void  insert(Dynamic dynamic);
}

