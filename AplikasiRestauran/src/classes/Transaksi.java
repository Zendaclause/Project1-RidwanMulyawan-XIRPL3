/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Transaksi {
    private String noTransaksi, namaPesanan, tanggal, noMeja;
    private ArrayList<Pesanan> pesanan;
    private double uangBayar, pajak, totalBayar;
    private double biayaService = 0;


    public Transaksi(String no_transaksi, String nama_pemesan, String tanggal, String no_meja){
        this.noTransaksi = no_transaksi;
        this.namaPesanan = nama_pemesan;
        this.tanggal = tanggal;
        this.noMeja = no_meja;

        pesanan = new ArrayList<>();
    }
    
    public void tambahPesanan(Pesanan pesanan){
        this.pesanan.add(pesanan);
    }

    // public Pesanan getPesanan(){
    //     return null;
    // }

    public ArrayList<Pesanan> getSemuaPesanan(){
        return pesanan;
    }
}