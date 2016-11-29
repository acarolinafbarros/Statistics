package org.iStat.api.iResponse;

public class CalcResponse {

    private StatusEnum status;

    private Object result;

    public CalcResponse(StatusEnum status, Object result) {
        super();
        this.status = status;
        this.result = result;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CalcResponse [status=" + status + ", result=" + result + "]";
    }

}
