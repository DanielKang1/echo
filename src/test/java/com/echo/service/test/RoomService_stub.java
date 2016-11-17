package com.echo.service.test;

import java.util.List;

import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;
import com.echo.service.roomservice.RoomService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class RoomService_stub implements RoomService {
    public boolean addNewRoom(Room room) {
        System.out.println("addNewRoom successfully");
        return false;
    }

    public boolean deleteRoom(int hotelID, int roomID) {
        System.out.println("deleteRoom successfully");
        return false;
    }

    public boolean updateRoom(Room room) {
        System.out.println("updateRoom successfully");
        return false;
    }

    public List<Room> showEmptyRooms(int hotelid) {
        System.out.println("show Room");
        return null;
    }

    public List<Room> showUsedRooms(int hotelid) {
        System.out.println("show Room");
        return null;
    }

    public boolean checkIn(Order order) {
        System.out.println("checkIn successfully");
        return false;
    }

    public boolean checkInOffline(int roomid) {
        System.out.println("checkInOffline successfully");
        return false;
    }

    public boolean checkOut(int roomID) {
        System.out.println("checkOut successfully");
        return false;
    }

    public List<Room> priceSearch(double floor, double ceiling) {
        System.out.println("show Room");
        return null;
    }

    public boolean HandleAbnormalOrder(String nickName) {
        System.out.println("HandleAbnormalOrder successfully");
        return false;
    }

	@Override
	public boolean deleteRoom(int id) {
		System.out.println("deleteRoom successfully");
		return false;
	}

	@Override
	public Room getRoomByRoomMumber(int hotelID, int roomNumber) {
		System.out.println("getRoomByRoomMumber successfully");
		return null;
	}

	@Override
	public boolean addRoomType(RoomType roomType) {
		System.out.println("addRoomType successfully");
		return false;
	}

	@Override
	public boolean deleteRoomType(int TypeID) {
		System.out.println("deleteRoomType successfully");
		return false;
	}

	@Override
	public boolean canDeleteRoomType(int typeID) {
		System.out.println("canDeleteRoomType successfully");
		return false;
	}

	@Override
	public boolean updateRoomType(RoomType roomType) {
		System.out.println("updateRoomType successfully");
		return false;
	}

	@Override
	public List<Room> checkIn(int roomTypeID, int num) {
		System.out.println("checkIn successfully");
		return null;
	}

	@Override
	public boolean checkInOffline(int hotrlID, int roomNumber) {
		System.out.println("checkInOffline successfully");
		return false;
	}

	@Override
	public boolean checkOutOffline(int hotrlID, int roomNumber) {
		System.out.println("checkOutOffline successfully");
		return false;
	}

	@Override
	public RoomType getRoomTypeByTypeID(int roomTypeID) {
		System.out.println("getRoomTypeByTypeID successfully");
		return null;
	}

	@Override
	public List<RoomType> getRoomTypeByHotelID(int hotelID) {
		System.out.println("getRoomTypeByHotelID successfully");
		return null;
	}

	@Override
	public boolean addRoomCheckinItem(Order order, List<Room> rooms) {
		System.out.println("addRoomCheckinItem successfully");
		return false;
	}

	@Override
	public boolean addRoomCheckinItemOffline(int hotelID, int roomNumber) {
		System.out.println("addRoomCheckinItemOffline successfully");
		return false;
	}

	@Override
	public boolean updateRoomCheckinItem(int roomCheckItemID) {
		System.out.println("updateRoomCheckinItem successfully");
		return false;
	}

	@Override
	public List<RoomCheckItem> getNotCheckoutRoomCheckItems(int hotelID) {
		System.out.println("getNotCheckoutRoomCheckItems successfully");
		return null;
	}

	@Override
	public RoomCheckItem getRoomCheckItemByRoomNumber(int hotelID, int roomNumber) {
		System.out.println("getRoomCheckItemByRoomNumber successfully");
		return null;
	}

	@Override
	public boolean updateRoomsInfo(int roomTypeID, double price, String typeName) {
		System.out.println("updateRoomsInfo successfully");
		return false;
	}
}
