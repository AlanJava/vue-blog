package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.BlogMapper;
import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Override
    public List<Blog> queryAllBlogs() {
        return blogMapper.queryAllBlogs();
    }

    @Override
    public List<Blog> queryBlogByCate(String category) {
        return blogMapper.queryBlogByCate(category);
    }

    @Override
    public Blog queryBlogById(Integer cid) {
        return blogMapper.queryBlogById(cid);
    }

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlog(int cid) {
        return blogMapper.deleteBlog(cid);
    }

    @Override
    public List<Blog> queryBlogByLimit(Map<String, Integer> map) {
        return blogMapper.queryBlogByLimit(map);
    }

    @Override
    public int getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    public int addViews(Integer cid) {
        return blogMapper.addViews(cid);
    }

    @Override
    public int getCategoryCount(String category) {
        return blogMapper.getCategoryCount(category);
    }

    @Override
    public Blog queryLast() {
        return blogMapper.queryLast();
    }

    @Override
    public List<Blog> queryHotBlog(Map<String, Integer> map) {
        return blogMapper.queryHotBlog(map);
    }
}
