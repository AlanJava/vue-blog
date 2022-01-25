package cn.tgkzxy.service;

import cn.tgkzxy.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {
    List<Category> queryAllCategories();
    int addCategory(Category category);
    Category queryByTid(int tid);
    int queryNumByTid(int tid);
    int updateCateNum(Category category);
    int updateCateName(Category category);
    int deleteCategory(int tid);
}
