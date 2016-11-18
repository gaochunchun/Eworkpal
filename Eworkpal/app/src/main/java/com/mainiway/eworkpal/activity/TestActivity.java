package com.mainiway.eworkpal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.base.BaseTitleActivity;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.model.A;
import com.mainiway.eworkpal.model.BaseResponse;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.okhttp.OkHttpUtils;
import com.mainiway.okhttp.utils.OkLogger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;


public class TestActivity extends BaseTitleActivity {


    @Bind(R.id.tvContent) TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this); // --- 初始化ButterKnife
        setTitle("测试界面");
    }

    @OnClick(R.id.btn1)
    public void btn1OnClick(View view){

        //访问网络测试(第一个this用于取消当前请求的Tag必须传，第二个this用于是否需要显示Dialog)
        UserRequestManager.getInstance().testReturnJsonObject(this,new DialogCallback<BaseResponse<A>>(this){
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

    @OnClick(R.id.btn2)
    public void btn2OnClick(View View){

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

