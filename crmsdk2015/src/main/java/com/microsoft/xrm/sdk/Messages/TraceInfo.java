package com.microsoft.xrm.sdk.Messages;

import java.util.ArrayList;

/**
 * Created on 3/31/2015.
 */
public final class TraceInfo {

    private ArrayList<ErrorInfo> errorInfoList;

    public TraceInfo() {
        errorInfoList = new ArrayList<>();
    }

    public ArrayList<ErrorInfo> getErrorInfoList() {
        return errorInfoList;
    }

    //    static TraceInfo LoadFromXml(XElement item)
//    {
//        TraceInfo traceInfo = new TraceInfo();
//        List<ErrorInfo> list = new List<ErrorInfo>();
//        foreach (var errorInfo in item.Element(Util.ns.g + "ErrorInfoList").Elements())
//        {
//            list.Add(ErrorInfo.LoadFromXml(errorInfo));
//        }
//
//        traceInfo.ErrorInfoList = list.ToArray();
//        return traceInfo;
//    }
}
