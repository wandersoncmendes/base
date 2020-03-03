package com.base.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class FormWrapper implements Serializable {
    private MultipartFile file;
    private String gymId;
    private List<MultipartFile> files;
    private int userId;
    private String name;
    private DataSaveGym dataSaveGym;


    public FormWrapper(MultipartFile file, int userId, String name) {
        this.file = file;
        this.userId = userId;
        this.name = name;
    }

    public FormWrapper(String gymId, List<MultipartFile> files) {
        this.gymId = gymId;
        this.files = files;
    }

    public FormWrapper(MultipartFile file, String gymId) {
        this.file = file;
        this.gymId = gymId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }


    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public FormWrapper() {
    }

    public DataSaveGym getDataSaveGym() {
        return dataSaveGym;
    }

    public void setDataSaveGym(DataSaveGym dataSaveGym) {
        this.dataSaveGym = dataSaveGym;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }
}
