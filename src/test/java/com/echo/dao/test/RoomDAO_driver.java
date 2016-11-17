package com.echo.dao.test;

import com.echo.dao.roomdao.RoomDAO;
import com.echo.domain.po.Room;
import com.echo.domain.po.RoomCheckItem;
import com.echo.domain.po.RoomType;

/**
 * Created by D.niel_K on 16/10/15.
 */
public class RoomDAO_driver {
    public void drive(RoomDAO roomDAO) {
    	roomDAO.delete(0);
        roomDAO.get(0,0);
        roomDAO.getAll(0);
        roomDAO.getRoomsByPrice(1,0.1,0.1);
        roomDAO.getRoomsByStatus(0,new Byte("a"));
        roomDAO.add(new Room());
        roomDAO.update(new Room());
        roomDAO.delete(0);
        roomDAO.getAllType(0);
        roomDAO.update(new RoomType());
        roomDAO.add(new RoomCheckItem());
        roomDAO.update(new RoomCheckItem());
    }

    public void main(String[] args){
        (new RoomDAO_driver()).drive(new RoomDAO_stub()) ;
    }
}
