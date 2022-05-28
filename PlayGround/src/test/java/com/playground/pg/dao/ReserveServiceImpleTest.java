package com.playground.pg.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.playground.pg.domain.ArtDto;
import com.playground.pg.domain.ArtTimeDto;
import com.playground.pg.domain.CouponDto;
import com.playground.pg.domain.MemberDto;
import com.playground.pg.domain.PointDto;
import com.playground.pg.domain.ReserveDto;
import com.playground.pg.service.AdArtService;
import com.playground.pg.service.JoinService;
import com.playground.pg.service.MyPagePointService;
import com.playground.pg.service.ReserveService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ReserveServiceImpleTest {
	@Autowired
	ReserveService reserveService;

	@Autowired
	JoinService jService;

	@Autowired
	AdArtService artService;

	@Test
	public void serviceTset() throws Exception {
		ReserveDto resDto = createReserve();
		MemberDto mDto = createMember();
		ArtDto aDto = createArt();
		ArtTimeDto tDto = createArtTime();

		
//		 //1. 회원가입 확인 
//		int insertRes = jService.joinMember(mDto);
//		System.out.println("MemberDto : "+mDto); 
//		assertTrue(insertRes == 1);
		 

		List<MultipartFile> list = new ArrayList<>();
		HttpSession session = new MockHttpSession();

//		// 2. 작품 등록하기 확인
//		int artresult = artService.insOrUpdArt("insert", list, session, aDto, tDto);
//		System.out.println("aDto : " + aDto);
//		assertTrue("작품 등록하기", artresult == 1);

		// 3. 일반 예약 확인
		boolean insertRes2 = reserveService.insertReserve(resDto);
		System.out.println("ReserveDto : " + resDto);
		assertTrue("예약하기 실행", insertRes2 == true);

	}

	// 예약정보 더미용
	private ReserveDto createReserve() {
		ReserveDto resDto = new ReserveDto();
		// 일반 예약(쿠폰, 포인트 사용x)시 : 아이디, 작품번호, 관람날짜, 관람시간(시작), 관람시간(끝),
		// 어른매수, 아이매수(기본값 0), 결제금액, 결제날자(예약날짜), 예약번호
		// 포인트 or 쿠폰사용시 일반 예약에 포인트, 쿠폰 추가

		Date reDate = new Date(0152, 04, 13);
		resDto.setuId("tester123");
		resDto.setExNo(1);
		resDto.setReDate(reDate);
		resDto.setReTime1("11:00");
		resDto.setReTime2("13:00");
		resDto.setAdCnt(6);
		resDto.setChCnt(0);
		resDto.setPayment(360000);
		resDto.setPayDate(reDate);
		resDto.setCoupon(0);
		resDto.setPoint(0);

		return resDto;
	}

	// 회원정보 더미용
	public MemberDto createMember() {
		MemberDto mDto = new MemberDto();
		// 들어오는 값 : 아이디, 비밀번호, 이름, 생년월일(2022-05-26), 휴대폰번호, 이메일, 등급
		mDto.setId("tester123");
		mDto.setPw("abcde12345");
		mDto.setName("테스터");
		mDto.setBirth("2022-05-27");
		mDto.setPhone("01012341234");
		mDto.setEmail("tester123@naver.com");

		return mDto;
	}

	// 작품정보 더미용
	public ArtDto createArt() {
		ArtDto artDto = new ArtDto();
		// 들어오는 값 : 작품번호, 장소, 작품명, 작품설명, 전시홀명, 최대인원수, 가격, 등급(일반,성인),
		// 가격2(할인가?), 장르, 전시상태, 썸네일이미지, 메인이미지, 추가설명1,2,3
		artDto.setLocation("서울");
		artDto.setExName("해바라기전시회");
		artDto.setExContent("가나다라마바사아가나다라마바사아가나다라마바사아가나다라마바사아");
		artDto.setExhall("해바라기전시관");
		artDto.setAllowNum(25);
		artDto.setPrice1(78000);
//		artDto.setPrice2(68000);
//		artDto.setGrade(0);
		artDto.setGenre("현대미술");
		artDto.setExState("N");
		artDto.setThumbImg("img1.png");
		artDto.setMainImg("img2.png");
		artDto.setExContent1("test1");
		artDto.setExContent2("test2");
		artDto.setExContent3("test3");

		return artDto;
	}

	// 작품시간 더미용
	public ArtTimeDto createArtTime() {
		ArtTimeDto timeDto = new ArtTimeDto();
		// 들어오는 값 : 작품번호, 전시기간(시작), 전시기간(끝), 전시시간1, 전시시간2, 전시시간3
		Date exDate1 = new Date(0152, 04, 00);
		Date exDate2 = new Date(0152, 04, 27);
		timeDto.setExNo_FK(1);
		timeDto.setExDate1(exDate1);
		timeDto.setExDate2(exDate2);
		timeDto.setExTime1_1("09:00");
		timeDto.setExTime1_2("11:00");
		timeDto.setExTime2_1("11:00");
		timeDto.setExTime2_2("13:00");
		timeDto.setExTime3_1("13:00");
		timeDto.setExTime3_2("15:00");

		return timeDto;
	}

	/*
	 * // 포인트 더미용 public PointDto createPoint() { PointDto pDto = new PointDto();
	 * 
	 * pDto.setId_FK("tester123"); pDto.setPoint(1200);
	 * pDto.setPointDate("20220518");
	 * 
	 * return pDto; }
	 */

}