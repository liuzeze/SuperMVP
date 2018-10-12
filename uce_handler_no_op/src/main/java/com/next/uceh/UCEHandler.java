package com.next.uceh;

import android.content.Context;

/**
 * <pre>
 *     author : NeXT
 *     time   : 2018/09/26
 *     desc   :
 * </pre>
 */
public class UCEHandler {

    private UCEHandler(Builder builder) {
    }

    public static class Builder {

        public Builder(Context context) {

        }

        public Builder setUCEHCallback(UCECallback ucehCallback) {
            return this;
        }

        public Builder setUCEHEnabled(boolean isUCEHEnabled) {
            return this;
        }

        public Builder setTrackActivitiesEnabled(boolean isTrackActivitiesEnabled) {
            return this;
        }

        public Builder setBackgroundModeEnabled(boolean isBackgroundModeEnabled) {
            return this;
        }

        public UCEHandler build() {
            return new UCEHandler(this);
        }

        public Builder setServiceUrl(String url) {
            return this;
        }

        public Builder setAutoSendLog(boolean isAutoSend) {
            return this;
        }
    }

}
