package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;

//Denne brukes for å opprette paginering på begge html-sidene
@Service
public class ChiliRepository {

    List<Chili> chiliList;

    public ChiliRepository(ChiliRepositoryWithDataJPA chiliRepositoryWithDataJPA) {
        chiliList = (List<Chili>) chiliRepositoryWithDataJPA.findAll();
    }

    public List<Chili> getChiliSubgroup(int page, int pageSize) {
        int from = Math.max(0, page * pageSize);
        int to = Math.min(chiliList.size(), (page + 1) * pageSize);
        return chiliList.subList(from, to);
    }

    public int numberOfPages(int pageSize) {
        return (int) Math.ceil((double) chiliList.size() / pageSize);
    }

    public Chili getChiliById(Integer id) {
        for (Chili chili : chiliList) {
            if (chili.getId().equals(id)) {
                return chili;
            }
        }
        return null;
    }

    public int getSize() { return chiliList.size(); }
}