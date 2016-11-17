package com.mw.eworkpal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mw.eworkpal.R;
import com.mw.eworkpal.callback.DialogCallback;
import com.mw.eworkpal.model.A;
import com.mw.eworkpal.model.BaseResponse;
import com.mw.eworkpal.request.UserRequestManager;
import com.mw.okhttp.OkHttpUtils;
import com.mw.okhttp.utils.OkLogger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;


public class TestActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.tvContent) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);// --- 初始化ButterKnife

    }

    @OnClick(R.id.btn1)
    public void btn1OnClick(View view){

        //访问网络测试(第一个this用于取消当前请求的Tag必须传，第二个this用于是否需要显示Dialog)

        UserRequestManager.getInstance().testReturnJsonObject(this,new DialogCallback<BaseResponse<A>>(this){
            @Override
            public void onSuccess(BaseResponse<A> responseData, Call call, Response response) {
                        OkLogger.e("requestId --- 1  ",responseData.data.toString());
                textView.setText(responseData.successed + " -- "+ responseData.status+ " -- "+responseData.message.get(0).msg+ " -- "+responseData.data);
                }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

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

