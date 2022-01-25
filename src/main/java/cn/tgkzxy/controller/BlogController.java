package cn.tgkzxy.controller;

import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.BlogService;
import cn.tgkzxy.service.CategoryService;
import cn.tgkzxy.service.LogsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    LogsService logsService;

    //获取blog总数
    @GetMapping("/count")
    @ResponseBody
    public Map<String,Integer> getBlogCount(){
        Integer blogCount = blogService.getBlogCount();
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        countMap.put("blogCount",blogCount);
        return countMap;
    }


    //查询所有blog
    @GetMapping("/all")
    @ResponseBody
    public String queryAllBlogs(){
        List<Blog> blogs = blogService.queryAllBlogs();
        String s = JSON.toJSONString(blogs);
        return s;
    }
    @GetMapping("/{startIndex}/{pageSize}")
    @ResponseBody
    public String queryBlogByLimit(@PathVariable("startIndex") Integer startIndex,
                                   @PathVariable("pageSize")Integer pageSize){
        Map<String,Integer> limitMap = new HashMap<String,Integer>();
        limitMap.put("startIndex",startIndex);
        limitMap.put("pageSize",pageSize);
        List<Blog> blogs = blogService.queryBlogByLimit(limitMap);

        return JSON.toJSONString(blogs);
    }

    @GetMapping("/hot/{startIndex}/{pageSize}")
    @ResponseBody
    public String queryHotBlog(@PathVariable("startIndex") Integer startIndex,
                                   @PathVariable("pageSize")Integer pageSize){
        Map<String,Integer> limitMap = new HashMap<String,Integer>();
        limitMap.put("startIndex",startIndex);
        limitMap.put("pageSize",pageSize);
        List<Blog> blogs = blogService.queryHotBlog(limitMap);
        return JSON.toJSONString(blogs);
    }

    //通过cid查询blog
    @GetMapping("/detail/{cid}")
    @ResponseBody
    public String queryBlogById(@PathVariable("cid") Integer cid){
        Blog blog = blogService.queryBlogById(cid);
        int i = blogService.addViews(cid);
        String s = JSON.toJSONString(blog);
        return s;

    }

    //添加blog
    @PostMapping("/addblog")
    @ResponseBody
    public String addBlog(@RequestParam("title")String title,
                          @RequestParam("content")String content,
                          @RequestParam("category")String category,
                          @RequestParam("introduction")String introduction
                          ){
        //创建时间
        Date date = new Date();
        long time = date.getTime();
        //新建blog并设置值
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCreated(time);
        blog.setCategory(category);
        blog.setViews(0);
        blog.setIntroduction(introduction);
        //插入blog
        int i = blogService.addBlog(blog);
        //加日志
        Logs logs = new Logs();
        logs.setTime(time);
        logs.setAction("添加文章");
        logsService.addLog(logs);
        String s = JSON.toJSONString(blog);
        return s;

    }

    //更新blog
    /**
     * 分类同步未解决
     * @param title
     * @param content
     * @param category
     * @param introduction
     * @param cid
     * @param views
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public String updateBlog(@RequestParam("title")String title,
                             @RequestParam("content")String content,
                             @RequestParam("category")String category,
                             @RequestParam("introduction")String introduction,
                             @RequestParam("cid") int cid,
                             @RequestParam("views") int views){
        //创建时间
        Date date = new Date();
        long time = date.getTime();
        Blog blog = new Blog();
        blog.setCid(cid);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCreated(time);
        blog.setCategory(category);
        blog.setViews(views);
        blog.setIntroduction(introduction);
        int i = blogService.updateBlog(blog);
        //日志
        Logs logs = new Logs();
        logs.setTime(time);
        logs.setAction("修改文章");
        logsService.addLog(logs);

        String s = JSON.toJSONString(blog);
        return s;
    }

    /**
     * 分类未解决
     * @param cid
     * @return
     */
    @DeleteMapping("delete/{cid}")
    @ResponseBody
    public String deleteBlog(@PathVariable("cid") int cid){

        int i = blogService.deleteBlog(cid);
        String code = "400";
        if (i==1) {
            code = "200";
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("删除文章");
            logsService.addLog(logs);
        }
        return JSON.toJSONString(code);
    }
}
