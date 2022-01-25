package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.FileMapper;
import cn.tgkzxy.pojo.File;
import cn.tgkzxy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;
    @Override
    public int addOneFile(File file) {
        return fileMapper.addOneFile(file);
    }

    @Override
    public int addMoreFile(List<File> files) {
        return fileMapper.addMoreFile(files);
    }

    @Override
    public List<File> queryAllFileByLimit(Map<String, Integer> map) {
        return fileMapper.queryAllFileByLimit(map);
    }

    @Override
    public int getFileCount() {
        return fileMapper.getFileCount();
    }

    @Override
    public int deleteFile(int fid) {
        return fileMapper.deleteFile(fid);
    }

    @Override
    public File queryFileByFid(int fid) {
        return fileMapper.queryFileByFid(fid);
    }
}
