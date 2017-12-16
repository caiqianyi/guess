package com.caiqianyi.commons.pager;

import java.util.List;


public class Pager {
	
	private Integer offset = null;// 起始行
	private Integer size = null;// 分页大小
	 
	private Object params; //slq需要的参数对象
	
	private Integer total;// 记录总条数
	
	private List<?> datas;
	
	public Pager() {
		// TODO Auto-generated constructor stub
	}
	public Pager(Integer size,Integer offset) {
		// TODO Auto-generated constructor stub
		this.size = size;
		this.offset = offset;
	}
	
	public Pager(Integer offset, Integer size, Object params) {
		this.offset =offset;
		this.size= size;
		this.params = params;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public Integer offset() {
		return offset;
	}

	public Integer size() {
		return size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

}
