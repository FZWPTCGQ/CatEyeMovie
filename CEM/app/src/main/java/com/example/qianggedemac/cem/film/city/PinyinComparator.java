package com.example.qianggedemac.cem.film.city;

import java.util.Comparator;

/**
 * Created by qianggedemac on 17/1/6.
 * 拼音比较器
 */

/**
 * Comparable 和 Comparator 都是用来实现集合中的排序的
 * 只是Comparable是在集合内部定义的方法实现的排序
 * Comparator是在集合外部实现的排序
 * 所以,如果实现排序,就需要在集合外定义Comparator接口的方法Compare()或在集合内实现Comparable
 * 接口的方法compareTo();
 * Comparable是一个对象本身就已经支持自比较所需要实现的接口
 */

public class PinyinComparator implements Comparator<CityBean.CtsBean>{
    @Override
    public int compare(CityBean.CtsBean ctsBean, CityBean.CtsBean t1) {
        return ctsBean.getPy().compareTo(t1.getPy());
    }
}
