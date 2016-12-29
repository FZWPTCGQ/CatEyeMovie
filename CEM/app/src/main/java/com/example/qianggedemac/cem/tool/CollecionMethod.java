package com.example.qianggedemac.cem.tool;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by qianggedemac on 16/12/29.
 */
@Table("Collection")
public class CollecionMethod {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    int id;
    String nm,cat,rt,img;

    public CollecionMethod(String nm, String cat, String rt, String img) {
        this.nm = nm;
        this.cat = cat;
        this.rt = rt;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
