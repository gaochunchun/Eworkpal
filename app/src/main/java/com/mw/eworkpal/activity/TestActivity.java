package com.mw.eworkpal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.mw.eworkpal.R;
import com.mw.eworkpal.callback.DialogCallback;
import com.mw.eworkpal.model.ResultResponse;
import com.mw.eworkpal.model.ServerModel;
import com.mw.eworkpal.request.UserRequestManager;
import com.mw.okhttp.OkHttpUtils;
import com.mw.okhttp.utils.OkLogger;

import org.w3c.dom.Text;

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

        //访问网络测试(第一个this用于取消当前请求的Tag必须传，1 用于在下方构造方法区分请求，第二个this用于是否需要显示Dialog)
        UserRequestManager.getInstance().testReturnJsonObject(this,new CallBack<ResultResponse<ServerModel>>(1,this));

        /*UserRequestManager.getInstance().testReturnJsonObject(this,new DialogCallback<ResultResponse<ServerModel>>(this){

            @Override
            public void onSuccess(ResultResponse<ServerModel> responseData, Call call, Response response) {
                        OkLogger.e("requestId --- 1  ",responseData.data.toString());
                        textView.setText("requestId --- 1  "+ responseData.data.toString());
                }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });*/

    }

    @OnClick(R.id.btn2)
    public void btn2OnClick(View View){

        UserRequestManager.getInstance().testReturnJsonArray(this,new CallBack<ResultResponse<List<ServerModel>>>(2,this));

       /* UserRequestManager.getInstance().testReturnJsonArray(this,new DialogCallback<ResultResponse<List<ServerModel>>>(this){

            @Override
            public void onSuccess(ResultResponse<List<ServerModel>> responseData, Call call, Response response) {
                textView.setText(responseData.data.toString());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });*/

    }



    //直接写一个Callback，根据传入的requestID来区分请求
    private class CallBack<T> extends DialogCallback<ResultResponse<T>>{

        private int requestId;  //区分请求的ID

        public CallBack(int requestId,Activity activity) {
            super(activity);
            this.requestId = requestId;
        }

        @Override
        public void onSuccess(ResultResponse<T> responseData, Call call, Response response) {

            switch (requestId) {
                case 1:

                    ServerModel  ss= (ServerModel)responseData.data;


                    OkLogger.e("requestId --- 1  ",responseData.data.toString());
                    //ResultResponse<ServerModel> serverM = (ResultResponse<ServerModel>)responseData.data;

                    textView.setText("requestId --- 1  "+responseData.msg+" ---"+ responseData.code +""+ ss.toString());
                    break;

                case 2:
                    OkLogger.e("requestId --- 2  ",responseData.data.toString());

                    List<ServerModel> sL = (List<ServerModel>)responseData.data;
                    //ResultResponse<List<ServerModel>> serverList = (ResultResponse<List<ServerModel>>)responseData.data;
                    textView.setText("requestId --- 1  "+responseData.msg+" ---"+ responseData.code +""+ sL.toString());
                    break;
            }

        }

        /*@Override
        public void onSuccess(ResultResponse<ServerModel> responseData, Call call, Response response) {
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

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

}

