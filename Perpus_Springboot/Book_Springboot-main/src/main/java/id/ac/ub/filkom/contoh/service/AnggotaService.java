package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> cariAnggota(String kata) {
        if (kata == null || kata.isBlank()) {
            return anggotaRepository.findAll();
        }
        return anggotaRepository.findByNamaContainingIgnoreCase(kata.trim());
    }

    public Anggota tambahAnggota(String idAnggota, String nama, String email) {
        String id = idAnggota.trim();
        if (id.isEmpty() || nama.isBlank()) {
            throw new IllegalArgumentException("ID dan nama wajib diisi");
        }
        if (anggotaRepository.existsById(id)) {
            throw new IllegalArgumentException("ID anggota " + id + " sudah terdaftar");
        }
        String emailBersih = (email == null || email.isBlank()) ? null : email.trim();
        return anggotaRepository.save(new Anggota(id, nama.trim(), emailBersih));
    }
}