package cn.tgkzxy.service;

import cn.tgkzxy.pojo.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileService {
    int addOneFile(File file);
    int addMoreFile(List<File> files);
    List<File> queryAllFileByLimit(Map<String,Integer> map);
    int getFileCount();
    int deleteFile(int fid);
    File queryFileByFid(int fid);
}
