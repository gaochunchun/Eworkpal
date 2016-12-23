package com.mainiway.eworkpal.activity.attendance;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.utils.DateUtils;

import java.util.Date;


/**
 * ===========================================
 * 作    者：zhsh
 * 版    本：1.0
 * 创建日期：2016/12/8.
 * 描    述：考勤界面，关于地图定位的
 * ===========================================
 */

public class AttendanceSignActivity extends BaseTitleActivity implements AMapLocationListener, AMap.OnMapClickListener, LocationSource {

    private MapView mapView;
    private AMap aMap;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //定位的监听对象
    private LocationSource.OnLocationChangedListener onLocationChangedListener;
    private AMapLocationClientOption mOption;
    private boolean isFirst = true;//地图标记mark只定位在第一次定位的位置
    private Boolean firsttouch = true;
    private LatLng mLatLng;//获取到的精度、纬度对象，要传给地图点击事件（地图3D）
    //默认的中心点坐标对象，可改的
    private LatLng defaultLatLng;
    private TextView tv_location, tv_sign, tv_internal_clock, tv_field_personnel_clock, tv_reported_position, tv_date;
    private ImageView iv_center_of_clock;
    private String city, district, street, streetNum;//往"上报位置"界面传递的省信息、城区信息、街道信息、街道门牌号信息


    //根据有效的打卡距离，设置签到按钮的背景颜色和点击状态
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            double distance = data.getDouble("distance");
//            double longitude = data.getDouble("longitude");
//            double latitude = data.getDouble("latitude");
            if (distance > 100) {
                tv_sign.setBackgroundResource(R.drawable.rectangle_27dp_light_gray);
            } else {
                tv_sign.setBackgroundResource(R.drawable.rectangle_27dp_blue_selected);

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_sign);
        setTitle("考勤");
        showBackwardView(true);
        showForwardView(R.mipmap.ic_attendance_record, R.mipmap.ic_attendance_statistics);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        initView();
        initMap();
        initLocation();
        initData();
    }

    private void initView() {
        tv_location = findView(R.id.tv_location);
        tv_location.setOnClickListener(new FastClickListener());

        tv_sign = findView(R.id.tv_sign);
        tv_sign.setOnClickListener(new FastClickListener());
        //默认的中心点坐标（31.163882, 121.40439）
        defaultLatLng = new LatLng(31.163882, 121.40439);

        tv_internal_clock = findView(R.id.tv_internal_clock);
        tv_internal_clock.setOnClickListener(new clickListener());
        tv_internal_clock.setSelected(true);

        tv_field_personnel_clock = findView(R.id.tv_field_personnel_clock);
        tv_field_personnel_clock.setOnClickListener(new clickListener());

        iv_center_of_clock = findView(R.id.iv_center_of_clock);

        tv_reported_position = findView(R.id.tv_reported_position);
        tv_reported_position.setOnClickListener(new FastClickListener());

        tv_date = findView(R.id.tv_date);

    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            //初始化地点显示为上海
            LatLng latLng = new LatLng(31.2379124310, 121.5020200654);
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

            UiSettings settings = aMap.getUiSettings();
            //隐藏缩放按钮
            settings.setZoomControlsEnabled(false);
            //隐藏地图logo
            settings.setLogoBottomMargin(-200);
            settings.setLogoLeftMargin(-200);

        }

    }

    private void initLocation() {
        //设置定位资源
        aMap.setLocationSource(this);
        aMap.setOnMapClickListener(this);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        mLocationClient.setLocationOption(getDefaultOption());

        //启动定位
        mLocationClient.startLocation();

    }


    private void initData() {
        //显示当前时间
        String[] date = DateUtils.getToday().split("-");//yyyy-MM-dd
        String week = DateUtils.getWeek(new Date());
        tv_date.setText(date[0] + "年" + date[1] + "月" + date[2] + "日" + "　　" + week);
    }


    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(90000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        //mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        return mOption;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        if (null != mLocationClient) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());

                Log.i("zhsh", "获取经度===" + aMapLocation.getLongitude());
                Log.i("zhsh", "获取纬度===" + aMapLocation.getLatitude());
                Log.i("zhsh", "地址信息===" + aMapLocation.getAddress());
                city = aMapLocation.getCity();
                district = aMapLocation.getDistrict();
                street = aMapLocation.getStreet();
                streetNum = aMapLocation.getStreetNum();

                if (isFirst) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //绘制点标记
                    aMap.clear();
                    MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .position(latLng)
                            .title(aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet() + aMapLocation.getStreetNum())
                            .draggable(true);
                    Marker marker = aMap.addMarker(markerOption);
                    marker.showInfoWindow();
                    isFirst = false;
                    mLatLng = latLng;
                }
                //计算两点间距离的，默认的中心点坐标（31.163882, 121.40439）
                float distance = AMapUtils.calculateLineDistance(latLng, defaultLatLng);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putDouble("distance", distance);
                bundle.putDouble("longitude", aMapLocation.getLongitude());
                bundle.putDouble("latitude", aMapLocation.getLatitude());
                message.setData(bundle);
                handler.sendMessage(message);
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
            }
        }
    }


    //地图点击事件吗，实现OnMapClickListener
    @Override
    public void onMapClick(LatLng latLng) {
        latLng = mLatLng;
        if (firsttouch) {
            firsttouch = false;
            aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 0, 0)), 1500, new AMap.CancelableCallback() {

                @Override
                public void onFinish() {
                    aMap.moveCamera(CameraUpdateFactory.changeTilt(60));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    aMap.animateCamera(CameraUpdateFactory.changeBearing(90), 2000, null);

                }

                @Override
                public void onCancel() {

                }
            });

        }

    }

    /**
     * 防止快速点击事件
     */
    private class FastClickListener extends OnClickFastListener {

        @Override
        public void onFastClick(View v) {
            switch (v.getId()) {
                case R.id.tv_location://定位
                    isFirst = true;
                    break;
                case R.id.tv_reported_position://上报位置
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("city", city);
                    bundle.putString("district", district);
                    bundle.putString("street", street);
                    bundle.putString("streetNum", streetNum);
                    intent.putExtras(bundle);
                    intent.setClass(AttendanceSignActivity.this, AttendanceReportedPositionActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 点击事件
     */
    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_internal_clock://内勤打卡
                    tv_internal_clock.setSelected(true);
                    tv_field_personnel_clock.setSelected(false);
                    tv_internal_clock.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv_field_personnel_clock.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    iv_center_of_clock.setImageResource(R.mipmap.ic_attendance_home_btn_one);
                    tv_reported_position.setVisibility(View.GONE);
                    break;

                case R.id.tv_field_personnel_clock://外勤打卡
                    tv_internal_clock.setSelected(false);
                    tv_field_personnel_clock.setSelected(true);
                    tv_internal_clock.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_field_personnel_clock.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    iv_center_of_clock.setImageResource(R.mipmap.ic_attendance_home_btn_two);
                    tv_reported_position.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }

    //激活位置接口
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.onLocationChangedListener = onLocationChangedListener;

    }

    //停止定位
    @Override
    public void deactivate() {
        onLocationChangedListener = null;
    }


}
