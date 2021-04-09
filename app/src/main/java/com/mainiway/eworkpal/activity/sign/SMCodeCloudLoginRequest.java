package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：SMCodeCloudLogin 1
 * ===========================================
 */

public class SMCodeCloudLoginRequest {

    //验证码登录成功
    String URL_BuildValidateCode = "https://www.fxiaoke.com/FHE/EM0AUL/Authorize/SMCodeCloudLogin/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E..-44D2150A-9B23-4476-90FC-111A519FCDF5&versionName=7.4.1&_postid=-2142465757";

    public static String getRequestParameter(String phoneNum, String smCode) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>229067111</PostId><Data DataType=\"Json/P\">";
        String param = "{\n" +
                "            \"M7\" : \"iPhone10,2\",\n" +
                "            \"M3\" : \"" + smCode + "\",\n" +
                "            \"M6\" : \"90646AC1-2641-41A2-BFF6-7D4D0A61F256\",\n" +
                "            \"M2\" : \"+" + phoneNum + "+\",\n" +
                "            \"M1\" : \"+86\"\n" +
                "        }";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }


    /*入参：<?xml version="1.0"?>
<FHE><Tickets/><PostId>229067111</PostId><Data DataType="Json/P">{
                "M7" : "iPhone10,2",
                "M3" : "867253",
                "M6" : "90646AC1-2641-41A2-BFF6-7D4D0A61F256",
                "M2" : "15717176068",
                "M1" : "+86"
    }</Data></FHE>

    返回：<?xml version="1.0" encoding="UTF-8" standalone="yes"?><FHE><Data DataType="Json/P">{"M1":[{"M1":"577382","M2":"上海中商网络股份有限公司","M3":1,"M4":false}]}</Data><Result Status="0"/></FHE>*/


}
