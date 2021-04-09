package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：3
 * ===========================================
 */

public class LoginByTokenRequest {

    String URL_LoginByToken = "https://www.fxiaoke.com/FHE/EM0ACUL/CloudAuthorize/LoginByToken/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E..-444D37D8-2D8A-4B06-A3AC-91FC69BBAD31&versionName=7.4.1&_postid=-926290185";

    public static String getRequestParameter(String imei, String M2) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>315425645</PostId><Data DataType=\"Json/P\">";
        String param = "{\n" +
                "        \"M3\" : \"iPhone10,2\",\n" +
                "        \"M2\" : \"" + imei + "\",\n" +
                "        \"M1\" : \"" + M2 + "\"\n" +
                "    }";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }

    /*入参：
    Cookie: LoginId=LOGIN_ID_e2e5583f-b64d-4662-9ff9-487038f0fb72
    <?xml version="1.0"?>
<FHE><Tickets/><PostId>315425645</PostId><Data DataType="Json/P">{
        "M3" : "iPhone10,2",
                "M2" : "90646AC1-2641-41A2-BFF6-7D4D0A61F256",
                "M1" : "179584ce-1977-461b-ae25-7faa2e880ea9"
    }</Data></FHE>

    返回：
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?><FHE><Data DataType="Json/P">{"M1":1,"M2":"+86","M3":"15717176068"}</Data><Result Status="0"/></FHE>
*/
}
