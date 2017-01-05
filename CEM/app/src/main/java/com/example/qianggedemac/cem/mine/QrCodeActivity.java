package com.example.qianggedemac.cem.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.qianggedemac.cem.R;
import com.example.qianggedemac.cem.baseclass.BaseActivity;
import com.example.qianggedemac.cem.mine.qrcode.FabCreateActivity;
import com.example.qianggedemac.cem.mine.qrcode.FabPicActivity;
import com.example.qianggedemac.cem.mine.qrcode.FabSelfActivity;
import com.example.qianggedemac.cem.mine.qrcode.ImageUtil;
import com.example.qianggedemac.cem.tool.GestureHelper;
import com.example.qianggedemac.cem.view.CanvasView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.zxing.BarcodeFormat;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.CaptureActivityHandler;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.util.Vector;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class QrCodeActivity extends BaseActivity {

    private String imgPath = null;
    private static final int CHOOSE_PIC = 0;

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private Button cancelScanButton;

    //手势退出
    private GestureHelper gestureHelper;


    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    FloatingActionMenu mFloatingActionMenu;
    /**
     * 打开默认二维码扫描界面
     * <p>
     * 打开系统图片选择界面
     * <p>
     * 定制化显示扫描界面
     * <p>
     * 测试生成二维码图片
     */
    private FloatingActionButton fabScan;
    private FloatingActionButton fabPic;
    private FloatingActionButton fabSelf;
    private FloatingActionButton fabCreate;


    private CanvasView mCanvasView;
    private Canvas canvas;
    private Bitmap mBackgroundBitmap;

    @Override
    protected int setLayout() {
        ZXingLibrary.initDisplayOpinion(this);
        return R.layout.activity_qr_code;

    }

    @Override
    protected void initViews() {
        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        fabScan = (FloatingActionButton) findViewById(R.id.menu_item_fab_scan);
        fabPic = (FloatingActionButton) findViewById(R.id.menu_item_fab_pic);
        fabSelf = (FloatingActionButton) findViewById(R.id.menu_item_fab_self);
        fabCreate = (FloatingActionButton) findViewById(R.id.menu_item_fab_create);
        fabScan.setOnClickListener(new ButtonOnClickListener(fabScan.getId()));
        fabPic.setOnClickListener(new ButtonOnClickListener(fabPic.getId()));
        fabSelf.setOnClickListener(new ButtonOnClickListener(fabSelf.getId()));
        fabCreate.setOnClickListener(new ButtonOnClickListener(fabCreate.getId()));

        mCanvasView = (CanvasView) findViewById(R.id.canvas);
//        CameraManager.init(getApplication());

        inactivityTimer = new InactivityTimer(this);

        if (mBackgroundBitmap == null) {
            mBackgroundBitmap = Bitmap.createBitmap(800, 1000, Bitmap.Config.RGB_565);
            canvas = new Canvas(mBackgroundBitmap);
        }

        mCanvasView.draw(canvas);

    }

    @Override
    protected void initDatas() {
        //初始化
        gestureHelper = new GestureHelper(this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                // 退出
                QrCodeActivity.this.finish();
                overridePendingTransition(R.anim.translate_exit_in, R.anim.translate_exit_out);
            }

            @Override
            public void OnFlingRight() {

            }
        });

    }

    // 手势的触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureHelper.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(QrCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(QrCodeActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(QrCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;


    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onClick(viewId);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    private void onClick(int viewId) {
        switch (viewId) {
            case R.id.menu_item_fab_scan:
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                mFloatingActionMenu.close(true);
                break;
            case R.id.menu_item_fab_self:
                intent = new Intent(QrCodeActivity.this, FabSelfActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                mFloatingActionMenu.close(true);
                break;
            default:
                break;
        }

    }


    /**
     * 按钮点击监听
     */
    class ButtonOnClickListener implements View.OnClickListener {

        private int buttonId;

        public ButtonOnClickListener(int buttonId) {
            this.buttonId = buttonId;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.menu_item_fab_pic) {
                mFloatingActionMenu.close(true);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            } else if (v.getId() == R.id.menu_item_fab_create) {
                mFloatingActionMenu.close(true);
                Intent intent = new Intent(QrCodeActivity.this, FabCreateActivity.class);
                startActivity(intent);
            } else {
                mFloatingActionMenu.close(true);
                cameraTask(buttonId);
            }
//            mFloatingActionMenu.close(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        QrCodeActivity.this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (handler != null) {
//            handler.quitSynchronously();
//            handler = null;
//        }
//        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }


    public void clearCanvas(View view) {
        mCanvasView.clearCanvas();
    }

}

