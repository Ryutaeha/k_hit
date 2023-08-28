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
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.Product;

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
		System.out.println(p);
		model.addAttribute("p", p);
		return "seller/updateProductFrm";
	}
}
