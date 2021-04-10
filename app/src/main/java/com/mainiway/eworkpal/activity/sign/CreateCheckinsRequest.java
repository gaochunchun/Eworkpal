package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：createCheckins
 * ===========================================
 */

public class CreateCheckinsRequest {

    public static String URL_createCheckins = "https://www.fxiaoke.com/FHE/EM1AKaoQinV2/dataAppService/createCheckins/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E.577382.1371-3FB330FB-5E7A-4342-85AE-78593F1B329C&versionName=7.4.1&_postid=-933325967";

    //121.515632,31.102277
    public static String getRequestParameter(String date) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3950243730</PostId><Data DataType=\"Json/P\">";
        String param = "{\n" +
                "  \"M16\" : \"ba:63:4d:48:e0:2b\",\n" +
                "  \"M11\" : 1,\n" +
                "  \"M19\" : \"" + date + "\",\n" +
                "  \"M20\" : \"\",\n" +
                "  \"M14\" : \"\",\n" +
                "  \"M25\" : 0,\n" +
                "  \"M17\" : 0,\n" +
                "  \"M12\" : 31.099376678466797,\n" +
                "  \"M15\" : \"ccn_office\",\n" +
                "  \"M10\" : \"6068639439c47a0001f25724\",\n" +
                "  \"M21\" : 0,\n" +
                "  \"M18\" : \"\",\n" +
                "  \"M13\" : 121.50934600830078\n" +
                "}";
        String xmlEnd = "</Data></FHE>";

        return xmlTop + param + xmlEnd;
    }
}
