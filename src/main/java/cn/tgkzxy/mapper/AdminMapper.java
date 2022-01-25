package cn.tgkzxy.mapper;

import cn.tgkzxy.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin queryAdminByName(@Param("name") String name);
}
