package com.example.qianggedemac.cem.tool;

import com.example.qianggedemac.cem.tool.myapp.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by qianggedemac on 16/12/26.
 */
public class LiteOrmInstance {
    private static LiteOrmInstance liteOrmInstance;
    //liteOrm 需要导jar包
    private LiteOrm mLiteOrm;

    public LiteOrm getLiteOrm() {
        return mLiteOrm;
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
    }
    /**
     * 插入一条数据
     */

}
