package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddListMembersListRequest extends OrganizationRequest {

    public UUID getListId() {
        if (this.getParameters().containsKey("ListId")) {
            return (UUID) this.getParameters().get("ListId");
        }

        return new UUID(0L, 0L);
    }

    public void setListId(UUID value) {
        this.set("ListId", value);
    }

    @Nullable
    public UUID[] getMemberIds() {
        if (this.getParameters().containsKey("MemberIds")) {
            return (UUID[])this.getParameters().get("MemberIds");
        }

        return null;
    }

    public void setMemberIds(UUID[] value) {
        this.set("MemberIds", value);
    }

    public AddListMembersListRequest() {
        this.setResponseType(new AddListMembersListResponse());
        this.setRequestName("AddListMembersList");
    }

    @Override
    public String getRequestBody() {
        this.set("ListId", getListId());
        this.set("MemberIds", getMemberIds());
        return getSoapBody();
    }
}
