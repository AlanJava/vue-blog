package cn.tgkzxy.controller;


import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.FileService;
import cn.tgkzxy.service.LogsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    LogsService logsService;

    @GetMapping("/count")
    @ResponseBody
    public Map<String,Integer> getFileCount(){
        int fileCount = fileService.getFileCount();
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        countMap.put("fileCount",fileCount);
        return countMap;
    }

    @GetMapping("/{startIndex}/{pageSize}")
    @ResponseBody
    public String queryAllFileByLimit(@PathVariable("startIndex") Integer startIndex,
                                      @PathVariable("pageSize")Integer pageSize){
        Map<String,Integer> limitMap = new HashMap<String,Integer>();
        limitMap.put("startIndex",startIndex);
        limitMap.put("pageSize",pageSize);
        List<cn.tgkzxy.pojo.File> files = fileService.queryAllFileByLimit(limitMap);
        return JSON.toJSONString(files);

    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart("imgs") MultipartFile[] imgs)throws IOException {
        //获取jar包位置
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        String dirPath = source.getParentFile().getParentFile().toString()+"/upload/";
        System.out.println(dirPath);
        File filePath = new File(dirPath);
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        //保存到文件服务器
        if(imgs.length>0){
            for (MultipartFile img:imgs
            ) {
                String originalFilename = img.getOriginalFilename();
                img.transferTo(new File(dirPath+originalFilename));
                cn.tgkzxy.pojo.File file = new cn.tgkzxy.pojo.File();
                file.setFileName(originalFilename);
                file.setDir("/upload/"+originalFilename);
                fileService.addOneFile(file);
            }
            //日志
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("上传文件");
            logsService.addLog(logs);
        }
        return "";
    }

    @DeleteMapping("/{fid}")
    @ResponseBody
    public String deleteFile(@PathVariable("fid") int fid){
        cn.tgkzxy.pojo.File myFile = fileService.queryFileByFid(fid);

        //获取jar包位置
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        String dirPath = source.getParentFile().getParentFile().toString()+myFile.getDir();
        System.out.println(dirPath);
        // java io 包的file
        File file = new File(dirPath);
        if (file.exists()) {
            //删文件
            file.delete();
            //删数据库记录
            fileService.deleteFile(fid);
            System.out.println("删除成功");
            //日志
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("删除文件");
            logsService.addLog(logs);
            return "ok";
        } else {
            System.out.println("删除失败");
            return "no";
        }

    }

}
