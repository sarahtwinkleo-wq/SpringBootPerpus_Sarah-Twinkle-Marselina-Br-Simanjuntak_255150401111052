package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;

public class Bukti {

    private final TransaksiPinjam transaksi;

    public Bukti(TransaksiPinjam transaksi) {
        this.transaksi = transaksi;
    }

    public TransaksiPinjam getTransaksi() {
        return transaksi;
    }
}