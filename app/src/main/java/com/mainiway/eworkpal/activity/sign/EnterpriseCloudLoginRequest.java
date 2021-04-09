package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：2
 * ===========================================
 */

public class EnterpriseCloudLoginRequest {

    //企业认证校验
    String URL_EnterpriseCloudLogin = "https://www.fxiaoke.com/FHE/EM0AUL/Authorize/EnterpriseCloudLogin/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E..-C5CC6286-6D1E-4123-B47E-EC3BF33705A1&versionName=7.4.1&_postid=-60228812";

    public static String getRequestParameter(String M1) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>2873771300</PostId><Data DataType=\"Json/P\">";
        String param = "\"M1\" : \"" + M1 + "\"";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }


    /*<?xml version="1.0"?>
    <FHE><Tickets/><PostId>2873771300</PostId><Data DataType="Json/P">{
        "M1" : "577382"
    }</Data></FHE>*/

    /*返回结果：
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?><FHE><Data DataType="Json/P">{"M1":{"img":"https://img.fxiaoke.com","file":"https://f.fxiaoke.com","root":"https://www.fxiaoke.com"},"M2":"179584ce-1977-461b-ae25-7faa2e880ea9"}</Data><Result Status="0"/></FHE>*/
}
