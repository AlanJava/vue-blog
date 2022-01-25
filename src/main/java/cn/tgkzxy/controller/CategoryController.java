package cn.tgkzxy.controller;

import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.pojo.Category;
import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.BlogService;
import cn.tgkzxy.service.CategoryService;
import cn.tgkzxy.service.LogsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BlogService blogService;
    @Autowired
    LogsService logsService;
    @GetMapping("/all")
    @ResponseBody
    public String queryAllCategories(){
        List<Category> categories = categoryService.queryAllCategories();
        String s = JSON.toJSONString(categories);
        return s;
    }
    @GetMapping("/type/{categoryName}")
    @ResponseBody
    public String queryBlogByCate(@PathVariable("categoryName")String category){
        List<Blog> blogs = blogService.queryBlogByCate(category);
        String s = JSON.toJSONString(blogs);
        return s;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addCategory(@RequestParam("categoryName")String categoryName){
        Category category = new Category();
        category.setCategoryName(categoryName);
        int i = categoryService.addCategory(category);
        //日志
        Date date = new Date();
        long time = date.getTime();
        Logs logs = new Logs();
        logs.setTime(time);
        logs.setAction("添加分类");
        logsService.addLog(logs);
        return JSON.toJSONString(category);
    }
    @PostMapping("/update")
    @ResponseBody
    public String updateCategoryName(@RequestParam("tid") Integer tid,
                                     @RequestParam("categoryName") String categoryName){
        Category category = new Category();
        category.setTid(tid);
        category.setCategoryName(categoryName);
        int i = categoryService.updateCateName(category);
        //日志
        Date date = new Date();
        long time = date.getTime();
        Logs logs = new Logs();
        logs.setTime(time);
        logs.setAction("更改分类");
        logsService.addLog(logs);
        return JSON.toJSONString(category.toString());
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteCategory(@RequestParam("tid") int tid,
                                 @RequestParam("categoryName") String categoryName,
                                 HttpServletResponse response){
        int categoryCount = blogService.getCategoryCount(categoryName);
        if (categoryCount == 0) {
            int i = categoryService.deleteCategory(tid);
            //日志
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("删除分类");
            logsService.addLog(logs);
            return "200";
        }
        else {
            response.setStatus(400);
            return "400";
        }
    }

}
