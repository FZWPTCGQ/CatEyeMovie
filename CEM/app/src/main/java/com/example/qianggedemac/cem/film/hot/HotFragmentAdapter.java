package com.example.qianggedemac.cem.film.hot;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 16/12/20.
 */

public class HotFragmentAdapter extends BaseAdapter {
    private HotFragmentListViewBean mHotFragmentListViewBean;
    private HotRefreshBean mHotRefreshBean;
    private Context mContext;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int COUNT = 100;
    private String mNewUrl;
    private String mVerIv;
    private String mBoxTv;
    private String mImgPath;

    public HotFragmentAdapter(Context context) {
        mContext = context;
    }

    public void setHotFragmentListViewBean(HotFragmentListViewBean hotFragmentListViewBean) {
        mHotFragmentListViewBean = hotFragmentListViewBean;
        notifyDataSetChanged();
    }

    public void setHotRefreshBean(HotRefreshBean hotRefreshBean) {
        mHotRefreshBean = hotRefreshBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mHotRefreshBean == null){

            return 10;
        }else{
            return 10 + mHotRefreshBean.getData().getMovies().size() ;
        }
    }

    @Override
    public Object getItem(int i) {
        return mHotFragmentListViewBean.getData().getHot().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("fasf", "position:" + position);
        if (position == 0){

            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }
    public void clean(){
        mHotFragmentListViewBean.getData().getHot().clear();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VHOne vhOne = null;
        VHTwo vhTwo = null;
        int type = getItemViewType(i);
        switch (type){
            case TYPE_ONE:
                if (view == null){
                     view = LayoutInflater.from(mContext).inflate(R.layout.hot_fragment_adapter_one_item,viewGroup,false);
                    vhOne = new VHOne(view);
                    view.setTag(vhOne);
                }else{
                   vhOne = (VHOne) view.getTag();
                }
                mImgPath = mHotFragmentListViewBean.getData().getHot().get(i).getImg();
                mNewUrl = mImgPath.replace("/w.h/","/165.220/");
                Glide.with(mContext).load(mNewUrl).into(vhOne.imgIvOne);
                mVerIv = mHotFragmentListViewBean.getData().getHot().get(i).getVer();
                if (mVerIv.contains("3D")){
                    vhOne.verIvOne.setImageResource(R.mipmap.wo);
                }else if (mVerIv.contains("3D") && mVerIv.contains("IMAX")){
                    vhOne.verIvOne.setImageResource(R.mipmap.main_movie_hot_3d_imax);
                }else if (mVerIv.contains("2D") && mVerIv.contains("IMAX")){
                    vhOne.verIvOne.setImageResource(R.mipmap.main_movie_hot_2d_imax);
                }
                mBoxTv = mHotFragmentListViewBean.getData().getHot().get(i).getBoxInfo();
                Log.d("啊啊啊啊", mBoxTv);
                if (!mBoxTv.equals("喵，即将上映")){
                    vhOne.scTvOne.setText("观众" + mHotFragmentListViewBean.getData().getHot().get(i).getSc());
                    vhOne.proscoreTvOne.setText("专业" + mHotFragmentListViewBean.getData().getHot().get(i).getProScore());
                }else{
                    vhOne.scTvOne.setText(mHotFragmentListViewBean.getData().getHot().get(i).getWish() + "人想看");
                }
                vhOne.scmTvOne.setText(mHotFragmentListViewBean.getData().getHot().get(i).getScm());
                vhOne.showInFoTvOne.setText(mHotFragmentListViewBean.getData().getHot().get(i).getShowInfo());
                if (!mBoxTv.equals("喵，即将上映")){
                    vhOne.gPTv.setText("购票");
                }else{
                    vhOne.gPTv.setText("预售");
                }
                vhOne.nmTvOne.setText(mHotFragmentListViewBean.getData().getHot().get(i).getNm());

                /**
                 * 点击事件
                 */
                vhOne.playIvOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "播放被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                vhOne.gPTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "购票被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                vhOne.titleOneTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "票房被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                vhOne.titleTwoTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "专题被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case TYPE_TWO:
                if (i <= 10){
                    if (view == null){
                        view = LayoutInflater.from(mContext).inflate(R.layout.hot_fragment_adapter_two_item,viewGroup,false);
                        vhTwo = new VHTwo(view);
                        view.setTag(vhTwo);
                    }else{
                        vhTwo = (VHTwo) view.getTag();
                    }
                    vhTwo.nmTvTwo.setText(mHotFragmentListViewBean.getData().getHot().get(i).getNm());
                    String imgUrl = mHotFragmentListViewBean.getData().getHot().get(i).getImg();
                    String newUrl = imgUrl.replace("/w.h/","/165.220/");
                    Glide.with(mContext).load(newUrl).into(vhTwo.imgIvTwo);
                    String verIv = mHotFragmentListViewBean.getData().getHot().get(i).getVer();
                    if (verIv.contains("3D")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.wo);
                    }else if (verIv.contains("3D") && verIv.contains("IMAX")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.main_movie_hot_3d_imax);
                    }else if (verIv.contains("2D") && verIv.contains("IMAX")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.main_movie_hot_2d_imax);
                    }
                    String boxTv = mHotFragmentListViewBean.getData().getHot().get(i).getBoxInfo();
                    Log.d("啊啊啊啊", boxTv);
                    if (!boxTv.equals("喵，即将上映")){
                        vhTwo.scTvTwo.setText("观众" + mHotFragmentListViewBean.getData().getHot().get(i).getSc());
                        vhTwo.proscoreTvTwo.setText("专业" + mHotFragmentListViewBean.getData().getHot().get(i).getProScore());
                    }else{
                        vhTwo.scTvTwo.setText(mHotFragmentListViewBean.getData().getHot().get(i).getWish() + "人想看");
                        Log.d("啊啊啊啊", "mHotFragmentListViewBean.getData().getHot().get(i).getWish():" + mHotFragmentListViewBean.getData().getHot().get(i).getWish());
                    }
                    vhTwo.scmTvTwo.setText(mHotFragmentListViewBean.getData().getHot().get(i).getScm());
                    vhTwo.showInFoTvTwo.setText(mHotFragmentListViewBean.getData().getHot().get(i).getShowInfo());
                    if (!boxTv.equals("喵，即将上映")){

                        vhTwo.gPTv.setText("购票");
                        Log.e("AAAA", "__---");
                    }else{
                        vhTwo.gPTv.setText("预售");
                        Log.e("AAAA", "__---" + "额");
                    }
                    /**
                     * 点击事件
                     */
                    vhTwo.playIvTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "播放被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });
                    vhTwo.gPTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "购票被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (i > 10 && i < 23){
                    if (view == null){
                        view = LayoutInflater.from(mContext).inflate(R.layout.hot_fragment_adapter_two_item,viewGroup,false);
                        vhTwo = new VHTwo(view);
                        view.setTag(vhTwo);

                    }else{
                       vhTwo = (VHTwo) view.getTag();
                    }
                    vhTwo.nmTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i - 10).getNm());
                    String imgUrl = mHotRefreshBean.getData().getMovies().get(i - 10).getImg();
                    Glide.with(mContext).load(imgUrl).into(vhTwo.imgIvTwo);
                    String verIv = mHotRefreshBean.getData().getMovies().get(i - 10).getVer();
                    if (verIv.contains("3D")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.wo);
                    }else if (verIv.contains("3D") && verIv.contains("IMAX")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.main_movie_hot_3d_imax);
                    }else if (verIv.contains("2D") && verIv.contains("IMAX")){
                        vhTwo.verIvTwo.setImageResource(R.mipmap.main_movie_hot_2d_imax);
                    }
                    int boxTv = mHotRefreshBean.getData().getMovies().get(i - 10).getPreSale();
                    Log.d("啊啊啊啊", boxTv + "");
                    if (boxTv != 1){
                        vhTwo.scTvTwo.setText("观众" + mHotRefreshBean.getData().getMovies().get(i - 10).getSc());

                    }else{
                        vhTwo.scTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i - 10).getWish() + "人想看");
                        //Log.d("啊啊啊啊", "mHotFragmentListViewBean.getData().getHot().get(i).getWish():" + mHotFragmentListViewBean.getData().getHot().get(i).getWish());
                    }
                    vhTwo.scmTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i - 10).getScm());
                    vhTwo.showInFoTvTwo.setText(mHotRefreshBean.getData().getMovies().get(i - 10).getShowInfo());
                    if (boxTv != 1){

                        vhTwo.gPTv.setText("购票");
                        Log.e("AAAA", "__---");
                    }else{
                        vhTwo.gPTv.setText("预售");
                        Log.e("AAAA", "__---" + "额");
                    }
                    /**
                     * 点击事件
                     */
                    vhTwo.playIvTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "播放被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });
                    vhTwo.gPTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "购票被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    return null;
                }

                break;
        }
        return view;
    }


    public class VHOne{
        ImageView imgIvOne,verIvOne,playIvOne;
     TextView nmTvOne,scTvOne,proscoreTvOne,scmTvOne,showInFoTvOne,titleOneTv,titleTwoTv,gPTv;
        public VHOne(View view){
            playIvOne = (ImageView)view.findViewById(R.id.hot_fragment_adapter_one_item_detail_playIv);
            imgIvOne = (ImageView)view.findViewById(R.id.hot_fragment_adapter_one_item_detail_img);
            verIvOne = (ImageView)view.findViewById(R.id.one_ll_ver_iv);
            nmTvOne = (TextView)view.findViewById(R.id.one_ll_nm_tv);
            scTvOne = (TextView)view.findViewById(R.id.two_ll_guanzhong_tv);
            proscoreTvOne = (TextView)view.findViewById(R.id.two_ll_zhuanye_tv);
            scmTvOne = (TextView)view.findViewById(R.id.three_ll_scm_tv);
            showInFoTvOne = (TextView)view.findViewById(R.id.four_ll_showInfo_tv);
            titleOneTv = (TextView)view.findViewById(R.id.hot_fragment_adapter_one_item_piaofang_title_tv);
            titleTwoTv = (TextView)view.findViewById(R.id.hot_fragment_adapter_one_item_title_zhuanti_tv);
            gPTv = (TextView)view.findViewById(R.id.hot_fragment_adapter_one_item_gouPiao_tv);
        }
    }
    public class VHTwo{
        ImageView imgIvTwo,verIvTwo,playIvTwo;
        TextView nmTvTwo,scTvTwo,proscoreTvTwo,scmTvTwo,showInFoTvTwo,gPTv;
        public VHTwo(View view){
            playIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_detail_playIv);
            imgIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_detail_img);
            verIvTwo = (ImageView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_ver_threeD_iv);
            nmTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_nm_tv);
            scTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_guanzhong_tv);
            proscoreTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_two_ll_zhuanye_tv);
            scmTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_three_ll_scm_tv);
            showInFoTvTwo = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_four_ll_showInfo_tv);
            gPTv = (TextView)view.findViewById(R.id.hot_fragment_adapter_two_item_goupiao_tv);
        }

    }
}
