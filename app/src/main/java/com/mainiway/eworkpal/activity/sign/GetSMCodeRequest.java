package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：getSMCode
 * ===========================================
 */

public class GetSMCodeRequest {

    //获取短信验证码
    String URL_BuildValidateCode = "https://www.fxiaoke.com/FHE/EM0AUL/Authorize/BuildValidateCode/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E..-3F61E167-6390-4906-9100-C2E51A5AFD1B&versionName=7.4.1&_postid=-152509192";

    public static String getRequestParameter(String phoneNum) {
              String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>188826340</PostId><Data DataType=\"Json/P\">";
        String param = "{\"M2\" : \"" + phoneNum + "\",\"M1\": \"+86\"}";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }

    /*返回：
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?><FHE><Data DataType="Json/P">{"isOK":true}</Data><Result Status="0"/></FHE>*/

}
