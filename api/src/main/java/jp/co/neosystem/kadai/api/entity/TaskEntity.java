package jp.co.neosystem.kadai.api.entity;

import java.util.Date;

public class TaskEntity {

	private Long id;

	private String title;

	private Date startDate;

	private Date limitDate;

	private String memo;

	private Date taskCompleteDate;

	private Date createDate;

	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getTaskCompleteDate() {
		return taskCompleteDate;
	}

	public void setTaskCompleteDate(Date taskCompleteDate) {
		this.taskCompleteDate = taskCompleteDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}