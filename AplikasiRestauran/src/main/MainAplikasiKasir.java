/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import classes.DaftarMenu;
import classes.Kuah;
import classes.Menu;
import classes.Minuman;
import classes.Pesanan;
import classes.Ramen;
import classes.Toping;
import classes.Transaksi;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class MainAplikasiKasir {
    public DaftarMenu daftarMenu;

    public static final double PAJAK_PPN = 0.10;
    public static final double BIAYA_SERVICE = 0.05;

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String no_transaksi, nama_pemesan, tanggal, no_meja = "";
        String transaksi_lagi = "", pesan_lagi = "", keterangan = "", makan_ditempat;
        int jumlah_pesanan, no_menu;

        MainAplikasiKasir app = new MainAplikasiKasir();
        app.generateDaftarMenu();
        
        System.out.println("========== TRANSAKSI ==========");

      do{
            System.out.println("No Transaksi : ");
            no_transaksi = input.next();
            System.out.println("Pemesan : ");
            nama_pemesan = input.next();
            System.out.println("Tanggal : [dd-mm-yyyy] ");
            tanggal = input.next();
            System.out.println("Makan di tempat? [Y/N] ");
            makan_ditempat = input.next();

            if (makan_ditempat.equalsIgnoreCase("Y")){
                System.out.println("Nomor Meja : ");
                no_meja = input.next();
            }
            
            Transaksi trans = new Transaksi(no_transaksi, nama_pemesan, tanggal, no_meja);
            System.out.println("======== PESANAN ========");
            int no_kuah;
            do {
                //ambil menu berdasarkan nomor urut yang dipilih
                Menu menu_yang_dipilih = app.daftarMenu.pilihMenu();

                jumlah_pesanan = (int) app.cekInputNumber("Jumlah : ");

                //buat pesanan
                Pesanan pesanan = new Pesanan(menu_yang_dipilih, jumlah_pesanan);
                trans.tambahPesanan(pesanan);

                if (menu_yang_dipilih.getKategori().equals("Ramen")){
                    //looping sesuai jumlah pesanan ramen
                    int jumlah_ramen = jumlah_pesanan;
                    do {
                        //ambil objek menu berdasarkan nomor yang dipilih 
                        Menu kuah_yang_dipilih = app.daftarMenu.pilihKuah();

                        System.out.print("Level : [0-5] : ");
                        String level = input.next();

                        //validasi jumlah kuah tidak boleh lebih besar dari jumlah_ramen
                        int jumlah_kuah = 0;
                        do {
                            jumlah_kuah = (int) app.cekInputNumber("Jumlah : ");

                            if (jumlah_kuah > jumlah_ramen){
                                System.out.println("[Err] Jumlah kuah melebihi jumlah ramen yang sudah dipesan");
                            } else {
                                break;
                            }
                        } while (jumlah_kuah > jumlah_pesanan);

                        // set pesanan kuah ke transaksi
                        Pesanan pesanan_kuah = new Pesanan(kuah_yang_dipilih, jumlah_kuah);
                        pesanan_kuah.setKeterangan("Level " + level);

                        //hitung jumlah ramen yang belum dipesan kuahnya
                        jumlah_ramen -= jumlah_kuah;
                    } while (jumlah_ramen > 0);
                } else {
                    //jika keterangan tidak diisi, tulis -
                    System.out.print("Keterangan [- jika kosong] : ");
                    keterangan = input.next();
                }

                //cek jika keterangan diisi selain "-" set ke pesanan
                if (!keterangan.equals("-")){
                    pesanan.setKeterangan(keterangan);
                }

                //konfirmasi, mau tambah pesanan atau tidak
                System.out.print("Tambah Pesanan Lagi? [Y/N] : ");
                pesan_lagi = input.next();
            } while (pesan_lagi.equalsIgnoreCase("Y"));
            
            trans.cetakStruk();

            double total_pesanan = trans.hitungTotalPesanan();
            System.out.println("=============================");
            System.out.println("Total : \t\t" + total_pesanan);

            trans.setPajak(PAJAK_PPN);
            double ppn = trans.hitungPajak();
            System.out.println("Pajak 10% : \t\t" + ppn);

            double biaya_service = 0;
            if (makan_ditempat.equalsIgnoreCase("Y")){
                trans.setBiayaService(BIAYA_SERVICE);
                biaya_service = trans.hitungBiayaService();
                System.out.println("Biaya Service 5% : \t" + biaya_service);
            }
            
            System.out.println("Total : \t\t" + trans.hitungTotalBayar(ppn, biaya_service));
            
             double kembalian = 0;
            do {
                double uang_bayar = app.cekInputNumber("Uang Bayar : \t\t");

                kembalian = trans.hitungKembalian(uang_bayar);
                if (kembalian < 0){
                    System.out.println("[Err] Uang anda kurang");
                } else {
                    System.out.println("Kembali : \t\t" + kembalian);
                    break;
                }
            } while (kembalian < 0);

            System.out.println("Lakukan Transaksi Lagi? [Y/N]");
            transaksi_lagi = input.next();

        }while (transaksi_lagi.equalsIgnoreCase("Y"));
        System.out.println("====== TERIMA KASIH ======");
    }

    

   
    public void generateDaftarMenu(){
        daftarMenu = new DaftarMenu();

        daftarMenu.tambahMenu(new Ramen("Ramen Seafood", 25000));
        daftarMenu.tambahMenu(new Ramen("Ramen Original", 18000));
        daftarMenu.tambahMenu(new Ramen("Ramen Vegetarian", 22000));
        daftarMenu.tambahMenu(new Ramen("Ramen Karnivor", 28000));

        daftarMenu.tambahMenu(new Kuah("Kuah Orisinil"));
        daftarMenu.tambahMenu(new Kuah("Kuah Internasional"));
        daftarMenu.tambahMenu(new Kuah("Kuah Spicy Lada"));
        daftarMenu.tambahMenu(new Kuah("Kuah Soto Padang"));

        daftarMenu.tambahMenu(new Toping("Crab Stick Bakar", 6000));
        daftarMenu.tambahMenu(new Toping("Chicken Katsu", 8000));
        daftarMenu.tambahMenu(new Toping("Gyoza Goreng", 4000));
        daftarMenu.tambahMenu(new Toping("Bakso Goreng", 7000));
        daftarMenu.tambahMenu(new Toping("Enoki Goreng", 5000));

        daftarMenu.tambahMenu(new Minuman("Jus Alpukat SPC", 10000));
        daftarMenu.tambahMenu(new Minuman("Jus Stroberi", 11000));
        daftarMenu.tambahMenu(new Minuman("Capucino Coffee", 15000));
        daftarMenu.tambahMenu(new Minuman("Vietnam Dripp", 14000));

        daftarMenu.tampilDaftarMenu();
    }

    public double cekInputNumber(String label){
        try {
            Scanner get_input = new Scanner(System.in);
            System.out.print(label);
            double nilai = get_input.nextDouble();

            return nilai;
        } catch (InputMismatchException ex){
            System.out.println("[Err] Harap masukkan angka");
            return cekInputNumber(label);
        }
    }
}