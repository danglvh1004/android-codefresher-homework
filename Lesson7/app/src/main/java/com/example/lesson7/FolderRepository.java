package com.example.lesson7;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class FolderRepository {

    private FolderDao mFolderDao;
    private LiveData<List<Folder>> mAllFolders;

    FolderRepository(Application application) {
        FolderDatabase db = FolderDatabase.getDatabase(application);
        mFolderDao = db.folderDao();
        mAllFolders = mFolderDao.getAllFolders();
    }

    LiveData<List<Folder>> getAllFolders() {
        return mAllFolders;
    }

    private static class insertAsyncTask extends AsyncTask<Folder, Void, Void> {

        private FolderDao mAsyncTaskDao;

        insertAsyncTask(FolderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Folder... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert(Folder folder) {
        new insertAsyncTask(mFolderDao).execute(folder);
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {

        private FolderDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(FolderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll() {
        new deleteAllWordsAsyncTask(mFolderDao).execute();
    }

    private static class updateFoldersAsyncTask extends AsyncTask<Folder, Void, Void> {
        private FolderDao mAsyncTaskDao;

        updateFoldersAsyncTask(FolderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Folder... params) {
            mAsyncTaskDao.updateFolder(params[0]);
            return null;
        }
    }

    public void updateFolder(Folder folder) {
        new insertAsyncTask(mFolderDao).execute(folder);
    }
}
