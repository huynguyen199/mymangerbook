package com.example.myproject1.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class HoaDonChiTiet {
    public String maHDCT;
    public String maHoaDon;
    public String maSach;
    public String soLuongMua;


    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHDCT, String maHoaDon, String maSach, String soLuongMua) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public String setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
        return maHDCT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(String soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("maHDCT", maHDCT);
        result.put("maHoaDon", maHoaDon);
        result.put("maSach", maSach);
        result.put("soLuongMua", soLuongMua);
        return result;
    }
}
