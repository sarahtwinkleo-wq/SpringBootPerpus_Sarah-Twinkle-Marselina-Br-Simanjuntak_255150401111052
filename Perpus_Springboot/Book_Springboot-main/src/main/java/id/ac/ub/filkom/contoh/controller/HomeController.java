package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import id.ac.ub.filkom.contoh.service.BukuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final BukuService bukuService;
    private final AnggotaService anggotaService;

    public HomeController(BukuService bukuService, AnggotaService anggotaService) {
        this.bukuService = bukuService;
        this.anggotaService = anggotaService;
    }

    @GetMapping("/")
    public String beranda(@RequestParam(required = false) String kataBuku,
                          @RequestParam(required = false) String kataAnggota,
                          Model model) {
        model.addAttribute("daftarBuku", bukuService.cariBuku(kataBuku));
        model.addAttribute("daftarAnggota", anggotaService.cariAnggota(kataAnggota));
        model.addAttribute("kataBuku", kataBuku);
        model.addAttribute("kataAnggota", kataAnggota);
        return "index";
    }
}