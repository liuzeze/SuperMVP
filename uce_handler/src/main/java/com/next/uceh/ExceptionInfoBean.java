package com.next.uceh;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : NeXT
 *     time   : 2018/09/25
 *     desc   :
 * </pre>
 */
public class ExceptionInfoBean implements Parcelable {

    private int lineNumber;
    private String cause;
    private String className;
    private String methodName;
    private String exceptionType;
    private String stackTraceString;
    private String activityLogString;
    private String url;
    private boolean isAutoSend;

    public static ExceptionInfoBean newInstance() {
        return new ExceptionInfoBean();
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStackTraceString() {
        return stackTraceString;
    }

    public void setStackTraceString(String stackTraceString) {
        this.stackTraceString = stackTraceString;
    }

    public String getActivityLogString() {
        return activityLogString;
    }

    public void setActivityLogString(String activityLogString) {
        this.activityLogString = activityLogString;
    }

    public String getCause() {
        return cause;
    }

    public String getUrl() {
        return url;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean AutoSend() {
        return isAutoSend;
    }

    @Override
    public String toString() {
        return "ExceptionInfoBean{" +
                "lineNumber=" + lineNumber +
                ", cause='" + cause + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", exceptionType='" + exceptionType + '\'' +
                ", stackTraceString='" + stackTraceString + '\'' +
                ", activityLogString='" + activityLogString + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public ExceptionInfoBean() {
    }

    public ExceptionInfoBean lineNumber(int val) {
        lineNumber = val;
        return this;
    }

    public ExceptionInfoBean methodName(String val) {
        methodName = val;
        return this;
    }

    public ExceptionInfoBean className(String val) {
        className = val;
        return this;
    }

    public ExceptionInfoBean stackTraceString(String val) {
        stackTraceString = val;
        return this;
    }

    public ExceptionInfoBean activityLogString(String val) {
        activityLogString = val;
        return this;
    }

    public ExceptionInfoBean cause(String val) {
        cause = val;
        return this;
    }

    public ExceptionInfoBean exceptionType(String val) {
        exceptionType = val;
        return this;
    }

    public ExceptionInfoBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public ExceptionInfoBean isAutoSend(boolean isAutoSend) {
        this.isAutoSend = isAutoSend;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.lineNumber);
        dest.writeString(this.cause);
        dest.writeString(this.className);
        dest.writeString(this.methodName);
        dest.writeString(this.exceptionType);
        dest.writeString(this.stackTraceString);
        dest.writeString(this.activityLogString);
        dest.writeString(this.url);
        dest.writeString(String.valueOf(this.isAutoSend));
    }

    protected ExceptionInfoBean(Parcel in) {
        this.lineNumber = in.readInt();
        this.cause = in.readString();
        this.className = in.readString();
        this.methodName = in.readString();
        this.exceptionType = in.readString();
        this.stackTraceString = in.readString();
        this.activityLogString = in.readString();
        this.url = in.readString();
        this.isAutoSend = Boolean.parseBoolean(in.readString());
    }

    public static final Creator<ExceptionInfoBean> CREATOR = new Creator<ExceptionInfoBean>() {
        @Override
        public ExceptionInfoBean createFromParcel(Parcel source) {
            return new ExceptionInfoBean(source);
        }

        @Override
        public ExceptionInfoBean[] newArray(int size) {
            return new ExceptionInfoBean[size];
        }
    };


}
