package com.sharebo.emp.web.entity;

import java.util.ArrayList;
import java.util.List;

public class Pagers<T> {
	private int pageSize;
	private int pageIndex;
	private int totalRecords;
	private int totalPages;
	private List<T> list = new ArrayList<T>();
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		if(pageIndex<=0){
			return 1;
		}
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		if(pageIndex<=0){
			this.pageIndex =1;
		}
		this.pageIndex = pageIndex;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages() {
		this.totalPages = this.totalRecords % this.pageSize == 0 ? this.totalRecords / this.pageSize : this.totalRecords / this.pageSize + 1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public boolean getIsFirst(){
		if(this.pageIndex == 1){
			return true;
		}else{
			return false;
		}
	}
	public boolean getIsLast(){
		if(this.pageIndex >= this.totalPages){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasPrevious(){
		if(this.pageIndex > 1 && this.pageIndex <= this.totalPages){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasNext(){
		if(this.pageIndex < this.totalPages){
			return true;
		}else{
			return false;
		}
	}
	public int getFirstPage(){
		return 1;
	}
	public int getLastPage(){
		return this.totalPages;
	}
	public int getPreviousPage(){
		if(this.hasPrevious()){
			return this.pageIndex - 1;
		}else{
			return 1;
		}
	}
	public int getNextPage(){
		if(this.hasNext()){
			return this.pageIndex + 1;
		}else{
			return this.totalPages;
		}
	}
}
