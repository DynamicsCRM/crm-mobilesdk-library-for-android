package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Metadata.OptionSetMetadataBase;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/24/2015.
 */
public final class CreateOptionSetRequest extends OrganizationRequest {

    public CreateOptionSetRequest() {
        this.setRequestName("CreateOptionSet");
        this.setResponseType(new CreateOptionSetResponse());
        this.setOptionSet(null);
    }

    @Nullable
    public OptionSetMetadataBase getOptionSet() {
        if (this.getParameters().containsKey("OptionSet")) {
            return (OptionSetMetadataBase) this.getParameters().get("OptionSet");
        }
        return null;
    }

    public void setOptionSet(OptionSetMetadataBase value) {
        this.set("OptionSet", value);
    }

    @Nullable
    public String getSolutionUniqueName() {
        if (this.getParameters().containsKey("SolutionUniqueName")) {
            return this.getParameters().get("SolutionUniqueName").toString();
        }
        return null;
    }

    public void setSolutionUniqueName(String value) {
        this.set("SolutionUniqueName", value);
    }

    @Override
    public String getRequestBody() {
        this.set("OptionSet", getOptionSet());
        this.set("SolutionUniqueName", getSolutionUniqueName());
        return getSoapBody();
    }
}
