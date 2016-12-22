package com.mainiway.eworkpal.activity.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.adapter.ImagePickerAdapter;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.DateUtils;
import com.mainiway.eworkpal.cache.GlideImageLoader;
import com.mainiway.imagepicker.ImagePicker;
import com.mainiway.imagepicker.model.ImageItem;
import com.mainiway.imagepicker.ui.ImageGridActivity;
import com.mainiway.imagepicker.ui.ImagePreviewDelActivity;
import com.mainiway.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Date;

/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/13.
 * 描    述：上报位置
 * ===========================================
 */

public class ReportedPositionActivity extends BaseTitleActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数


    private TextView tv_position, tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_reported_position);
        setTitle("上报位置");
        showBackwardView(true);
        initView();
        initData();


        //放到Application oncreate执行
        initImagePicker();
        initWidget();

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        }
    }


    private void initView() {
        tv_position = findView(R.id.tv_position);
        tv_date = findView(R.id.tv_date);

        findView(R.id.tv_submit).setOnClickListener(new FastClickListener());
    }

    private void initData() {
        //显示当前位置信息
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            tv_position.setText(bundle.getString("city") + bundle.getString("district") + bundle.getString("street") + bundle.getString("streetNum"));
        }
        //显示当前时间
        String[] date = DateUtils.getToday().split("-");//yyyy-MM-dd
        String[] time = DateUtils.getTime().split(":");//HH:mm:ss
        String week = DateUtils.getWeek(new Date());
        tv_date.setText(date[0] + "年" + date[1] + "月" + date[2] + "日" + "　　" + week + "　　" + time[0] + ":" + time[1]);
    }

    /**
     * 防止快速点击事件
     */
    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit://提交
                    startActivity(new Intent(ReportedPositionActivity.this, AttendanceRecordActivity.class));
                    break;

            }
        }
    }
}
