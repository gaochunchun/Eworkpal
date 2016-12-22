package com.mainiway.eworkpal.manager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-12-22.
 * 描    述：SharedPreferences通用数据管理类
 * ===========================================
 */

public class ConfigManager{

    private static final String TAG = ConfigManager.class.getSimpleName();

    //保存在手机里面的文件名
    public static final String PREF_FILE_NAME = "share_data";

    private SharedPreferences mPreferences;
    private static ConfigManager sInstance;

    private ConfigManager(Context mApplicationContext) {
        mPreferences = mApplicationContext.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
    }


    public synchronized static ConfigManager getInstance() {

        if (sInstance == null) {
            Log.e(TAG,"ConfigManager.initiate method not called in the application.");
        } // else ignored.
        return sInstance;
    }


    /**
     * @param aApplicationContext Please pass an "application context", not an
     *            "activity context".
     * @return
     */
    public static void initialize(Context aApplicationContext) {
        sInstance = new ConfigManager(aApplicationContext);
    }


    /**
     * 保存数据的方法
     * 需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object){

        SharedPreferences.Editor editor = mPreferences.edit();

        if (object instanceof String)
        {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer)
        {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float)
        {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long)
        {
            editor.putLong(key, (Long) object);
        } else
        {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 得到保存数据的方法
     * 根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(String key, Object defaultObject){

        if (defaultObject instanceof String)    //此处String的默认值不可以为null
        {
            return mPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return mPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return mPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return mPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return mPreferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }


    /**
     * 移除某个key值已经对应的值
     * @param context
     * @param key
     */
    public void remove(Context context, String key){

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 清除所有数据
     * @param context
     */
    public void clear(Context context){

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 查询某个key是否已经存在
     * @param context
     * @param key
     * @return
     */
    public boolean contains(Context context, String key){
        return mPreferences.contains(key);
    }


    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public Map<String, ?> getAll(Context context){
        return mPreferences.getAll();
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat{

        /*对SharedPreference的使用做了建议的封装，对外公布出put，get，remove，clear等等方法；
        注意一点，里面所有的commit操作使用了SharedPreferencesCompat.apply进行了替代，
        目的是尽可能的使用apply代替commit，首先说下为什么，因为commit方法是同步的，
        并且我们很多时候commit操作都是UI线程中，毕竟是IO操作，尽可能异步；所以我们使用
   apply进行替代，apply异步的进行写入；但是apply相当于commit来说是new API呢，
       为了更好的兼容，我们做了适配；SharedPreferencesCompat也可以给大家创建兼容类提供一定的参考*/

        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod(){
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {

                Log.e(TAG,"ConfigManager."+e.getMessage());
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try{
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e){
                Log.e(TAG,"apply."+e.getMessage());
            } catch (IllegalAccessException e) {
                Log.e(TAG,"apply."+e.getMessage());
            } catch (InvocationTargetException e) {
                Log.e(TAG,"apply."+e.getMessage());
            }
            editor.commit();
        }
    }
}
