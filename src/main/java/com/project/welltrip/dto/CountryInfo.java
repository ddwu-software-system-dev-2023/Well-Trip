package com.project.welltrip.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
public class CountryInfo {
    private String country_eng_nm;
    //private String country_iso_alp2;
    private String country_nm;
    //private String html_origin_cn;
    //private String notice_id;
    //private String title;
    private String txt_origin_cn;
    private String wrt_dt;

    public CountryInfo(String country_eng_nm, /*String country_iso_alp2,*/ String country_nm,
                       /*String html_origin_cn, String notice_id, String title,*/
                       String txt_origin_cn, String wrt_dt){
        this.country_eng_nm = country_eng_nm;
        //this.country_iso_alp2 = country_iso_alp2;
        this.country_nm = country_nm;
        //this.html_origin_cn = html_origin_cn;
        //this.notice_id = notice_id;
        //this.title = title;
        this.txt_origin_cn = txt_origin_cn;
        this.wrt_dt = wrt_dt;
    }
}
