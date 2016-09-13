package com.gkzxhn.ywt_gkzx.regist;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gkzxhn.ywt_gkzx.R;
import com.gkzxhn.ywt_gkzx.utils.BaseActivity;
import com.gkzxhn.ywt_gkzx.utils.ImageTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ZengWenZhi on 2016/8/13 0013.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private AlertDialog agreement_dialog;
    private AlertView alertView;
    private int imageClick;
    private static final int WIDTHSCALE = 12;

    private static final int HEIGHTSCALE = 17;
    private static final int SCALE = 9;// 照片缩小比例
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView headImage;
    private String uploadFile1 = "";
    private Bitmap bitMapTwo;
    private String uploadFile2 = "";
    private static final int CROP_SMALL_PICTURE = 2;
    private Bitmap bitMapOne;
    private Bitmap bitMapHead;
    private Drawable drawableOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_agreement:
                showSoftProtocolDialog();
                break;
            case R.id.regist_headimage:
                showPhotoPicker(this, false);
                imageClick = 0;
                break;
            case R.id.regist_imageone:
                showPhotoPicker(this, true);
                imageClick = 1;
                break;
            case R.id.regist_imagetwo:
                showPhotoPicker(this, true);
                imageClick = 2;
                break;


        }
    }

    //显示狱务通使用协议
    private void showSoftProtocolDialog() {
        AlertDialog.Builder agreement_builder = new AlertDialog.Builder(this);
        View agreement_view = View.inflate(this, R.layout.software_use_agreement, null);
        LinearLayout ll_explain_content = (LinearLayout) agreement_view.findViewById(R.id.agreement_content);
        agreement_dialog = agreement_builder.create();
        agreement_dialog.setCancelable(true);//允许返回键返回
        agreement_builder.setView(agreement_view);
        agreement_builder.show();

        //给协议文本区域设置点击事件，点击退出协议浏览
        ll_explain_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreement_dialog.dismiss();
                Toast.makeText(RegisterActivity.this, "666", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //采用AlertView来选择是拍摄还是从相册中上传照片
    private void showPhotoPicker(Context context, boolean isTwo) {
        if (isTwo) {
            alertView = new AlertView("上传身份证照片", null, "取消", null, new String[]{"拍照", "从相册中选择"},
                    context, AlertView.Style.ActionSheet, new OnItemClickListener() {
                public void onItemClick(Object o, int position) {
                    if (position == 0) {
                        takePhotoFromCamera();// 拍照
                    } else if (position == 1) {
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, 1);
                    } else if (position == -1) {// 取消
                        alertView.dismiss();
                    }
                }
            });
            alertView.show();
        } else {
            alertView = new AlertView("上传头像", null, "取消", null, new String[]{"拍照"},
                    context, AlertView.Style.ActionSheet, new OnItemClickListener() {
                @Override
                public void onItemClick(Object o, int position) {
                    if (position == 0) {
                        takePhotoFromCamera();// 拍照
                    } else if (position == -1) {
                        alertView.dismiss();
                    }
                }
            });
            alertView.show();
        }
    }

    /**
     * 相机拍照
     */
    private void takePhotoFromCamera() {
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "image.jpg"));
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, 0);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    // 将处理过的图片显示在界面上，并保存到本地
                    if (imageClick == 1) {
                        setFirstIDPhoto(); // 设置第一张身份证照片
                    } else if (imageClick == 2) {
                        setSecondIDPhoto(); // 设置第二张身份证照片
                    } else if (imageClick == 0) {
                        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        cropImageUri(imageUri, 300, 300, CROP_SMALL_PICTURE);// 裁剪
                    }
                    break;
                case 1:
                    ContentResolver resolver = getContentResolver();
                    // 照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {// 使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            setPhotoFromAlbum(photo);
                        }
                    } catch (FileNotFoundException e) {
                        toastLong("文件不存在");
                    } catch (IOException e) {
                        toastLong("读取文件出错");
                    }
                    break;
                case CROP_SMALL_PICTURE:
                    // 将保存在本地的图片取出并缩小后显示在界面上
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
                    bitMapHead = bitmap;
                    headImage.setImageBitmap(bitMapHead);
                    toastLong("你好。。。");
                    break;
            }
        }
    }

    /**
     * 设置第一张身份证照片
     * Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()=/storage/emulated/0/DCIM
     * 相机的存储路径的获取方法是Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
     */
    private void setFirstIDPhoto() {
        // 将保存在本地的图片取出并缩小后显示在界面上
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
        bitMapOne = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / WIDTHSCALE, bitmap.getHeight() / HEIGHTSCALE);
        // 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
        bitmap.recycle();
        imageOne.setImageBitmap(bitMapOne);
        ImageTools.savePhotoToSDCard(bitMapOne, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + "/Camera", String.valueOf(System.currentTimeMillis()));
        uploadFile1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + "/Camera/" + String.valueOf(System.currentTimeMillis()) + ".png";
    }

    /**
     * 设置第二张身份证照片
     */
    private void setSecondIDPhoto() {
        // 将保存在本地的图片取出并缩小后显示在界面上
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
        bitMapTwo = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / WIDTHSCALE, bitmap.getHeight() / HEIGHTSCALE);
        // 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
        bitmap.recycle();
        imageTwo.setImageBitmap(bitMapTwo);
        ImageTools.savePhotoToSDCard(bitMapTwo, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + "/Camera", String.valueOf(System.currentTimeMillis()));
        uploadFile2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + "/Camera/" + String.valueOf(System.currentTimeMillis()) + ".png";
    }

    /**
     * 裁剪照片
     *
     * @param uri         image uri
     * @param outputX     default image width
     * @param outputY     default image height
     * @param requestCode
     */
    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 设置从相册获取的图片
     *
     * @param photo
     */
    private void setPhotoFromAlbum(Bitmap photo) {
        if (imageClick == 1) {
            // 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
            bitMapOne = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
            // 释放原始图片占用的内存，防止out of memory异常发生
            photo.recycle();
            imageOne.setImageBitmap(bitMapOne);
            uploadFile1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .getAbsolutePath() + "/Camera/" + "emptyphoto.png";
        } else if (imageClick == 2) {
            bitMapTwo = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
            // 释放原始图片占用的内存，防止out of memory异常发生
            photo.recycle();
            imageTwo.setImageBitmap(bitMapTwo);
            uploadFile2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .getAbsolutePath() + "/Camera/" + "emptyphoto.png";
        }
    }


    private void initView() {
        imageOne = (ImageView) findViewById(R.id.regist_imageone);
        imageOne.setOnClickListener(this);

        imageTwo = (ImageView) findViewById(R.id.regist_imagetwo);
        imageTwo.setOnClickListener(this);
        headImage = (ImageView) findViewById(R.id.regist_headimage);
        headImage.setOnClickListener(this);

        ImageView imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(this);
        TextView agreement = (TextView) findViewById(R.id.tv_agreement);
        agreement.setOnClickListener(this);
    }
}
