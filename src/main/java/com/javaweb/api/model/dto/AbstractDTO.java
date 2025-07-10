package com.javaweb.api.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AbstractDTO<T> implements Serializable {
    private static final long serialVersionUID = 7213600440729202783L;

	private Long id ;
	private Date createdDate ;
	private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private List<T> listResult = new ArrayList<>();
    private int page = 1;
    private int maxPageItems = 2 ;
    private int totalItems = 0;
    private String searchValue;
    private Integer totalPage;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPageItems() {
		return maxPageItems;
	}
	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public Integer getTotalPage() {
		return (int) Math.ceil((double) this.getTotalItems() / this.getMaxPageItems());
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    

}
