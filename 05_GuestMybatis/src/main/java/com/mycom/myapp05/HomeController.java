package com.mycom.myapp05;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myguest.model.GuestDTO;
import com.myguest.model.GuestListVO;
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
	@ResponseBody // 진짜 문자열이 return 됨
	public String insert(@RequestBody GuestDTO guest, HttpServletRequest request) { // @RequestBody 를 적어줘야 json 형태로 받을 수 있음
		guest.setIpaddr(request.getRemoteAddr()); // ip주소 입력
		// 서비스 먼저 부르기 : 서비스 만들기 -> 25~26번 줄 작성
		service.guestInsert(guest); // 서비스 갔다가 -> DAO로 감
		return "success";
	}
	
	@GetMapping("gList")
	@ResponseBody
	public GuestListVO list(String field, String word) { // json형태로 return 시키기 때문에 Model 필요없음
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("field", field);
		hm.put("word", word);
		System.out.println(hm);
		List<GuestDTO> guestlist = service.guestList(hm);
		int count = service.guestCount(hm);
		// return 1개만 시킬 수 있는데 guestlist, count를 return 해주어야 함 : 두개를 하나로 묶기 위해 -> GuestListVO.java만들기
		GuestListVO listvo = new GuestListVO(count, guestlist);
		return listvo;
	}
	
	// 상세보기 : jackson 사용 -> 파싱하지않고 전달가능
	@GetMapping("gView")
	@ResponseBody // return 되어지는 guest는 .jsp가 아니기 때문에 적어줌 
	public GuestDTO view(@RequestParam(name="num", required = false) int num) { // num 이라는 이름으로 넘어오는 것을 int 다른 이름으로 쓰겠다 하면 @RequestParam을 써주면 됨(단점 : null값이 오면 안됨! - required false 해주면 null값도 가능, 기본 값은 true)
		GuestDTO guest = service.findByNum(num);
		return guest;
	}
	
//	@GetMapping("gView")
//	@ResponseBody
//	public String view(@RequestParam("num") int num) {
//		GuestDTO guest = service.findByNum(num);
//		JSONObject obj = new JSONObject();
//		obj.put("num", guest.getNum());
//		obj.put("name", guest.getName());
//		obj.put("content", guest.getContent());
//		obj.put("grade", guest.getGrade());
//		obj.put("created", guest.getCreated());
//		obj.put("ipaddr", guest.getIpaddr());
//		return obj.toString(); // 혹은 toJSON
//	}
	
	//삭제
	@DeleteMapping("gDelete/{num}") // 넘어노는 num 값 받아와야 함
	@ResponseBody
	public String delete(@PathVariable int num) { // 경로상에서 값을 받아오는 어노테이션
		service.guestDelete(num);
		return "success";
	}
	
}