package com.jf.vo.response;


/**
 * @author luoyb
 * Created on 2019/9/29
 */
public class CheckRecSvipPracticeResponse {

    private boolean received;

    public CheckRecSvipPracticeResponse() {
    }

    public CheckRecSvipPracticeResponse(boolean received) {
        this.received = received;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }
}
