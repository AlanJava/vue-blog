package cn.tgkzxy.mapper;

import cn.tgkzxy.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    int addOneFile(File file);
    int addMoreFile(List<File> files);
    List<File> queryAllFileByLimit(Map<String,Integer> map);
    int getFileCount();
    int deleteFile(@Param("fid")int fid);
    File queryFileByFid(@Param("fid") int fid);
}
