package com.example.lesson7;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FolderViewModel extends AndroidViewModel {

    private FolderRepository mRepository;
    private LiveData<List<Folder>> mAllFolders;

    public FolderViewModel(Application application) {
        super(application);
        mRepository = new FolderRepository(application);
        mAllFolders = mRepository.getAllFolders();
    }

    LiveData<List<Folder>> getAllWords() {
        return mAllFolders;
    }

    public void insert(Folder folder) {
        mRepository.insert(folder);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void updateFolder(Folder folder){
        mRepository.updateFolder(folder);
    }
}

