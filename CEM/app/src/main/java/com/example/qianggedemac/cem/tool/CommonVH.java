package com.example.qianggedemac.cem.tool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/23.
 * 对recycleView的ViewHolder缓存类进行封装
 */

public class CommonVH extends RecyclerView.ViewHolder{
    // sparseArray 用法和Hashmap 一样
    // 但是key 固定是int类型
    // 用它来存放所有的view, key 就是View 的 id
    private SparseArray<View> views; // 行布局的集合
    private Context mContext;

    public View getItemView() {
        return itemView;
    }

    private View itemView; // 行布局



    public CommonVH(View itemView) {
        super(itemView);

        this.itemView = itemView;
        views = new SparseArray<>();


    }

    /**
     * 通过View 的 id 来获得指定的view
     * 如果view没有复制, 就先执行findviewbyid
     * 然后把它放到集合里
     * 使用泛型来取消强转
     *
     * @param id
     * @return 指定的view
     */
    // 泛型的声明 : 方法: 在方法的前面, 类 : 在类名的后面
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            // 证明SpareArray里没有这个View
            view = itemView.findViewById(id);
            // spareArray 放值
            views.put(id, view);
        }
        return (T) view;
    }

    // 专门给listView使用的方法
    public static CommonVH getViewHolder(View itemView, ViewGroup parent, int itemId) {
        CommonVH commonVH;
        if (itemView == null) {
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, parent, false);
            commonVH = new CommonVH(itemView);
            itemView.setTag(commonVH);
        } else {
            commonVH = (CommonVH) itemView.getTag();
        }
        return commonVH;

    }

    // 专门给recyclerView使用的方法
    public static CommonVH getViewHolder(ViewGroup parent, int itemId) {
        return getViewHolder(null, parent, itemId);

    }

    public static CommonVH getViewHoldrer(View view){
        return new CommonVH(view);
    }

    /***************
     * ViewHolder 设置数据的方法
     *********/
    // 设置文字
    public CommonVH setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;

    }
    //TODO 一次性产品
    // 设置播放文字的颜色
    public CommonVH setTextColor(int id, int color){
        TextView textView = getView(id);
        textView.setTextColor(color);


        return this;
    }

    public CommonVH setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }

    public CommonVH setImage(int id, String url) {
        ImageView imageView = getView(id);
        Glide.with(MyApp.getContext()).load(url).into(imageView);
        return this;
    }

    public CommonVH setViewClick(int id, View.OnClickListener listener) {
        // 此处为接口
        getView(id).setOnClickListener(listener);
        return this;
    }
    public CommonVH setImageVisible(int id,int visible) {
        // 此处为接口
        getView(id).setVisibility(visible);
        return this;
    }

    //给行布局设置点击事件
    public CommonVH setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

}
