package com.example.demo.ag.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("ag_notice_details")
public class AGNotice {

	@Id
	@Field("notice_id")
	private int noticeId;

	@Field("notice_summary")
	private String noticeSummary;

	@Field("notice_details")
	private String noticeDetails;

	@Field("notic_beg_dt")
	private Date noticBegDt;
	
	@Field("notic_end_dt")
	private Date noticEndDt;
	
	@Field("create_dt")
	private Date createDt;
	
	@Field("update_dt")
	private Date updateDt;

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeSummary() {
		return noticeSummary;
	}

	public void setNoticeSummary(String noticeSummary) {
		this.noticeSummary = noticeSummary;
	}

	public String getNoticeDetails() {
		return noticeDetails;
	}

	public void setNoticeDetails(String noticeDetails) {
		this.noticeDetails = noticeDetails;
	}

	public Date getNoticBegDt() {
		return noticBegDt;
	}

	public void setNoticBegDt(Date noticBegDt) {
		this.noticBegDt = noticBegDt;
	}

	public Date getNoticEndDt() {
		return noticEndDt;
	}

	public void setNoticEndDt(Date noticEndDt) {
		this.noticEndDt = noticEndDt;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}	
}
