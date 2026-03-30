package com.hotel.app.dto;

public class HotelDto {
    private String nameHotel;
    private String idHotel;
    private String administratorHotel;

    public String getNameHotel() {
        return name;
    }

    public String getIdHotel() {
        return id;
    }
    public String getAdministratorHotel() {
        return adminstratorHotel;
    }
    public void setNameHotel(String nameHotel) {
        this.nameHotel =nameHotel;
    }
    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }
    public void setAdministratorHotel(String administratorHotel) {
        this.administratorHotel = administratorHotel;
    }
}
