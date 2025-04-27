package org.fos.api;

import org.fos.api.config.Base;

public class Routes {
    public static String baseUrl;
    public Routes(Base base){
        baseUrl = base.getBaseUrl();
    }
    public static String bulkVisitEndPoint = "/fos/visit/bulk";
    public static String portfolioEndPoint = "/recovery/fos/portfolio";
    public static String quickVisitEndPoint = "/fos/visit/close";
}
