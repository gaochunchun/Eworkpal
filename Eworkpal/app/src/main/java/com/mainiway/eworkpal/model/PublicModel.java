package com.mainiway.eworkpal.model;

import java.io.Serializable;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-12-30.
 *
 * 描    述：后端某些接口与WEB端共用，故格式进行统一，
 * 服务器端返回的data节点下可能存在"" , {} ,[] 等情况，
 * 移动端为了处理解析返回的数据，故根据后端返回可能需要
 * 在调用接口传递解析类型时传递 String , PublicModel ,List<PublicModel>
 * ===========================================
 */
public class PublicModel implements Serializable {

    private static final long serialVersionUID = 8917630089144730645L;

}
