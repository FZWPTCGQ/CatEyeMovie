package com.example.qianggedemac.cem.film.city;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qianggedemac on 17/1/7.
 */
public class DBTools {
    // 此方法为 饿汉式 单例
    // LiteOrm 在使用的时候要使用单例
    private static DBTools dbTools = new DBTools();
    private final ExecutorService mThreadPool;
    private LiteOrm mLiteOrm;

    private Handler mHandler;

    private DBTools() {


        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), "MaoYanMovie.db");
        // 确保在主线程中执行
        mHandler = new Handler(Looper.getMainLooper());
        int core = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(core + 1);

    }

    public static DBTools getInstance() {
        return dbTools;
    }

    /**
     * 数据插入
     */
    public <T> void inserthistoryInfo(T t) {

        long  kk =  mLiteOrm.insert(t);

    }

    public <T> void insertMusciInfo(List<T> ts) {
        mLiteOrm.insert(ts);
    }

    // 删除
    public <T> void deleteAllInfo(Class<T> tClass) {
        mLiteOrm.deleteAll(tClass);
    }

    // 删除单个
    public <T> void deleteOneHistoryInfo(Class<T> tClass, String content) {
        int count = mLiteOrm.delete(new WhereBuilder(tClass).where("content" + "=?", content));
        Log.d("DBTools", "单个删除count:" + count);
    }


    /**
     * 修改播放状态
     *
     * @param tClass 表名, 即类名
     * @param songId 歌曲的id 用来唯一值的匹配
     * @param column 要修改的列名
     * @param value  之后修改的值
     * @param <T>
     */
    public <T> void modifyMusicInfo(Class<T> tClass, String songId, String column, int value) {


        ColumnsValue columnsValue = new ColumnsValue(new String[]{column}, new Integer[]{value});
        if(songId.equals("")){
            int count = mLiteOrm.update(new WhereBuilder(tClass), columnsValue, ConflictAlgorithm.Replace);
        }
        else {

            int count = mLiteOrm.update(new WhereBuilder(tClass).where("songId" + "=?", songId), columnsValue, ConflictAlgorithm.Replace);
            Log.d("DBTools", "count:" + count);
        }
    }
    // 查询
    public <T> void queryHistoryInfo(Class<T> tClass, final OnQueryHistoryInfo<T> mOnQueryInfo) {

        mThreadPool.execute(new QueryRunnable(mOnQueryInfo, tClass));
    }
    // 一个传值的接口

    public interface OnQueryHistoryInfo<T> {
        void OnQuery(ArrayList<T> query);
    }

    private class QueryRunnable<T> implements Runnable {
        private OnQueryHistoryInfo onQueryMusicInfo;
        private Class mClass;


        public QueryRunnable(OnQueryHistoryInfo mOnQueryMusicInfo, Class<T> tClass) {
            this.onQueryMusicInfo = mOnQueryMusicInfo;
            this.mClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = mLiteOrm.query(mClass);
            mHandler.post(new CallBacks(onQueryMusicInfo, query));
        }
    }

    // 将值发回main
    private class CallBacks<T> implements Runnable {
        private OnQueryHistoryInfo onQueryPersonValue;
        private ArrayList<T> beanArrayList;

        public CallBacks(OnQueryHistoryInfo onQueryPersonValue,
                         List<T> beanArrayList) {
            this.onQueryPersonValue = onQueryPersonValue;
            this.beanArrayList = (ArrayList<T>) beanArrayList;
        }

        @Override
        public void run() {
            onQueryPersonValue.OnQuery(beanArrayList);
        }
    }
}
