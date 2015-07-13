package com.microsoft.xrm.sdk.Messages;

import java.util.ArrayList;

/**
 * Created on 3/31/2015.
 */
public final class ErrorInfo {
    private String errorCode;
    private ArrayList<ResourceInfo> resourceList;

    public ErrorInfo() {
        resourceList = new ArrayList<>();
    }

    public ArrayList<ResourceInfo> getResourceList() {
        return resourceList;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setResourceList(ArrayList<ResourceInfo> resourceList) {
        this.resourceList = resourceList;
    }

//    static ErrorInfo LoadFromXml(XElement item)
//    {
//        List<ResourceInfo> list = new List<ResourceInfo>();
//        foreach (var resourceList in item.Element(Util.ns.g + "ResourceList").Elements())
//        {
//            list.Add(ResourceInfo.LoadFromXml(resourceList));
//        }
//        ErrorInfo errorInfo = new ErrorInfo()
//        {
//            ErrorCode = item.Element(Util.ns.g + "ErrorCode").Value,
//            ResourceList = list.ToArray()
//        };
//        return errorInfo;
//    }
}
