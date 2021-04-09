package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：getDailyInfo 信息
 * ===========================================
 */

public class GetDailyInfoRequest {

    String URL_getDailyInfo = "https://www.fxiaoke.com/FHE/EM1AKaoQinV2/dataAppService/getDailyInfo/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E.577382.1371-45B54624-4700-4F02-951E-2D23463E836F&versionName=7.4.1&_postid=-2011031941";


    public static String getRequestParameter(String date) {
        /*StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3950243730</PostId><Data DataType=\"Json/P\">");
        sb.append("{\"M10\" : \"").append(date).append("\",\"M11\": 1,\"M4\" : 1}");
        sb.append("</Data></FHE>");
        return sb.toString();*/

        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>901258484</PostId><Data DataType=\"Json/P\">";
        String param = "{\"M10\" : \"" + date + "\",\"M11\": 1,\"M4\" :\"1\"}";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }


    /*<?xml version="1.0"?>
           <FHE><Tickets/>
           <PostId>3950243730</PostId>
           <Data DataType="Json/P">{
               "M10" : "2021-04-03",
               "M11" : 1,
               "M4" : 1
               }</Data>
           </FHE>
           */
    //public String xmlSacme = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3950243730</PostId><Data DataType=\"Json/P\">";
    //public T requestParameter;
    //public String endXML = "</Data></FHE>";

    /*public String getRequestParameter(T object){
        String  objectJson = GsonConvertUtil.toJson(object);
        return xmlSacme + objectJson + endXML;
    }*/
}
