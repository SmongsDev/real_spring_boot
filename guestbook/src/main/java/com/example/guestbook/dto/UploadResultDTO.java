package com.example.guestbook.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable{
  
  private String fileName;
  private String uuid;
  private String folderPath;

  public String getImageURL(){
    try{
      return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
    } catch(UnsupportedEncodingException e){
      e.printStackTrace();
    }
    return "";
  }

  // 이렇게 만들어 놓으면 json에 ThumbnailURL로 변수 생김
  public String getThumbnailURL(){
    try{
      return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
    } catch (UnsupportedEncodingException e){
      e.printStackTrace();
    }
    return "";
  }
}
