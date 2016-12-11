package com.echo.dao.test;

import java.util.List;

import com.echo.dao.roomdao.RoomDAO;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class RoomDAO_stub implements RoomDAO {
    public boolean add(Room room) {
        System.out.println("save successfully");
        return false;
    }

    public boolean delete(int hotelID, int roomID) {
        System.out.println("delete successfully");
        return false;
    }

    public boolean update(Room room) {
        System.out.println("update successfully");
        return false;
    }

    public Room get(int hotelID, int roomID) {
        System.out.println("show room");
        return null;
    }

    public List<Room> getAll(int hotelID) {
        System.out.println("show rooms");
        return null;
    }

    public List<Room> getRoomsByType(int hotelID, byte typeID) {
        System.out.println("show rooms");
        return null;
    }

    public List<Room> getRoomsByStatus(int hotelID, byte status) {
        System.out.println("show rooms");
        return null;
    }

    public List<Room> getRoomsByPrice(double floor, double ceiling) {
        System.out.println("show rooms");
        return null;
    }
    
    public boolean add(RoomCheckItem roomCheckItem) {
        System.out.println("add successfully");
        return false;
    }

    public boolean update(RoomCheckItem roomCheckItem) {
        System.out.println("update successfully");
        return false;
    }
    
    
    public boolean add(int hotelID, String typeName) {
        System.out.println("add successfully");
        return false;
    }

    public boolean delete(int typeID) {
        System.out.println("delete successfully");
        return false;
    }

    public boolean update(RoomType roomType) {
        System.out.println("update successfully");
        return false;
    }

    public List<RoomType> getAllType(int hotelID) {
        System.out.println("show roomtype");
        return null;
    }

	@Override
	public Room get(int id) {
		System.out.println("get room successfully");
		return null;
	}

	@Override
	public List<Room> getRoomsByTypeAndStatus(int typeID, byte status) {
		System.out.println("getRoomsByTypeAndStatus successfully");
		return null;
	}

	@Override
	public boolean updateRoomsInfo(int roomTypeID, double price, String typeName) {
		System.out.println("updateRoomsInfo successfully");
		return false;
	}

	@Override
	public List<Object[]> getRoomsByPrice(int hotelID, double floor, double ceiling) {
		System.out.println("getRoomsByPrice successfully");
		return null;
	}

 

	@Override
	public RoomCheckItem getCheckItemByItemID(int id) {
		System.out.println("getCheckItemByItemID successfully");
		return null;
	}

	@Override
	public List<RoomCheckItem> getCheckItemList(int hotelID, int roomNumber) {
		System.out.println("getCheckItemList successfully");
		return null;
	}

	@Override
	public List<RoomCheckItem> getCheckItemList(int hotelID) {
		System.out.println("getCheckItemList successfully");
		return null;
	}

	@Override
	public boolean add(RoomType roomType) {
		System.out.println("add successfully");
		return false;
	}

	@Override
	public RoomType getType(int roomTypeID) {
		System.out.println("getType successfully");
		return null;
	}

	@Override
	public boolean deleteType(int typeID) {
		System.out.println("deleteType successfully");
		return false;
	}
}
