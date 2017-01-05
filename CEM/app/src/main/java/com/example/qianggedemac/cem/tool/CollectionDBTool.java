package com.example.qianggedemac.cem.tool;

import android.os.Handler;
import android.os.Looper;

import com.example.qianggedemac.cem.film.findfilm.ClassifyBean;
import com.example.qianggedemac.cem.find.CollectionBean;
import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by qianggedemac on 17/1/3.
 */
public class CollectionDBTool {
    private static CollectionDBTool collectionDBTool = new CollectionDBTool();
    private LiteOrm mLiteOrm;
    private Handler mHandler;
    private Executor mThreadPool;
    public static CollectionDBTool getInstance() {
        return collectionDBTool;
    }

    /**
     * 构造方法私有化
     */
    private CollectionDBTool() {
      mLiteOrm = mLiteOrm.newSingleInstance(MyApp.getContext(),"collection.db");
        /**
         * 保证在主线程中运行
         */
        mHandler = new Handler(Looper.getMainLooper());
        /**
         * 获取手机核数
         */
        int coreNum = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);
    }
    /**
     * 插入方法
     */
    public void insertCollectBean(final CollectionBean collectionBean){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(collectionBean);
            }
        });
    }
    /**
     * 删除表的方法
     */
    public void deleteCollectBean(final Class<CollectionBean> collectionBeanClass){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(collectionBeanClass);
            }
        });
    }
    /**
     * 删除某一数据
     */
    public void deleteValueBean(final Class<CollectionBean> collectionBeanClass, final String field, final String[] value){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(collectionBeanClass, WhereBuilder.create(collectionBeanClass).where(field + "=?",value));
            }
        });
    }
    /**
     * 这个方式的查询或者是插入,没有返回值的,是通过接口形式
     * 按条件查询
     */
    public void queryByValuesCollectBean(final Class<CollectionBean> collectionBeanClass, final String field, final String[] value, final OnQueryListener onQueryListener){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                final List<CollectionBean> data = mLiteOrm.query(new QueryBuilder<CollectionBean>(collectionBeanClass).where(field + "=?",value));
                /**
                 * 将值传到主线程
                 */
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onQueryListener.onQuery(data);
                    }
                });
            }
        });
    }

    /**
     * 查询方法
     */
    public void queryAllCollectBean(OnQueryListener onQueryListener){
        mThreadPool.execute(new QueryRunnable(onQueryListener));
    }
   public class QueryRunnable implements Runnable{
      private OnQueryListener mOnQueryListener;

       public QueryRunnable(OnQueryListener onQueryListener) {
           mOnQueryListener = onQueryListener;
       }


       @Override
       public void run() {
           List<CollectionBean> query = mLiteOrm.query(CollectionBean.class);
           mHandler.post(new CallBackRunnable(mOnQueryListener,query));
       }
   }
    /**
     * Handler使用 将查询的数据饭回到主线程使用的Runnable
     */
    public class CallBackRunnable implements Runnable{
     private OnQueryListener mOnQueryListener;
        private List<CollectionBean> mCollectionBeen;

        public CallBackRunnable(OnQueryListener onQueryListener, List<CollectionBean> collectionBeen) {
            mOnQueryListener = onQueryListener;
            mCollectionBeen = collectionBeen;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(mCollectionBeen);
        }
    }

    /**
     * 返回查询结果用的接口
     */
    public interface OnQueryListener{
        void onQuery(List<CollectionBean> collectionBeen);
    }
}
