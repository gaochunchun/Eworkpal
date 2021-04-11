package com.ccn.SmartPDA.adapter;

/**
 * ================================================
 * 描    述：返回值的适配器
 * 修订历史：
 * ================================================
 */
public interface CallAdapter<T, R> {

    /** call执行的代理方法 */
    R adapt(Call<T> call, AdapterParam param);
}
