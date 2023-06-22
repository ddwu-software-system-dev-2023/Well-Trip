package com.project.welltrip.controller;

import com.project.welltrip.dto.CountryInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CountryInfoController {

    @GetMapping("/countryInfoView")
    public ModelAndView getCountryInfoVIew() {
        ModelAndView mav = new ModelAndView("countryInfo");
        mav.addObject(new CountryInfo());
        return mav;
    }

    @GetMapping("/countryInfo")
    public ModelAndView getCountryInfo(@RequestParam(required=false) String country) {

        ModelAndView modelAndView = new ModelAndView("countryInfo");
        String result = "";

        try{
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=nv8fL9MqAtpEJC%2FwuWpgJBTW%2BSnzZe6zMdLV5uOqKTNCGWs8jklCX%2B3gAl9pnZJf2P5XWKJhapiCalnEao80rg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode(country, "UTF-8")); /*한글 국가명*/

            URL url = new URL(urlBuilder.toString());
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);

            JSONArray data = (JSONArray)jsonObject.get("data");

            List<CountryInfo> infoList = new ArrayList<>();

            for(int i=0; i<data.size(); i++) {
                JSONObject tmp = (JSONObject) data.get(i);

                CountryInfo infoObj = null;
             // json으로 접근한 country_nm의 값이 사용자가 입력한 country랑 같다면

                infoObj = new CountryInfo((String) tmp.get("country_eng_nm"),
                        (String) tmp.get("country_nm"),
                        (String) tmp.get("txt_origin_cn"),
                        (String) tmp.get("wrt_dt"));

                // mapping한 info를 List 형식으로 저장해줌.
                infoList.add(infoObj);
            }
            // mav 객체에 infoList(json을 매핑한 info들의 배열)을 object로 넣어줌.
            modelAndView.addObject("infoList", infoList);
            System.out.println(infoList.size());//
        }catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}

