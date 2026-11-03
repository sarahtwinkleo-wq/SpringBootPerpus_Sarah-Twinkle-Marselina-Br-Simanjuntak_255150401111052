package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.control.Bukti;
import id.ac.ub.filkom.contoh.control.PinjamGagalException;
import id.ac.ub.filkom.contoh.control.PinjamService;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.TransaksiPinjamRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HalamanPinjamController {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;
    private final PinjamService pinjamService;

    public HalamanPinjamController(
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository,
            TransaksiPinjamRepository transaksiRepository,
            PinjamService pinjamService) {

        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.transaksiRepository = transaksiRepository;
        this.pinjamService = pinjamService;
    }

    @GetMapping("/pinjam")
    public String halamanPinjam(Model model) {

        model.addAttribute(
                "anggotaList",
                anggotaRepository.findAll()
        );

        model.addAttribute(
                "bukuList",
                bukuRepository.findAll()
        );

        return "pinjam";
    }

    @PostMapping("/pinjam")
    public String prosesPinjam(
            @RequestParam String idAnggota,
            @RequestParam String idBuku,
            @RequestParam int jumlah,
            Model model) {

        try {

            TransaksiPinjam transaksi =
                    pinjamService.pinjamBuku(
                            idAnggota,
                            idBuku,
                            jumlah
                    );

            Bukti bukti = new Bukti(transaksi);

            model.addAttribute("bukti", bukti);

            return "bukti";

        } catch (PinjamGagalException e) {

            model.addAttribute("error", e.getMessage());

            model.addAttribute(
                    "anggotaList",
                    anggotaRepository.findAll()
            );

            model.addAttribute(
                    "bukuList",
                    bukuRepository.findAll()
            );

            return "pinjam";
        }
    }
}