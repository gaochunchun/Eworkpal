package com.mainiway.eworkpal.activity.sign;

/**
 * ===========================================
 * 创 建 者：gao_chun
 * 版    本：1.0
 * 创建日期：2021-04-09.
 * 描    述：4
 * ===========================================
 */

public class GetUserInitialDataRequest {

    String URL_GetUserInitialData = "https://www.fxiaoke.com/FHE/EM1ALGN/General/GetUserInitialData/iOS.741001?_vn=741001&_ov=14.3&traceId=E-E..-7683DD0E-E694-43C1-B2EB-465165D225D7&versionName=7.4.1&_postid=-34011816";

    public static String getRequestParameter(String imei, String M2) {
        String xmlTop = "<?xml version=\"1.0\"?><FHE><Tickets/><PostId>3861862899</PostId><Data DataType=\"Json/P\">";
        String param = "{}";
        String xmlEnd = "</Data></FHE>";
        return xmlTop + param + xmlEnd;
    }

    /*入参：Cookie:
    fs_token=P34pCJ4pCZSjPZKtCIqqPZGpBJWoC6OjOZOvC3OvP6PbCZSm; LoginId=LOGIN_ID_e2e5583f-b64d-4662-9ff9-487038f0fb72; FSAuthXC=0G7bua6uvm80003bkzBQrFtp1JsAhFkyKy8z1Xh7yZoK69KKTPpKknLaLGR2vHwzXcS72661iot9JTVtBE68DtMHjned23qPMicvvzcvO4UFFqQyzJLuRolq2azQ2AzY9P4Ocf2LiVeAh2twsUs7M9E5uLo6m4sgz3mPhCFfzy9vrI9yxWDFCu1iCVDcit8PgvGj2vNFjedK8NU34zK8NgVAGXDCneCEqV1ElQl0edoyo718HhVngBcl0Nhg0; FSAuthX=0G7bua6uvm80003bkzBQrFtp1JsAhFkyKy8z1Xh7yZoK69KKTPpKknLaLGR2vHwzXcS72661iot9JTVtBE68DtMHjned23qPMicvvzcvO4UFFqQyzJLuRolq2azQ2AzY9P4Ocf2LiVeAh2twsUs7M9E5uLo6m4sgz3mPhCFfzy9vrI9yxWDFCu1iCVDcit8PgvGj2vNFjedK8NU34zK8NgVAGXDCneCEqV1ElQl0edoyo718HhVngBcl0Nhg0; sso_token=f1ddbf16-a296-4ff0-8456-aa165477a7eb

    <?xml version="1.0"?>
<FHE><Tickets/><PostId>3861862899</PostId><Data DataType="Json/P">{}</Data></FHE>*/

    /*返回结果
    <?xml version="1.0" encoding="UTF-8"?>
<FHE><Result Status="0" Code="0" /><UserInfo EID="1371" EA="577382" /><Data DataType="Json/P">{"M1":{"M1":"577382","M10":"","M11":"M","M12":"上海中商网络股份有限公司","M13":"N_201910_03_f48848aebac44d6397da0ca3d9abf455","M14":{"M1":1039,"M2":"黄辉","M3":"N_201808_01_258dccf25e534bbf861ffb57a10f1f5b","M4":"HUANGHUI"},"M15":"上海中商网络集团公司","M16":false,"M17":[],"M18":[],"M19":[],"M2":1371,"M20":"zh-CN","M21":577382,"M22":0,"M3":"高纯","M4":"15717176068","M5":false,"M6":"+86","M7":"15717176068","M8":"","M9":"chun.gao@yesno.com.cn"},"M2":{"M1":"1617450227132(LpKqyw==)","M2":"cf2DYhQdeCOcn3CtEaL3Iw==","M3":1617829200000,"M4":"EjLWjySo526P6x/kXjOGbycqvfaRXT6Iu0OosPsSwQc=","M5":"90646AC1-2641-41A2-BFF6-7D4D0A61F256","M6":["120.133.55.26:443","119.254.150.49:443"],"M7":["120.133.55.26:443","119.254.150.49:443"]},"M3":577382}</Data></FHE>
*/
}
