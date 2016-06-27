package com.microsoft.xrm.sdk;

import java.util.Set;

/**
 * Created on 3/6/2015.
 */
public final class RelatedEntityCollection extends DataMapCollection<Relationship, EntityCollection> {

    @Override
    public boolean getIsReadOnly() {
        return super.getIsReadOnly();
    }

    @Override
    public void setIsReadOnly(boolean value) {
        super.setIsReadOnly(value);
        Set<Relationship> keys = this.keySet();

        for (Relationship relationship : keys) {
            EntityCollection entities = this.get(relationship);
            if (entities != null) {
                entities.setIsReadOnly(true);
            }
        }
    }

    String toXml() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.size() == 0) {
            return stringBuilder.append("<a:RelatedEntities/>").toString();
        }
        stringBuilder.append("<a:RelatedEntities>");
        for (Entry<Relationship, EntityCollection> item : this.entrySet()) {
            stringBuilder.append(relatedEntityToXml(item));
        }

        stringBuilder.append("</a:RelatedEntities>");
        return stringBuilder.toString();
    }

    String relatedEntityToXml(Entry<Relationship, EntityCollection> item)
    {
        return "<a:KeyValuePairOfRelationshipEntityCollectionX_PsK4FkN>"
                + Utils.objectToXml(item.getKey(), "b:key", true)
                + Utils.objectToXml(item.getValue(), "b:value", true)
                + "</a:KeyValuePairOfRelationshipEntityCollectionX_PsK4FkN>";
    }
}
