/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author admin
 */
import classes.*;

import java.util.Scanner;
public class MainAplikasiKasir {
    public DaftarMenu daftarMenu;
    
    public static double PAJAK_PPN = 0.10;
    public static double BIAYA_SERVICE = 0.05;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String no_transaksi, nama_pemesan, tanggal, no_meja = "";
        String transaksi_lagi = "", pesan_lagi = "", keterangan = "", makan_ditempat;
        int jumlah_pesanan, no_menu;
    
        MainAplikasiKasir app = new MainAplikasiKasir();
        app.generateDaftarMenu();}
    
    public void generateDaftarMenu() {
        daftarMenu = new DaftarMenu();
        daftarMenu.tambahMenu(new Ramen("Ramen Seafood", 25000));
        daftarMenu.tambahMenu(new Ramen("Ramen Original", 18000));
        daftarMenu.tambahMenu(new Ramen("Ramen Vegetarian", 22000));
        daftarMenu.tambahMenu(new Ramen("Ramen Karnivor", 28000));
        daftarMenu.tambahMenu(new Kuah("Kuah Orisinil"));
        daftarMenu.tambahMenu(new Kuah("Kuah Internasional"));
        daftarMenu.tambahMenu(new Kuah("Kuah Spicy Lada"));
        daftarMenu.tambahMenu(new Kuah("Kuah Soto Padang"));
        daftarMenu.tambahMenu(new Toping("Wiskas", 7000));
        daftarMenu.tambahMenu(new Toping("Mesis Sereal", 10000));
        daftarMenu.tambahMenu(new Toping("Pur Ayam", 5000));
        daftarMenu.tambahMenu(new Toping("Blue Cheese", 50000));
        daftarMenu.tambahMenu(new Minuman("Jamu Kuat", 10000));
        daftarMenu.tambahMenu(new Minuman("Sake", 80000));
        daftarMenu.tambahMenu(new Minuman("Shochu", 70000));
        daftarMenu.tambahMenu(new Minuman("Energen", 10000));
    
        daftarMenu.tampilDaftarMenu();}
}