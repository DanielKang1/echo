package com.echo.domain.vo;

import java.io.Serializable;
import java.util.List;

import com.echo.domain.po.Hotel;


public class HotelSearchResult implements Serializable{
	
	private static final long serialVersionUID = -1651903981158549298L;
	
	private Hotel hotel;      //酒店信息
	private double rating;    //总评分
	private int EvalutionNum;  //评论人数
	private double minPrice;   //该酒店最低价格
	private List<RoomSearchResult> roomInfo; //符合类型的房间信息
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getEvalutionNum() {
		return EvalutionNum;
	}
	public void setEvalutionNum(int evalutionNum) {
		EvalutionNum = evalutionNum;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public List<RoomSearchResult> getRoomInfo() {
		return roomInfo;
	}
	public void setRoomInfo(List<RoomSearchResult> roomInfo) {
		this.roomInfo = roomInfo;
	}
	@Override
	public String toString() {
		return "HotelSearchResult [hotel=" + hotel + ", rating=" + rating + ", EvalutionNum=" + EvalutionNum
				+ ", minPrice=" + minPrice + ", roomInfo=" + roomInfo + "]";
	}
	

}
