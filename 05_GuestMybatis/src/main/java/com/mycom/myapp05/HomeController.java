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
// @Getter, @Setter, @ToString ��...
@Controller // getter, setter, ������, toString �� �˾Ƽ� �������(lombok)
public class HomeController {
	
	@Resource(name="service") // -> @AutoWired Ȥ�� @Resource �ؼ� �Ʒ� �޼��忡�� ����� service ����� -> import�� �ȵǸ� maven repository���� annotation �����ؼ� dependency �߰��ϱ�
	private GuestServiceImpl service; // ���� bean ��������� �ʾƼ� �� ������
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "insert";
	}
	
	@PostMapping("gInsert") // insert.jsp���� �Է� ��ư Ŭ�� �� �������� �Ѿ��
	public void insert(GuestDTO guest, HttpServletRequest request) { // ip�ּҸ� ���ϱ� ����
		guest.setIpaddr(request.getRemoteAddr()); // ip�ּ� �Է�
		// ���� ���� �θ��� : ���� ����� -> 25~26�� �� �ۼ�
		service.guestInsert(guest); // ���� ���ٰ� -> DAO�� ��
	}
	
	@GetMapping("gList")
	public String list(Model model) {
		List<GuestDTO> guestlist = service.guestList(null);
		model.addAttribute("guestlist", guestlist);
		return "list";
	}
	
	
}
