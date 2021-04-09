package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：updateCheckins
 * ===========================================
 */

public class UpdateCheckinsRequest {

    String URL_updateCheckins = "https://www.fxiaoke.com/FHE/EM1AKaoQinV2/dataAppService/updateCheckins/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E.577382.1371-543433D5-94E1-4134-A5B0-A798056969FF&versionName=7.4.1&_postid=1479881945";

    //M21：重签次数？
    public static String getRequestParameter(String date) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3950243730</PostId><Data DataType=\"Json/P\">";
        String param = "<?xml version=\"1.0\"?>\n" +
                "<FHE><Tickets/><PostId>3740712623</PostId><Data DataType=\"Json/P\">{\n" +
                "  \"M16\" : \"28:b4:48:2e:26:33\",\n" +
                "  \"M11\" : 1,\n" +
                "  \"M19\" : \"+" + date + "+\",\n" +
                "  \"M20\" : \"\",\n" +
                "  \"M14\" : \"\",\n" +
                "  \"M25\" : 0,\n" +
                "  \"M17\" : 0,\n" +
                "  \"M12\" : 31.099355697631836,\n" +
                "  \"M15\" : \"ccn_office\",\n" +
                "  \"M10\" : \"6068639439c47a0001f25724\",\n" +
                "  \"M21\" : 1,\n" +
                "  \"M18\" : \"\",\n" +
                "  \"M13\" : 121.50933837890625\n" +
                "}</Data></FHE>\n";
        String xmlEnd = "</Data></FHE>";

        return xmlTop + param + xmlEnd;
    }


    /*返回：
    <?xml version="1.0" encoding="UTF-8"?>
<FHE><Result Status="0" Code="0" /><UserInfo EID="1371" EA="577382" /><Data DataType="Json/P">{"M1":1,"M10":{"M1":"2021-04-08","M2":1,"M3":[{"M1":"08:30","M2":"17:30","M3":0,"M4":1,"M5":1,"M6":0,"M7":"606e6a5939c47a0001063b06","M8":"606f081986691f0001367ee4","M9":0}],"M4":28800000},"M11":[{"M1":"606e6a5939c47a0001063b06","M10":0,"M11":0,"M12":0,"M13":0,"M14":0,"M17":"","M18":"","M19":1,"M2":0,"M20":0,"M21":0,"M22":true,"M23":0,"M25":1,"M26":0,"M27":0,"M28":0,"M29":0,"M3":1617848880000,"M4":"2021-04-08","M5":121.50660705566406,"M6":31.096437454223633,"M7":"上海中商网络股份有限公司","M8":"","M9":""},{"M1":"606f081986691f0001367ee4","M10":0,"M11":0,"M12":0,"M13":0,"M14":0,"M17":"","M18":"","M19":1,"M2":1,"M20":0,"M21":0,"M22":true,"M23":1,"M25":0,"M26":0,"M27":0,"M28":0,"M29":0,"M3":1617889320000,"M4":"2021-04-08","M5":121.50933837890625,"M6":31.099355697631836,"M7":"上海市闵行区西环南路","M8":"CCN-OFFICE","M9":"28:b4:48:2e:26:33"}],"M12":0,"M13":[],"M14":true,"M15":36840000,"M2":"光阴似箭,日月如梭","M26":0,"M3":1617889344104,"M30":0,"M34":0}</Data></FHE>
*/
}
