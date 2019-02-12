package edu.spring.myboard.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileUploadService {
  
  private static final String SAVE_PATH = "/upload";
  private static final String PREFIX_URL = "/upload/";
   
  public String restore(MultipartFile multipartFile) {
    String url = null;
     
    try {
      // 파일 정보
      String originFilename = multipartFile.getOriginalFilename();
      String extName
        = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
       
      // 서버에서 저장 할 파일 이름
      String saveFileName = genSaveFileName(originFilename);      
      writeFile(multipartFile, saveFileName);
      
      url = PREFIX_URL + saveFileName;
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return url;
  }
   
   
  // 현재 시간을 기준으로 파일 이름 생성
  private String genSaveFileName(String unoString) {
    String fileName = "";
     
    Calendar calendar = Calendar.getInstance();
    fileName += calendar.get(Calendar.YEAR);
    fileName += calendar.get(Calendar.MONTH);
    fileName += calendar.get(Calendar.DATE);
    fileName += calendar.get(Calendar.HOUR);
    fileName += calendar.get(Calendar.MINUTE);
    fileName += calendar.get(Calendar.SECOND);
    fileName += calendar.get(Calendar.MILLISECOND);
    fileName += unoString;
     
    return fileName;
  }
   
  // 파일을 실제로 write 하는 메서드
  private boolean writeFile(MultipartFile multipartFile, String saveFileName)
                throws IOException{
    boolean result = false;
 
    byte[] data = multipartFile.getBytes();
    FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
    fos.write(data);
    fos.close();
     
    return result;
  }
}