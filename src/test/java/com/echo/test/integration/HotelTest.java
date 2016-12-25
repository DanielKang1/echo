package com.echo.test.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.echo.domain.vo.HotelSearchResult;
import com.echo.domain.vo.HotelSearcher;
import com.echo.service.hotelservice.HotelServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HotelTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public HotelServiceImpl hotelServiceImpl;
	
	@Test
	public void testSearch(){
		HotelSearcher hotelSearcher = new HotelSearcher("南京", "新街口", (byte)5, "", 100, 900);
		List<HotelSearchResult> res = hotelServiceImpl.search(hotelSearcher);
		assertThat(res.size(),greaterThan(0));
	}
	
	@Test
	public void testSearch2(){
		assertThat(hotelServiceImpl.searchByCityName("南京").size(),greaterThan(0));
		assertThat(hotelServiceImpl.searchByDistrictName("新街口地区（市中心）").size(),greaterThan(0));
	}
	
	@Test
	public void testSort(){
		HotelSearcher hotelSearcher = new HotelSearcher("南京", "新街口地区（市中心）", (byte)0 , "", 0, 99999);
		List<HotelSearchResult> res = hotelServiceImpl.search(hotelSearcher);
		System.out.println("----------------原始顺序-----------------------");
		for(HotelSearchResult r: res){
			System.out.println(r.getHotel().getHotelID()+"---"+r.getMinPrice());
		}
		
		hotelServiceImpl.sortByPriceAscending(res);
		System.out.println("----------------升       序-----------------------");
		for(HotelSearchResult r: res){
			System.out.println(r.getHotel().getHotelID()+"---"+r.getMinPrice());
		}
		
		hotelServiceImpl.sortByPriceDescending(res);
		System.out.println("----------------降       序-----------------------");
		for(HotelSearchResult r: res){
			System.out.println(r.getHotel().getHotelID()+"---"+r.getMinPrice());
		}
		
		hotelServiceImpl.sortByStarLevelDescending(res);
		System.out.println("----------------星级排序-----------------------");
		for(HotelSearchResult r: res){
			System.out.println(r.getHotel().getHotelID()+"---"+r.getHotel().getStarLevel());
		}
		
		hotelServiceImpl.sortByRatingDescending(res);
		System.out.println("----------------评分排序-----------------------");
		for(HotelSearchResult r: res){
			System.out.println(r.getHotel().getHotelID()+"---"+r.getRating());
		}
	}
	
	@Test
	public void testSelectLived(){
		HotelSearcher hotelSearcher = new HotelSearcher("南京", "新街口地区（市中心）", (byte)0 , "", 0, 9999999);
		List<HotelSearchResult> res = hotelServiceImpl.search(hotelSearcher);
		List<HotelSearchResult>  livedRes = hotelServiceImpl.getLivedResult(res,3 );
		for(HotelSearchResult r: livedRes){
			System.out.println(r.getHotel().getHotelName());
		}
	}
	
	

	

}
