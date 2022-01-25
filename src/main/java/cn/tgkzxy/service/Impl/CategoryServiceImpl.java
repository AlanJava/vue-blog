package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.BlogMapper;
import cn.tgkzxy.mapper.CategoryMapper;
import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.pojo.Category;
import cn.tgkzxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BlogMapper blogMapper;
    @Override
    public List<Category> queryAllCategories() {
        return categoryMapper.queryAllCategories();
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Override
    public Category queryByTid(int tid) {
        return categoryMapper.queryByTid(tid);
    }

    @Override
    public int queryNumByTid(int tid) {
        return categoryMapper.queryNumByTid(tid);
    }

    @Override
    public int updateCateNum(Category category) {
        return categoryMapper.updateCateNum(category);
    }

    @Override
    @Transactional
    public int updateCateName(Category category) {
        Category oldCategory = categoryMapper.queryByTid(category.getTid());
        List<Blog> blogs = blogMapper.queryBlogByCate(oldCategory.getCategoryName());
        //分类名更改,连带着blog的分类一起改
        if (!ObjectUtils.isEmpty(blogs)){
            for (Blog b:blogs
                 ) {
                b.setCategory(category.getCategoryName());
                int result = blogMapper.updateBlog(b);
            }
        }
        return categoryMapper.updateCateName(category);
    }

    @Override
    public int deleteCategory(int tid) {
        return categoryMapper.deleteCategory(tid);
    }


}
