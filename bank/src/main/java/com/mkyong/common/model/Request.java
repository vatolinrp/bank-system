package com.mkyong.common.model;

import java.io.Serializable;

public class Request implements Serializable
{
    private Long id;
    private String content;
    private Boolean isApprByRef;
    private String credReport;
    private String accState;
    private Boolean isApprByEmp;
    private Boolean isApprByInsp;
    private String conditions;
    private String isCreated;

    public String getContentForView()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>")
        .append("<td>").append("id:").append("</td>").append("<td>").append(id).append("</td>")
        .append("<td>").append(" | is approved by referent:").append("</td>").append("<td>").append(isApprByRef).append("</td>")
        .append("<td>").append(" | is approved by employee:").append("</td>").append("<td>").append(isApprByEmp).append("</td>")
        .append("<td>").append(" | is approved by inspector:").append("</td>").append("<td>").append(isApprByInsp).append("</td>")
        .append("<td>").append(" | is created:").append("</td>").append("<td>").append(isCreated).append("</td>")
        .append("</tr>")

        .append("<tr>")
        .append("<td>").append(" | content:").append("</td>").append("<td>").append(content).append("</td>")
        .append("<td>").append(" | credit report:").append("</td>").append("<td>").append(credReport).append("</td>")
        .append("</tr>")

        .append("<tr>")
        .append("<td>").append(" | account state:").append("</td>").append("<td>").append(accState).append("</td>")
        .append("<td>").append(" | conditions:").append("</td>").append("<td>").append(conditions).append("</td>")
        .append("</tr>");
        return sb.toString();
    }

    public Request()
    {
        content="";
        isApprByRef=false;
        credReport="";
        accState="";
        isApprByEmp=false;
        isApprByInsp=false;
        conditions="";
        isCreated="";
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Boolean isApprByRef()
    {
        return isApprByRef;
    }

    public void setApprByRef(Boolean apprByRef)
    {
        isApprByRef = apprByRef;
    }

    public String getCredReport()
    {
        return credReport;
    }

    public void setCredReport(String credReport)
    {
        this.credReport = credReport;
    }

    public String getAccState()
    {
        return accState;
    }

    public void setAccState(String accState)
    {
        this.accState = accState;
    }

    public Boolean isApprByEmp() {
        return isApprByEmp;
    }

    public void setApprByEmp(Boolean apprByEmp) {
        isApprByEmp = apprByEmp;
    }

    public Boolean isApprByInsp() {
        return isApprByInsp;
    }

    public void setApprByInsp(Boolean apprByInsp) {
        isApprByInsp = apprByInsp;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String isCreated() {
        return isCreated;
    }

    public void setCreated(String created) {
        isCreated = created;
    }

    @Override
    public String toString()
    {
        return "Request{" + "id=" + id + ", content='" + content + '\'' + ", isApprByRef=" + isApprByRef
                + ", credReport='" + credReport + '\'' + ", accState='" + accState + '\'' + ", isApprByEmp="
                + isApprByEmp + ", isApprByInsp=" + isApprByInsp + ", conditions='" + conditions + '\''
                + ", isCreated=" + isCreated + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Request request = (Request) o;

        if (!id.equals(request.id))
            return false;
        if (!content.equals(request.content))
            return false;
        if (isApprByRef != null ? !isApprByRef.equals(request.isApprByRef) : request.isApprByRef != null)
            return false;
        if (credReport != null ? !credReport.equals(request.credReport) : request.credReport != null)
            return false;
        if (accState != null ? !accState.equals(request.accState) : request.accState != null)
            return false;
        if (isApprByEmp != null ? !isApprByEmp.equals(request.isApprByEmp) : request.isApprByEmp != null)
            return false;
        if (isApprByInsp != null ? !isApprByInsp.equals(request.isApprByInsp) : request.isApprByInsp != null)
            return false;
        if (conditions != null ? !conditions.equals(request.conditions) : request.conditions != null)
            return false;
        return !(isCreated != null ? !isCreated.equals(request.isCreated) : request.isCreated != null);

    }

    @Override
    public int hashCode()
    {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + (isApprByRef != null ? isApprByRef.hashCode() : 0);
        result = 31 * result + (credReport != null ? credReport.hashCode() : 0);
        result = 31 * result + (accState != null ? accState.hashCode() : 0);
        result = 31 * result + (isApprByEmp != null ? isApprByEmp.hashCode() : 0);
        result = 31 * result + (isApprByInsp != null ? isApprByInsp.hashCode() : 0);
        result = 31 * result + (conditions != null ? conditions.hashCode() : 0);
        result = 31 * result + (isCreated != null ? isCreated.hashCode() : 0);
        return result;
    }

    public String toHtml()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>")
        .append("<td>").append("id:").append("</td>").append("<td>").append(id).append("</td>")
        .append("<td>").append(" | is approved by referent:").append("</td>").append("<td>").append(isApprByRef).append("</td>")
        .append("<td>").append(" | is approved by employee:").append("</td>").append("<td>").append(isApprByEmp).append("</td>")
        .append("<td>").append(" | is approved by inspector:").append("</td>").append("<td>").append(isApprByInsp).append("</td>")
        .append("<td>").append(" | is created:").append("</td>").append("<td>").append(isCreated).append("</td>")
        .append("</tr>")

        .append("<tr>")
        .append("<td>").append(" | content:").append("</td>").append("<td>").append(content).append("</td>")
        .append("<td>").append(" | credit report:").append("</td>").append("<td>").append(credReport).append("</td>")
        .append("</tr>")

        .append("<tr>")
        .append("<td>").append(" | account state:").append("</td>").append("<td>").append(accState).append("</td>")
        .append("<td>").append(" | conditions:").append("</td>").append("<td>").append(conditions).append("</td>")
        .append("</tr>");
        return sb.toString();
    }
}
