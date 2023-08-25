package kr.or.iei;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
	public String getFilepath(String savepath, String filename) {
		//예를들어 String filename이 "test.txt"라 가정했을 시
		//test와 .txt 로 분리해야됌
		//"."을 기준으로 0번 인덱스부터 "."앞까지 = test
		String onlyFilename = filename.substring(0,filename.lastIndexOf("."));
		//"."을 기준으로 "."부터 끝까지 = .txt
		String extention = filename.substring(filename.lastIndexOf("."));
		//실제 업로드 할 파일명를 저장할 변수
		String filepath = null;
		//파일명 중복되면 뒤에 붙일 숫자
		int count = 0;
		while(true) {
			if(count==0) {
				//중복된 것이 없을 시
				//파일이름+파일속성 = filename 
				filepath = onlyFilename+extention;
			}else {
				//중복이 있을 시
				//ex) test_1.txt
				filepath = onlyFilename+"_"+count+extention;
			}
			//파일을 저장할 객체에 저장
			//C:/khit/upload/seller/+filepath
			File checkFile = new File(savepath+filepath);
			if(!checkFile.exists()) {
				break;
			}
			count++;
		}
		return filepath;
	}
	public void downloadFile(String savepath, String filename, String filepath, HttpServletResponse response) {
		String downFile = savepath+filepath;
		
		try {
			//파일을 객체로 읽어오기 위한 스트림 생성
			FileInputStream fis = new FileInputStream(downFile);
			//속도개선을 위한 보조스크림 생성
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			//읽어온 파일을 사용자에게 내보낼 스트림 생성
			ServletOutputStream sos = response.getOutputStream();
			//속도개선을 위한 보조스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			
			//파일명처리
			String resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			
			//파일 다운로드를 위한 HTTP 헤더 설정
			//파일이라는 것을 알려줌
			response.setContentType("application/octet-stream");
			//주는 파일이 무엇인지 알려줌
			response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
			
			//파일을 읽어온 뒤 전송
			while(true) {
				int data = bis.read();
				if(data != -1) {
					bos.write(data);
				}else {
					break;
				}
			}
			bis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
