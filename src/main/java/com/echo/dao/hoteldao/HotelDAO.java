package com.echo.dao.hoteldao;

import java.util.List;
import com.echo.domain.po.Hotel;

public interface HotelDAO {

	/**
	 * 增加酒店
	 * @param hotel
	 * @return
	 */
	public boolean add(Hotel hotel);
	
	/**
	 * 更新酒店基本信息
	 * @param hotel
	 * @return
	 */
	public boolean update(Hotel hotel);
	
	/**
	 * 按照酒店ID获得该酒店所有基本信息
	 * @param hotelID
	 */
	public Hotel get(int hotelID);
	
	public Hotel get(String hotelName);
	
	/**
	 * 对编号为hotelID的酒店的房间进行搜索，以客房类型（typeID）进行分组，
	 * 将（房间类型ID，房间类型名称，价格，数量）取出，放入Object[]中
	 * @param hotelID
	 * @return
	 */
//	public List<Object[]> getRoomTypeAndNum(int hotelID);
	
	/**
	 * 搜索hotel表，将除了价格因素外的其他因素都符合条件的酒店放入List中
	 * @param city 搜索因素:城市名称
	 * @param district 搜索因素:商圈
	 * @param starLevel 搜索因素:星级
	 * @param keyWord 搜索因素:酒店名，简介与设施服务中的关键字
	 * @return  符合条件的酒店ID列表
	 */
	public List<Hotel> getHotels(String city,String district,byte starLevel,String keyWord);
	

}
