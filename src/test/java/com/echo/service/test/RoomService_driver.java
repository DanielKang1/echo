package com.echo.service.test;

import java.util.List;
import java.util.ListIterator;

import com.echo.domain.po.Order;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomType;
import com.echo.service.roomservice.RoomService;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class RoomService_driver {
    public void drive(RoomService roomService){
        roomService.addNewRoom(new Room());
        roomService.checkOut(0);
        roomService.showEmptyRooms(0);
        roomService.showUsedRooms(0);
        roomService.updateRoom(new Room());
        roomService.addRoomCheckinItem(new Order(), null);
        roomService.addRoomType(new RoomType());
        roomService.canDeleteRoomType(1);
        roomService.deleteRoom(1);
        roomService.updateRoomsInfo(1, 2, "");
        roomService.getRoomTypeByTypeID(1);
        roomService.getRoomTypeByHotelID(1);
        roomService.getRoomCheckItemByRoomNumber(1, 1);
    }

    public static void main(String[] args) {
        (new RoomService_driver()).drive(new RoomService_stub());
    }
}
