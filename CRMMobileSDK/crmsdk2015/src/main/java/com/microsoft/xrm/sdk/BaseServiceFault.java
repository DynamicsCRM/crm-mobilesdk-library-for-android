//package com.microsoft.xrm.sdk;
//
//import java.util.Date;
//
///**
// * Created on 3/30/2015.
// */
//public abstract class BaseServiceFault extends Exception {
//
//    private int errorCode;
//    private ErrorDetailCollection errorDetails;
//    private String message;
//    private Date timestamp;
//
//    public int getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(int errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public ErrorDetailCollection getErrorDetails() {
//        return errorDetails;
//    }
//
//    public void setErrorDetails(ErrorDetailCollection errorDetails) {
//        this.errorDetails = errorDetails;
//    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }
//
////    static void LoadFromXml(XElement item, BaseServiceFault fault)
////    {
////        if (item.Elements().Count() == 0)
////            return;
////        fault.ErrorCode = Util.LoadFromXml<int>(item.Element(Util.ns.a + "ErrorCode"));
////        fault.ErrorDetails = ErrorDetailCollection.LoadFromXml(item.Element(Util.ns.a + "ErrorDetails"));
////        fault.Message = Util.LoadFromXml<string>(item.Element(Util.ns.a + "Message"));
////        fault.Timestamp = Util.LoadFromXml<DateTime>(item.Element(Util.ns.a + "Timestamp"));
////    }
//}
