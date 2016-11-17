package com.mainiway.eworkpal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mainiway.eworkpal.R;
import com.mainiway.eworkpal.callback.DialogCallback;
import com.mainiway.eworkpal.model.A;
import com.mainiway.eworkpal.model.BaseResponse;
import com.mainiway.eworkpal.request.UserRequestManager;
import com.mainiway.okhttp.OkHttpUtils;
import com.mainiway.okhttp.utils.OkLogger;

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

        //访问网络测试(第一个this用于取消当前请求的Tag必须传，1 用于在下方构造方法区分请求，第二个this用于是否需要显示Dialog)
        //UserRequestManager.getInstance().testReturnJsonObject(this,new CallBack<BaseResponse<A>>(1,this));

        UserRequestManager.getInstance().testReturnJsonObject(this,new DialogCallback<BaseResponse<A>>(this){
            @Override
            public void onSuccess(BaseResponse<A> responseData, Call call, Response response) {
                        OkLogger.e("requestId --- 1  ",responseData.data.toString()+" - "+responseData.message.get(0).msg);
                        textView.setText("requestId --- 1  "+ responseData.data.email);
                }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });

    }

    @OnClick(R.id.btn2)
    public void btn2OnClick(View View){

        //UserRequestManager.getInstance().testReturnJsonArray(this,new CallBack<BaseResponse<List<ServerModel>>>(2,this));

       /* UserRequestManager.getInstance().testReturnJsonArray(this,new DialogCallback<BaseResponse<List<ServerModel>>>(this){

            @Override
            public void onSuccess(BaseResponse<List<ServerModel>> responseData, Call call, Response response) {
                textView.setText(responseData.data.toString());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });*/

    }


    //直接写一个Callback，根据传入的requestID来区分请求
    private class CallBack<T> extends DialogCallback<BaseResponse<T>>{

        private int requestId;  //区分请求的ID

        public CallBack(int requestId,Activity activity) {
            super(activity);
            this.requestId = requestId;
        }

        @Override
        public void onSuccess(BaseResponse<T> responseData, Call call, Response response) {

            switch (requestId) {
                case 1:
                    //A t = (BaseResponse<A>)responseData.data;

                    //ServerModel  ss= (ServerModel)responseData.data;

                    OkLogger.e("----------------->",responseData.successed+"  -- "+responseData.status+"--"+responseData.message.get(0).msg);
                    OkLogger.e("----------------->",responseData.data.toString());
                    //OkLogger.e("----------------->",t.+ " -- "+t.address);

                    //OkLogger.e("----------------->",responseData.data.getClass().toString());

                    //A baseResponse = (A) responseData.data;

                    //A a = (A)responseData.data;
                    //textView.setText("requestId" + a.name);

                    break;

                case 2:
                    //OkLogger.e("requestId --- 2  ",responseData.data.toString());

                    //List<ServerModel> sL = (List<ServerModel>)responseData.data;
                    //BaseResponse<List<ServerModel>> serverList = (BaseResponse<List<ServerModel>>)responseData.data;
                    //textView.setText("requestId --- 1  "+responseData.msg+" ---"+ responseData.code +""+ sL.toString());
                    break;
            }

        }

        /*@Override
        public void onSuccess(BaseResponse<ServerModel> responseData, Call call, Response response) {
            switch (requestId) {
                case 1:
                    OkLogger.e("requestId --- 1  ",responseData.data.toString());
                    textView.setText("requestId --- 1  "+ responseData.data.toString());
                    break;

                case 2:
                    OkLogger.e("requestId --- 2  ",responseData.data.toString());
                    textView.setText("");
                    textView.setText("requestId --- 2  " + responseData.data.toString());
                    break;
            }

        }*/

        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);

            OkLogger.e("----------------->",e.getMessage());

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

}

