package com.florina.greenpanion.service;

import com.florina.greenpanion.dto.PaperDTO;
import com.florina.greenpanion.model.Paper;
import com.florina.greenpanion.repository.PaperRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperService {
    private final PaperRepository paperRepository;

    public Paper findById(Long id) {
        return paperRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Paper not found: " + id));
    }

    public List<PaperDTO> findAll() {
        List<Paper> papers = new ArrayList<>();
        papers = paperRepository.findAll();

        List<PaperDTO> papersDTO = new ArrayList<>();

        for (Paper paper : papers) {
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setId(paper.getId());
            paperDTO.setPaperType(paper.getName());

            papersDTO.add(paperDTO);
        }
        return papersDTO;
    }

    public PaperDTO create(PaperDTO paperDTO) {
        Paper paper = new Paper();
        paper.setId(paperDTO.getId());
        paper.setName(paperDTO.getPaperType());

        Paper paper1 = paperRepository.save(paper);

        PaperDTO paperDTO1 = new PaperDTO();
        paperDTO1.setId(paper1.getId());
        paperDTO1.setPaperType(paper1.getName());

        return paperDTO1;
    }

    public void delete(long id) {
        paperRepository.deleteById(id);
    }

    public PaperDTO update(Long id, PaperDTO paperDTO) {
        Paper paper = findById(id);
        paperDTO.setId(paper.getId());
        paperDTO.setPaperType(paper.getName());

        Paper paper1 = paperRepository.save(paper);

        PaperDTO paperDTO1 = new PaperDTO();
        paperDTO1.setId(paper1.getId());
        paperDTO1.setPaperType(paper1.getName());

        return paperDTO1;
    }

    public Integer calculatePointsPaper(List<PaperDTO> papers) {
        int sum = 0;
        for (PaperDTO paper : papers) {
            if (paper.getPaperType().name().equals("Carton"))
                sum = sum + 30;
            else if (paper.getPaperType().name().equals("HartieAlba"))
                sum = sum + 10;
            else if (paper.getPaperType().name().equals("RevistaZiar"))
                sum = sum + 15;
        }
        return sum;
    }
}
