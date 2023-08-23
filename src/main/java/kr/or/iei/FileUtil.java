package kr.or.iei;

import java.io.File;

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
}
