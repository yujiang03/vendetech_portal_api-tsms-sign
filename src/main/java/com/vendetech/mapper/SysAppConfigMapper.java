package com.vendetech.mapper;

import com.vendetech.vo.SysAppConfig;

public interface SysAppConfigMapper {

    int deleteByPrimaryKey(Long appId);

    int insert(SysAppConfig record);

    int insertSelective(SysAppConfig record);

    SysAppConfig selectByPrimaryKey(Long appId);

    int updateByPrimaryKeySelective(SysAppConfig record);

    int updateByPrimaryKey(SysAppConfig record);
}