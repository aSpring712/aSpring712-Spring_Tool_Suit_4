package com.mycom.myapp05;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myguest.model.GuestDTO;
import com.myguest.model.GuestServiceImpl;

/**
 * Handles requests for the application home page.
 */
// @Getter, @Setter, @ToString 등...
@Controller // getter, setter, 생성자, toString 다 알아서 만들어줌(lombok)
public class HomeController {
	
	@Resource(name="service") // -> @AutoWired 혹은 @Resource 해서 아래 메서드에서 사용할 service 만들기 -> import가 안되면 maven repository에서 annotation 복사해서 dependency 추가하기
	private GuestServiceImpl service; // 아직 bean 만들어주지 않아서 빈 껍데기
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "insert";
	}
	
	@PostMapping("gInsert") // insert.jsp에서 입력 버튼 클릭 시 이쪽으로 넘어옴
	public void insert(GuestDTO guest, HttpServletRequest request) { // ip주소를 구하기 위해
		guest.setIpaddr(request.getRemoteAddr()); // ip주소 입력
		// 서비스 먼저 부르기 : 서비스 만들기 -> 25~26번 줄 작성
		service.guestInsert(guest); // 서비스 갔다가 -> DAO로 감
	}
	
	@GetMapping("gList")
	public String list(Model model) {
		List<GuestDTO> guestlist = service.guestList(null);
		model.addAttribute("guestlist", guestlist);
		return "list";
	}
	
	
}
