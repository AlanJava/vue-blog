package cn.tgkzxy.service;

import cn.tgkzxy.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> queryAllBlogs();
    List<Blog> queryBlogByCate(@Param("category") String category);
    Blog queryBlogById(@Param("cid") Integer cid);
    int addBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlog(@Param("cid") int cid);
    List<Blog> queryBlogByLimit(Map<String,Integer> map);
    int getBlogCount();
    int addViews(Integer cid);
    int getCategoryCount(String category);
    Blog queryLast();
    List<Blog> queryHotBlog(Map<String,Integer> map);
}
