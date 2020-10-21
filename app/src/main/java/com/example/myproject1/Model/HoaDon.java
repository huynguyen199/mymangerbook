package com.example.myproject1.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class HoaDon {
    public String maHoadon;
    public String ngaymua;

    public HoaDon() {
    }

    public HoaDon(String maHoadon, String ngaymua) {
        this.maHoadon = maHoadon;
        this.ngaymua = ngaymua;
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("maHoadon", maHoadon);
        result.put("ngaymua", ngaymua);
        return result;
    }
}
