package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaksi_pinjam")
public class TransaksiPinjam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi")
    private Long idTransaksi;

    @ManyToOne
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private LocalDate tanggalKembali;

    @Column(name = "status", nullable = false)
    private String status;

    protected TransaksiPinjam() {
    }

    public TransaksiPinjam(
            Anggota anggota,
            Buku buku,
            int jumlah,
            LocalDate tanggalPinjam,
            LocalDate tanggalKembali,
            String status) {

        this.anggota = anggota;
        this.buku = buku;
        this.jumlah = jumlah;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public int getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public String getStatus() {
        return status;
    }
}