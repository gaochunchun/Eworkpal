package com.mainiway.eworkpal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.listener.OnClickFastListener;
import com.mainiway.eworkpal.model.A;
import com.mainiway.eworkpal.base.BaseResponse;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.okhttp.OkHttpUtils;
import com.mainiway.okhttp.utils.OkLogger;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class TestActivity extends BaseTitleActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network);

        setTitle("测试界面标题");
        showBackwardView(true);
        textView = (TextView)findView(R.id.tvcontent);

        findView(R.id.btn1).setOnClickListener(new OnClickFastOrIsNetworkListener());
        findView(R.id.btn2).setOnClickListener(new OnClickFastOrIsNetworkListener());
    }


    private class OnClickFastOrIsNetworkListener extends  OnClickFastListener {

        @Override
        public void onFastClick(View v) {

            switch (v.getId()) {

                case R.id.btn1:
                    testJsonObject();
                    break;

                case R.id.btn2:
                    testJsonArray();
                    break;

            }
        }
    }


    private void testJsonObject(){
        //访问网络测试(第一个this用于取消当前请求的Tag必须传，第二个this用于是否需要显示Dialog)
        UserRequestManager.getInstance().testReturnJsonObject(TestActivity.this,new DialogCallback<BaseResponse<A>>(TestActivity.this){
            @Override
            public void onSuccess(BaseResponse<A> responseData, Call call, Response response) {
                textView.setText(responseData.successed + " -- "+ responseData.status+ " -- "+responseData.message.get(0).msg+ " -- "+responseData.data);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.i("onError",e.getMessage());
            }
        });
    }


    private void testJsonArray(){
        UserRequestManager.getInstance().testReturnJsonArray(this,new DialogCallback<BaseResponse<List<A>>>(this){
            @Override
            public void onSuccess(BaseResponse<List<A>> responseData, Call call, Response response) {
                textView.setText("size:"+responseData.data.size()+" -- "+responseData.successed + " -- "+ responseData.status+ " -- "+responseData.message.get(0).msg+ " -- "+responseData.data);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                OkLogger.i("onError",e.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

}

