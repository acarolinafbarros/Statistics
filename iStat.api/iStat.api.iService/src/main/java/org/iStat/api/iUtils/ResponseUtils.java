package org.iStat.api.iUtils;

import org.iStat.api.iResponse.ResponseStatusiStat;
import org.iStat.api.iResponse.StatusEnum;

public class ResponseUtils {

    public static ResponseStatusiStat buildResponseStatus(StatusEnum status, String messagem, Object... args) {
        ResponseStatusiStat responseStatus = new ResponseStatusiStat();
        responseStatus.setStatus(status);
        responseStatus.setMessage(messagem != null
                ? String.format(messagem, args) : null);
        return responseStatus;
    }

    public static ResponseStatusiStat buildResponseStatus(StatusEnum status) {
        return buildResponseStatus(status, null);
    }

}
