package com.example.qianggedemac.cem.mine.login;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.qianggedemac.cem.tool.myapp.MyApp;

/**
 * Created by qianggedemac on 17/1/5.
 */
public class LoginTool {
    //Mob
    public static final String APP_KEY = "1a5a1df96be00";
    public static final String APP_SECRETE = "8f4bf2db1a46a61b53346a0871953738";
    //Bmob
    public static final String APP_ID = "11b8881cca97f0dec45c89a94c149894";
    //头像
    public static final String icon[] = {
            "http://file.popoho.com/wmpic/2016061352/1461908702_JQUhdVyQ.jpg",
            "http://www.qq1234.org/uploads/allimg/150706/8_150706160053_6.jpg",
            "http://v1.qzone.cc/avatar/201405/10/19/54/536e136f35188055.jpg!200x200.jpg",
            "http://img5.duitang.com/uploads/item/201411/01/20141101235509_NVNer.jpeg",
            "http://v1.qzone.cc/avatar/201407/28/19/57/53d63ab74671d732.jpg!200x200.jpg",
            "http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110420035680.jpg",
            "http://www.poluoluo.com/qq/UploadFiles_7828/201610/2016102120092169.png"
    };

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNum
     */
    public static boolean judgePhoneNum(String phoneNum) {
        if (isMatchLength(phoneNum, 11) && isMobileNO(phoneNum)) {
            return true;
        } else {
            Toast.makeText(MyApp.getContext(), "手机号码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNum) {
        /*
        * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
        * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNum)) {
            return false;
        } else {
            return mobileNum.matches(telRegex);
        }
    }

}
