package com.echo.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.echo.dao.roomdao.RoomDAOImpl;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RoomTest{

	@Autowired
	private RoomDAOImpl roomDAOImpl;
	
	@Test
	public void testAddRoomType(){
		RoomType roomType = new RoomType();
		roomType.setTypeID(1);
		roomType.setTypeName("标准双人间");
		roomType.setTypeDesc("");
		roomDAOImpl.add(roomType);
	}
	
	@Test
	public void testGetAllRoomTypes(){
		List<RoomType> results = roomDAOImpl.getAllType(1);
		assertNotNull(results);
//		System.out.println(results);
	}
	
	@Test
	public void testDeleteRoomTypes(){
		assertTrue(roomDAOImpl.deleteType(3));
	}
	
	@Test
	public void testUpdateRoomType(){
		RoomType roomType = new RoomType();
		roomType.setTypeID(1);
		roomType.setTypeName("标准双人间");
		roomType.setTypeDesc("test3");
		assertTrue(roomDAOImpl.update(roomType));
	}
	
	@Test
	public void testAddRoom(){
		Room room = new Room(1, 888, (byte)2, "普通57", 888.0, (byte)1);
		assertTrue(roomDAOImpl.add(room));
	}
	
	@Test
	@Transactional  
    @Rollback(true) 
	public void testDeleteRoom(){
		assertTrue(roomDAOImpl.delete(86));
	}
	
	@Test
	@Transactional  
    @Rollback(true) 
	public void testUpdateRoom(){
		Room room = new Room();
		room.setId(85);
		room.setHotelID(1);
		room.setRoomNumber(803);
		room.setStatus((byte)1);
		room.setTypeID((byte)2);
		room.setTypeName("豪华房");
		room.setPrice(999.0);
		assertTrue(roomDAOImpl.update(room));
	}
	
	@Test
	public void testGetRoom(){
		assertNotNull(roomDAOImpl.get(1));
	}
	
	@Test
	public void testGetAllRooms(){
		assertNotNull(roomDAOImpl.getAll(1));
//		System.out.println(roomDAOImpl.getAll(1).size());
	}
	
//	@Test
//	public void testGetRoomsByType(){
//		assertNotNull(roomDAOImpl.getRoomsByType(1, (byte)2));
//		System.out.println(roomDAOImpl.getRoomsByType(1, (byte)2).size());
//	}
//	
	@Test
	public void testGetRoomsByStatus(){
		assertNotNull(roomDAOImpl.getRoomsByStatus(1, (byte)1));
		System.out.println(roomDAOImpl.getRoomsByStatus(1, (byte)1).size());
	}
	
	@Test
	public void testGetRoomsByPrice(){
		assertNotNull(roomDAOImpl.getRoomsByPrice(1, 300,900));
		List<Object[]> res = roomDAOImpl.getRoomsByPrice(1, 300,900);
		for(Object[] objArray :res){
			System.out.println(objArray[0]+"-"+objArray[1]+"-"+objArray[2]+"-"+objArray[3]);
		}
	}
	
	@Test
	public void testGetMin(){
		System.out.println(roomDAOImpl.getMinPrice(1));
	}
	
	@Test
	public void testGetNumByRoomTypeID(){
		System.out.println(roomDAOImpl.getNumByRoomTypeID(3));
	}
	
	@Test
	public void testGetRoomCheckItem(){
//		System.out.println(roomDAOImpl.getCheckItem(hotelID, roomNumber));
	}
	
	@Test
	public void testAddRoomCheckItem(){
		RoomCheckItem roomCheckItem = new RoomCheckItem();
		roomCheckItem.setHotelID(123);
		roomCheckItem.setOrderID(123);
		roomCheckItem.setUserID(123);
		roomCheckItem.setRoomNumber(123);
		System.out.println(roomDAOImpl.add(roomCheckItem));
	}
	
 
}
