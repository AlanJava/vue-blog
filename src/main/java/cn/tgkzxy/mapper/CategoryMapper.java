package cn.tgkzxy.mapper;

import cn.tgkzxy.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> queryAllCategories();
    Category queryByTid(@Param("tid") int tid);
    int addCategory(Category category);
    int queryNumByTid(@Param("tid") int tid);
    int updateCateNum(Category category);
    int updateCateName(Category category);
    int deleteCategory(@Param("tid") int tid);
}
