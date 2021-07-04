package com.example.lesson7;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final FolderDao mDao;
    List<Folder> folderList = addDatatoArrayList();

    PopulateDbAsync(FolderDatabase db) {
        mDao = db.folderDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        // Start the app with a clean database every time.
        // Not needed if you only populate the database
        // when it is first created
        mDao.deleteAll();

        for (int i = 0; i <= folderList.size() - 1; i++) {
            Folder folder = folderList.get(i);
            mDao.insert(folder);
        }
        return null;
    }

    private List<Folder> addDatatoArrayList() {
        List<Folder> folderList = new ArrayList<>();

        folderList.add(new Folder("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        folderList.add(new Folder("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        folderList.add(new Folder("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        folderList.add(new Folder("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        folderList.add(new Folder("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        folderList.add(new Folder("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));

        return folderList;
    }
}
