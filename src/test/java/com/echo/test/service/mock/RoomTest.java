package com.echo.test.service.mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.Hotel;
import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.type.RoomStatusType;
import com.echo.service.roomservice.RoomServiceImpl;

public class RoomTest {
	
	@InjectMocks
	private RoomServiceImpl mockRoomService;
	
	@Mock
	private RoomDAOImpl mockRoomDAOImpl;
	
	@Before 
    public void initMocks() {
    	//初始化当前测试类所有Mock模拟对象
        MockitoAnnotations.initMocks(this);
    }
	
	/**
	 * 测试入住处理
	 */
	@Test
	public void testCheckIn(){
		List<Room> roomList = new ArrayList<>();
		Room r1 = new Room(1,801,32,"豪华房",888,RoomStatusType.FREE);
		Room r2 = new Room(1,802,32,"豪华房",888,RoomStatusType.FREE);
		Room r3 = new Room(1,803,32,"豪华房",888,RoomStatusType.FREE);
		roomList.add(r1 );
		roomList.add(r2);
		roomList.add(r3);
		
		when(mockRoomDAOImpl.getRoomsByTypeAndStatus(32,RoomStatusType.FREE)).thenReturn(roomList);
		when(mockRoomDAOImpl.update(r1)).thenReturn(true);
		when(mockRoomDAOImpl.update(r2)).thenReturn(true);
		when(mockRoomDAOImpl.update(r3)).thenReturn(true);
		
		List<Room> result = mockRoomService.checkIn(32, 2);
		assertThat(result.size(), is(2));
		System.out.println("-------------checkIn-------------");
		System.out.println(result);
		
		verify(mockRoomDAOImpl,times(1)).getRoomsByTypeAndStatus(32,RoomStatusType.FREE);
		verify(mockRoomDAOImpl,times(2)).update(any(Room.class));
	}
	
	/**
	 * 测试退房处理
	 */
	@Test
	public void testCheckOut(){
		RoomCheckItem roomCheckItem = new RoomCheckItem();
		roomCheckItem.setId(1000);
		roomCheckItem.setHotelID(1);
		roomCheckItem.setRoomNumber(801);
		Room room = new Room(1,801,32,"豪华房",888,RoomStatusType.USING);
		
		when(mockRoomDAOImpl.getCheckItemByItemID(1000)).thenReturn(roomCheckItem);
		when(mockRoomDAOImpl.get(1,801)).thenReturn(room);
		when(mockRoomDAOImpl.update(room)).thenReturn(true);
		
		assertTrue(mockRoomService.checkOut(1000));
		assertThat(room.getStatus(),is(RoomStatusType.FREE));
		
		verify(mockRoomDAOImpl,times(1)).getCheckItemByItemID(any(int.class));
		verify(mockRoomDAOImpl,times(1)).get(any(int.class),any(int.class));
		verify(mockRoomDAOImpl,times(1)).update(any(Room.class));
	}
	
	/**
	 * 测试线下寻找房间与入住处理 、退房处理
	 */
	@Test
	public void testCheckInAndCheckOutOffline(){
		
		Room room = new Room(1,801,32,"豪华房",888,RoomStatusType.FREE);
		ArrayList<Room> list = new ArrayList<>();
		list.add(room);
		
		when(mockRoomDAOImpl.getRoomsByStatus(1,RoomStatusType.FREE)).thenReturn(list);
		when(mockRoomDAOImpl.get(1,801)).thenReturn(room);
		when(mockRoomDAOImpl.update(room)).thenReturn(true);
		
		assertNotNull(mockRoomService.showEmptyRooms(1));
		assertTrue(mockRoomService.checkInOffline(1,801));
		assertThat(room.getStatus(), is(RoomStatusType.USING));
		assertTrue(mockRoomService.checkOutOffline(1,801));
		assertThat(room.getStatus(), is(RoomStatusType.FREE));
		
		verify(mockRoomDAOImpl,times(1)).getRoomsByStatus(1,RoomStatusType.FREE);
		verify(mockRoomDAOImpl,times(2)).get(any(int.class),any(int.class));
		verify(mockRoomDAOImpl,times(2)).update(any(Room.class));
		
	}
	
	/**
	 * 测试添加入住记录、退房记录
	 */
	@Test
	public void testRoomCheckItem(){
		Hotel hotel = new Hotel();
		hotel.setHotelID(1);
		Order order = new Order();
		order.setOrderID(1000);
		order.setBookingNum(2);
		order.setHotel(hotel);
		order.setCustomerID(10010);
		Room room1 = new Room(1,801,32,"豪华房",888,RoomStatusType.FREE);
		Room room2 = new Room(1,803,32,"豪华房",888,RoomStatusType.FREE);
		List<Room> list = new ArrayList<>();
		list.add(room1);
		list.add(room2);
		
		when(mockRoomDAOImpl.add(any(RoomCheckItem.class))).thenReturn(true);
		assertTrue(mockRoomService.addRoomCheckinItem(order,list));
		verify(mockRoomDAOImpl,times(2)).add(any(RoomCheckItem.class));
		
		RoomCheckItem item = new RoomCheckItem();
		item.setId(2341);
		when(mockRoomDAOImpl.getCheckItemByItemID(2341)).thenReturn(item);
		when(mockRoomDAOImpl.update(item)).thenReturn(true);
		assertTrue(mockRoomService.updateRoomCheckinItem(2341));
		assertNotNull(item.getCheckOutDate());
		verify(mockRoomDAOImpl,times(1)).update(any(RoomCheckItem.class));
		verify(mockRoomDAOImpl,times(1)).getCheckItemByItemID(any(int.class));
	}

}
