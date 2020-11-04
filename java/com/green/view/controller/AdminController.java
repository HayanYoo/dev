package com.green.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.admin.AdminService;
import com.green.biz.admin.WorkerVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;
import com.green.biz.product.ProductService;
import com.green.biz.product.SalesQuantity;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;
import com.green.biz.utils.Criteria;
import com.green.biz.utils.PageMaker;

@Controller
@SessionAttributes("adminUser")
public class AdminController {

	@Autowired
	private AdminService workerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private QnaService qnaService;

	@RequestMapping(value = "admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}

	@RequestMapping(value = "admin_login")
	public String adminLoginAction(WorkerVO vo, Model model) {
		int result = workerService.workerCheck(vo);

		if (result == 1) {
			WorkerVO adminUser = workerService.getEmployee(vo.getId());
			model.addAttribute("adminUser", adminUser);
			return "redirect:/admin_product_list";

		} else if (result == 0) {
			model.addAttribute("message", "비밀번호를 확인하세요");
			return "admin/main";

		} else {
			model.addAttribute("message", "아이디를 확인하세요");
			return "admin/main";
		}
	}
	/*
	 * 페이징이 적용 안된 제품 목록 조회
	 * 
	 * @RequestMapping(value = "admin_product_list") public String
	 * adminProductList(@RequestParam(value = "key", defaultValue = "", required =
	 * false) String key, HttpSession session, Model model) { WorkerVO adminUser =
	 * (WorkerVO) session.getAttribute("adminUser");
	 * 
	 * if (adminUser == null) { return "admin/main"; } else { List<ProductVO>
	 * prodList = productService.getlistProduct(key);
	 * 
	 * int prodCount = productService.countProductList(key);
	 * 
	 * model.addAttribute("productList", prodList);
	 * model.addAttribute("productListSize", prodCount);
	 * 
	 * return "admin/product/productList"; } }
	 */

	// 페이징 처리가 구현된 주문 목록 조회
	@RequestMapping(value = "admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "", required = false) String key,
			Criteria criteria, HttpSession session, Model model) {
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {
			List<ProductVO> prodList = productService.getListProductPaging(key, criteria);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria);

			int prodCount = productService.countProductList(key);
			pageMaker.setTotalCount(prodCount);
			System.out.println("페이지 정보 : " + pageMaker);

			model.addAttribute("productList", prodList);
			model.addAttribute("productListSize", prodCount);
			model.addAttribute("pageMaker", pageMaker);

			return "admin/product/productList";
		}
	}

	@RequestMapping(value = "admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String[] kindList = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers", "sale" };

		model.addAttribute("kindList", kindList);
		return "admin/product/productWrite";
	}

	@RequestMapping(value = "admin_product_write")
	public String adminProductWriteAction(@RequestParam(value = "product_image") MultipartFile uploadFile, ProductVO vo,
			Model model, HttpSession session) {

		// 관리자 로그인 확인
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {
			String fileName = "";
			if (!uploadFile.isEmpty()) {// 상품 이미지가 업로드 됨
				// 업로드할 파일의 경로를 탐색
				String root_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");

				System.out.println("프로젝트 Root 경로 : " + root_path);
				fileName = uploadFile.getOriginalFilename();

				File file = new File(root_path + fileName);

				try {
					uploadFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			vo.setImage(fileName);

			productService.insertProduct(vo);
			return "redirect:/admin_product_list";
		}
	}

	@RequestMapping(value = "admin_product_detail")
	public String adminProductDetail(ProductVO vo, Criteria criteria, Model model) {
		ProductVO productVO = productService.getProduct(vo);
		String[] kindList = { "", "Heels", "Boots", "Sandals", "Slipers", "Sneakers", "sale" };

		model.addAttribute("productVO", productVO);
		model.addAttribute("criteria", criteria);
		model.addAttribute("kind", kindList[Integer.parseInt(productVO.getKind())]);

		return "admin/product/productDetail";
	}

	@RequestMapping(value = "admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Criteria criteria, Model model) {
		String[] kindList = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers", "sale" };

		ProductVO productVO = productService.getProduct(vo);

		model.addAttribute("kindList", kindList);
		model.addAttribute("productVO", productVO);
		// model.addAttribute("criteria", criteria);
		return "admin/product/productUpdate";

	}

	@RequestMapping(value = "admin_product_update")
	public String adminProductUpdate(@RequestParam(value = "product_image") MultipartFile uploadFile,
			HttpSession session, ProductVO vo) {
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {
			String fileName = "";
			if (!uploadFile.isEmpty()) {// 상품 이미지가 업로드 됨
				// 업로드할 파일의 경로를 탐색
				String root_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");

				System.out.println("프로젝트 Root 경로 : " + root_path);
				fileName = uploadFile.getOriginalFilename();

				File file = new File(root_path + fileName);

				try {
					uploadFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vo.setImage(fileName);
			}

			if (vo.getBestyn() == null) {
				vo.setBestyn("n");
			}

			if (vo.getUseyn() == null) {
				vo.setUseyn("n");
			}

		}

		productService.updateProduct(vo);

		return "redirect:/admin_product_list";
	}

	@RequestMapping(value = "admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete();

		return "admin/main";
	}
/*
	@RequestMapping(value = "admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "", required = false) String key,
			Criteria criteria,
			Model model) {

		List<OrderVO> orderList = orderService.listOrder(key);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";

	}
	*/

	// 페이징 처리가 구현된 주문 목록 조회
		@RequestMapping(value = "admin_order_list")
		public String adminOrderList(@RequestParam(value = "key", defaultValue = "", required = false) String key,
				Criteria criteria, HttpSession session, Model model) {
			WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

			if (adminUser == null) {
				return "admin/main";
			} else {
				List<OrderVO> orderList = orderService.getListOrderPaging(key, criteria);

				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(criteria);

				int orderCount = orderService.countOrderList(key);
				pageMaker.setTotalCount(orderCount);
				System.out.println("페이지 정보 : " + pageMaker);

				model.addAttribute("orderList", orderList);
				model.addAttribute("orderListSize", orderCount);
				model.addAttribute("pageMaker", pageMaker);

				return "admin/order/orderList";
			}
		}

	
	@RequestMapping(value = "admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result") int[] odseq) {
		for (int i = 0; i < odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}

		return "redirect:/admin_order_list";
	}

	/*
	 * 상품별 판매 실적 화면표시
	 */

	@RequestMapping(value = "sales_record_chart",
			produces="application/json; charset=utf-8")
	@ResponseBody	// 이걸 넣어줘야 데이터를 반환함, 화면반환x
	public List<SalesQuantity> salesRecordChart() {
		
		List<SalesQuantity> listSales = productService.getProductSales();
		System.out.println("<<<<< 판매실적 >>>>>");
		System.out.println("   제품명         수량");
		System.out.println("-----------------");
		for (SalesQuantity item : listSales) {
			System.out.printf("%10s%3d\n", item.getPname(), item.getQuantity());
		}
		
		return listSales;
	}

	@RequestMapping(value = "admin_member_list")
	public String adminMemberList(@RequestParam(value = "key", defaultValue = "", required = false) String key,
			Model model) {
		List<MemberVO> memberList = memberService.listMember(key);

		model.addAttribute("memberList", memberList);

		return "admin/member/memberList";
	}

	@RequestMapping(value = "admin_qna_list")
	public String adminQnaList(Model model) {
		List<QnaVO> qnaList = qnaService.listAllQna();

		model.addAttribute("qnaList", qnaList);
		return "admin/qna/qnaList";
	}

	@RequestMapping(value = "admin_qna_detail")
	public String adminQnaDetail(@RequestParam(value = "qseq") int qseq, Model model) {
		QnaVO qnaVO = qnaService.getQna(qseq);

		model.addAttribute("qnaVO", qnaVO);

		return "admin/qna/qnaDetail";

	}

	@RequestMapping(value = "admin_qna_repsave")
	public String adminQnaRepsave(QnaVO vo) {
		qnaService.updateQna(vo);

		return "redirect:/admin_product_list";
	}

	@RequestMapping(value = "admin_sales_record_form")
	public String adminSalesRecordView() {
		return "admin/order/salesRecords";
	}

}
