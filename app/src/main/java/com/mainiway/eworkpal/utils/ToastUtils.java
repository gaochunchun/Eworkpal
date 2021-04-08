package com.mainiway.eworkpal.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mainiway.eworkpal.AppAplication;
import com.mainiway.eworkpal.R;


public class ToastUtils {

    /**
     * 长时间的Toast提示
     * @param msg
     */
    public static void showToastLong(CharSequence msg){
        Toast.makeText(AppAplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 短时间的Toast提示
     * @param msg
     */
    public static void showToastShort(CharSequence msg){
        Toast.makeText(AppAplication.getContext(),msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * 提示居中的Toast
     * @param str
     */
    public static void showToastCenter(String str) {
        Toast toast = new Toast(AppAplication.getContext());
        toast = Toast.makeText(AppAplication.getContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);//设置居中
        toast.show();
    }


    /**
     * 自定义ProgressDialog
     * @param context
     * @return
     */
    /*public static Dialog createLoadingDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_customprogress_view, null);        // 得到加载view

        ImageView imageView = (ImageView) v.findViewById(R.id.customprogress_loading_iv);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);     //创建自定义样式dialog

        loadingDialog.setCancelable(false);         //不可以用"返回键"取消
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return loadingDialog;

    }*/



    /**
     * 弹出框
     */
    /*public static Dialog createDialog(int layoutId,Context context) {

        final Dialog dialog = new Dialog(context, R.style.bottom_dialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_task_details_dialog, null);

        View rl_task_details_top = (View) dialogView.findViewById(R.id.rl_task_details_top);

        TextView tv_task_details_dialog_edit_task = (TextView) dialogView.findViewById(R.id.task_completion_text);
        TextView tv_task_details_dialog_task_completion = (TextView) dialogView.findViewById(R.id.reminder_text);

        tv_task_details_dialog_edit_task.setText("回复");
        tv_task_details_dialog_task_completion.setText("删除");

        TextView tv_task_details_dialog_reminders_reminder = (TextView) dialogView.findViewById(R.id.edit_task_text);
        tv_task_details_dialog_reminders_reminder.setVisibility(View.GONE);

        View view_three = dialogView.findViewById(R.id.view_three);
        view_three.setVisibility(View.GONE);

        //tv_task_details_dialog_edit_task.setOnClickListener(context);
        //tv_task_details_dialog_task_completion.setOnClickListener(context);

        dialog.setContentView(dialogView);
        dialog.show();

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        dialog.getWindow().setAttributes(params);

        rl_task_details_top.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dialog.dismiss();
                return false;
            }
        });

        return dialog;
    }*/



}
