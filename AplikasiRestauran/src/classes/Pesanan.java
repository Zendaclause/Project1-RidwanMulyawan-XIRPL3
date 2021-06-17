/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author admin
 */
public class Pesanan {
    private Menu menu;
    private int jumlah;
    private String keterangan;
    
    public Pesanan(Menu menu, int jumlah){
        this.menu = menu;
        this.jumlah = jumlah;
    }


    public void setKeterangan(String keterangan){
        
    }
    
    public String getKeterangan(){
        return "";
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    

    public int getJumlah() {
        return jumlah;
    }


    
}