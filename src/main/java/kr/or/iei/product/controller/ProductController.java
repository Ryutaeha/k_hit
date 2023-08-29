package kr.or.iei.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
	private String root;
	
	@GetMapping(value="/productDetail")
	public String productDetail() {
		return "/product/productDetail";
	}
	
	@ResponseBody
	@PostMapping(value="/editor",produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
			//System.out.println(image);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "/editor/"+filepath;
	}
	
	@GetMapping(value="/deleteProduct")
	public String deleteProduct(int productNo, Model model) {
		int result = productService.deleteProduct(productNo);
		if(result>0) {
			model.addAttribute("title","판매중지 완료");
			model.addAttribute("msg", "상품이 판매중지 처리되었습니다.");
			model.addAttribute("icon","success");
		}else {
			model.addAttribute("title", "에러 발생");
			model.addAttribute("msg", "관리자에게 문의하세요");
			model.addAttribute("icon","error");
		}
		model.addAttribute("loc","/seller/productManagement?reqPage=1");
		return "common/msg";
			
		}
	
	@GetMapping(value="/updateFrm")
	public String updateFrm(int productNo, Model model) {
		Product p = productService.getProduct(productNo);
		//System.out.println(p); 수정버튼 클릭시 정보 확인용
		model.addAttribute("p", p);
		
		return "seller/updateProductFrm";
	}
	
	@PostMapping(value="/update")
	public String update(Product p, String[] optionColor, String[] optionSize, @SessionAttribute(required = false) Seller s,MultipartFile upfile, Model model) {
		//, int delFileNo 매개변수 지워둠
		String savepath = root+"product/";
		System.out.println(upfile);
		if(upfile.getSize() != 0) {
			
			File delFile = new File(savepath+p.getProductImg());
			delFile.delete();			
			
			String filepath = fileUtil.getFilepath(savepath, upfile.getOriginalFilename());
			//File uploadFile = new File(filepath);
			p.setProductImg(filepath);
			
			File uploadFile = new File(savepath+filepath);
			try {
				upfile.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("상품명 : "+p.getProductName());//null
		System.out.println("가격 : "+p.getProductPrice());
		System.out.println("카테고리번호 : "+p.getCategoryNo());
		System.out.println("판매자번호 : "+s.getSellerNo());
		System.out.println(s.getSellerNo());
		System.out.println("이미지 : "+p.getProductImg());//null
		System.out.println("요약정보 : "+p.getProductContent());
		System.out.println("세부정보 : "+p.getProductContentDetails());		
		System.out.println(p);
		
		for(String size : optionSize) {
			System.out.println(size);
		}
		for(String color : optionColor) {
			System.out.println(color);
		}
		
		int result = productService.updateProduct(p, optionSize, optionColor);
		//delFileNo, 매개변수 지워둠
		if(result>0) {
			model.addAttribute("title", "상품 수정 성공");
			model.addAttribute("msg", "상품 수정이 완료되었습니다.");
			model.addAttribute("icon", "success");
			/*
			int productNo = p.getProductNo();
			System.out.println(p.getProductNo());
			System.out.println(result);
			*/
			/*
			File delFile = new File(savepath+filepath);
			delFile.delete();
			*/
		}else {
			model.addAttribute("title", "상품 수정 실패");
			model.addAttribute("msg", "잠시 후 다시 시도하세요.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/seller/productManagement?reqPage=1");
		return "common/msg";
		
		
		
	}
}
