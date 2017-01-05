package com.example.qianggedemac.cem.tool;

import android.os.Handler;
import android.os.Looper;

import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qianggedemac on 16/12/26.
 */
public class LiteOrmInstance {
    private static LiteOrmInstance liteOrmInstance;
    //liteOrm 需要导jar包
    private LiteOrm mLiteOrm;
    /**
     * 因为存入数据库属于耗时任务
     * 所以需要在线程池中完成
     */
    private static ExecutorService mThreadPool;

    private Handler mHandler;
    public static LiteOrmInstance getInstance(){
        return liteOrmInstance;
    }

    public static LiteOrmInstance getLiteOrmInstance() {
        if (liteOrmInstance == null){
            synchronized (LiteOrmInstance.class){
                if (liteOrmInstance == null){
                    liteOrmInstance = new LiteOrmInstance();
                }
            }
        }
        return liteOrmInstance;
    }
    private LiteOrmInstance() {
        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getContext(),"MyApp.db");
        /**
         * 确保在主线程中执行
         */
        mHandler = new Handler(Looper.getMainLooper());
        /**
         * 获取手机核数
         */
        int core = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(core + 1);
    }
    /**
     * 插入一条数据
     */
   public <T> void insertHistoryInfo(T t){
       long kk = mLiteOrm.insert(t);
   }

    /**
     * 删除数据类
     */
    public <T> void deleteAllInfo(Class<T> tClass){
        mLiteOrm.deleteAll(tClass);
    }
    /**
     * 删除单个
     */
    public <T> void deleteOneHistoryInfo(Class<T> tClass,String content){
     int count = mLiteOrm.delete(new WhereBuilder(tClass).where("content" + "=?", content));
    }
    /**
     * 查询
     */
    public <T> void queryHistoryInfo(Class<T> tClass,final OnQueryHistoryInfo mOnQueryInfo){
        mThreadPool.execute(new QueryRunnable(mOnQueryInfo,tClass));
    }

    /**
     * 一个传值接口
     */
    public interface OnQueryHistoryInfo<T>{
        void OnQuery(ArrayList<T> query);
    }
    private class QueryRunnable<T> implements Runnable{
       private OnQueryHistoryInfo mOnQueryHistoryInfo;
        private Class mClass;

        public QueryRunnable(OnQueryHistoryInfo onQueryHistoryInfo,Class<T> tClass) {
            mOnQueryHistoryInfo = onQueryHistoryInfo;
            mClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = mLiteOrm.query(mClass);
            mHandler.post(new CallBacks(mOnQueryHistoryInfo,query));
        }
    }

    /**
     * 将值发回main
     */
    private class CallBacks<T> implements Runnable{
        private OnQueryHistoryInfo mOnQueryHistoryInfo;
        private ArrayList<T> beanArrayList;

        public CallBacks(OnQueryHistoryInfo onQueryHistoryInfo, ArrayList<T> beanArrayList) {
            mOnQueryHistoryInfo = onQueryHistoryInfo;
            this.beanArrayList = beanArrayList;
        }

        @Override
        public void run() {
            mOnQueryHistoryInfo.OnQuery(beanArrayList);
        }
    }
}
