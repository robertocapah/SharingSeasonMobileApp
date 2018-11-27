package com.shp.sharingseasonmobileapp.Response.dataApotek;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("next")
	private int next;

	@SerializedName("current")
	private int current;

	@SerializedName("last")
	private int last;

	@SerializedName("records")
	private List<RecordsItem> records;

	@SerializedName("before")
	private int before;

	@SerializedName("total_records")
	private int totalRecords;

	@SerializedName("limit")
	private int limit;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("first")
	private int first;

	public void setNext(int next){
		this.next = next;
	}

	public int getNext(){
		return next;
	}

	public void setCurrent(int current){
		this.current = current;
	}

	public int getCurrent(){
		return current;
	}

	public void setLast(int last){
		this.last = last;
	}

	public int getLast(){
		return last;
	}

	public void setRecords(List<RecordsItem> records){
		this.records = records;
	}

	public List<RecordsItem> getRecords(){
		return records;
	}

	public void setBefore(int before){
		this.before = before;
	}

	public int getBefore(){
		return before;
	}

	public void setTotalRecords(int totalRecords){
		this.totalRecords = totalRecords;
	}

	public int getTotalRecords(){
		return totalRecords;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setFirst(int first){
		this.first = first;
	}

	public int getFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"next = '" + next + '\'' + 
			",current = '" + current + '\'' + 
			",last = '" + last + '\'' + 
			",records = '" + records + '\'' + 
			",before = '" + before + '\'' + 
			",total_records = '" + totalRecords + '\'' + 
			",limit = '" + limit + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}