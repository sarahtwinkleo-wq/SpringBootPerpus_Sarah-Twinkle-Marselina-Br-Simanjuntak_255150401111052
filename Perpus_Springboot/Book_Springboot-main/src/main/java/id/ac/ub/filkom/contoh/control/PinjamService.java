package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.TransaksiPinjamRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PinjamService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    public PinjamService(
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository,
            TransaksiPinjamRepository transaksiRepository) {

        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.transaksiRepository = transaksiRepository;
    }

    @Transactional
    public TransaksiPinjam pinjamBuku(
            String idAnggota,
            String idBuku,
            int jumlah) {

        // Validasi jumlah buku
        if (jumlah <= 0) {
            throw new PinjamGagalException(
                    "Jumlah buku yang dipinjam harus lebih dari 0");
        }

        // Cari anggota
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> new PinjamGagalException(
                        "Anggota tidak ditemukan"));

        // Cari buku
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> new PinjamGagalException(
                        "Buku tidak ditemukan"));

        // Validasi stok
        if (buku.getStok() < jumlah) {
            throw new PinjamGagalException(
                    "Stok buku tidak mencukupi");
        }

        // Kurangi stok buku
        buku.setStok(buku.getStok() - jumlah);

        // Simpan perubahan stok
        bukuRepository.save(buku);

        // Buat transaksi peminjaman
        TransaksiPinjam transaksi = new TransaksiPinjam(
                anggota,
                buku,
                jumlah,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                "DIPINJAM"
        );

        // Simpan transaksi
        return transaksiRepository.save(transaksi);
    }
}