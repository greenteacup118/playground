package com.playground.pg.controller;

import java.util.HashMap;
import java.util.Map;

import org.checkerframework.checker.units.qual.m;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.playground.pg.domain.ArtDto;
import com.playground.pg.domain.ArtTimeDto;
import com.playground.pg.domain.ReserveDto;
import com.playground.pg.service.ReserveService;

@Controller	//예약하기 컨트롤러
@RequestMapping("/reserve")
public class ReserveController {
	@Autowired
    ReserveService reserveService;
	
	// 날짜 선택 페이지
	@GetMapping("/select")	// 조회x 선택 select 헷갈릴시 selDay or day로 변경
	public String selectDay(int no, Model m) throws Exception {//작품번호 매개변수로 받기
		
		// 작품번호 이용하여 해당 작품관련 DTO 반환받기(작품제목, 전시장소 표시용)
		ArtDto artDto = reserveService.getArt(no);
		
		// 작품번호 이용하여 해당 작품시간관련 DTO 반환받기
		ArtTimeDto artTimeDto = reserveService.getTime(no);
		
		// 작품번호를 이용하여 해당 작품 예약한 인원 수 반환받기
		int resCnt = reserveService.getResCnt(no);
		
		m.addAttribute("artDto", artDto);
		m.addAttribute("artTimeDto", artTimeDto);
		m.addAttribute("resCnt", resCnt);
		
		return "예매화면(날짜선택)";
		
	}
	
	// 결제 화면 페이지
	@GetMapping("/pay")
	public String payment(int no, String date, String time1, String time2, Model m) throws Exception {
		// 작품번호, 선택한 날짜, 시간 매개변수로 받기
		

		// 작품번호 이용하여 해당 작품관련 DTO 반환받기(작품정보 표시용)		
		ArtDto artDto = reserveService.getArt(no);
		m.addAttribute("artDto", artDto);
		// 선택한 날짜, 시간 모델에 넣기
		m.addAttribute("date",date);
		m.addAttribute("time1",time1);
		m.addAttribute("time2",time2);
		
		return "결제페이지";
	}
	
	// 결제하기
	@PostMapping("/pay") // 결제하기
	public String order(ReserveDto reserveDto) throws Exception {
		// ReserveDto에 작품번호, 선택날짜, 시작,종료시간, 어른매수, 아이매수, 가격, 포인트, 쿠폰 매개변수로 받기
		
		// reserveDTO에 내용 넣은 후 DB에 insert하기
		boolean result = reserveService.insertReserve(reserveDto);
		
		if (result == false) {
			return "redirect:에러페이지";
		}
		return "redirect:결제완료 페이지";
	}
	
}