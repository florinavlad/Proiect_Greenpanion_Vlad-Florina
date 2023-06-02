package com.florina.greenpanion.service;

import com.florina.greenpanion.dto.PlasticDTO;
import com.florina.greenpanion.model.Plastic;
import com.florina.greenpanion.repository.PlasticRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlasticService {
    private final PlasticRepository plasticRepository;

    public Plastic findById(Long id) {
        return plasticRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Plastic not found: " + id));
    }

    public List<PlasticDTO> findAll() {
        List<Plastic> plastics = new ArrayList<>();
        plastics = plasticRepository.findAll();

        List<PlasticDTO> plasticsDTO = new ArrayList<>();

        for (Plastic plastic : plastics) {
            PlasticDTO plasticDTO = new PlasticDTO();
            plasticDTO.setId(plastic.getId());
            plasticDTO.setPlasticType(plastic.getName());

            plasticsDTO.add(plasticDTO);
        }
        return plasticsDTO;
    }

    public PlasticDTO create(PlasticDTO plasticDTO) {
        Plastic plastic = new Plastic();
        plastic.setId(plasticDTO.getId());
        plastic.setName(plasticDTO.getPlasticType());

        Plastic plastic1 = plasticRepository.save(plastic);

        PlasticDTO plasticDTO1 = new PlasticDTO();
        plasticDTO1.setId(plastic1.getId());
        plasticDTO1.setPlasticType(plastic1.getName());

        return plasticDTO1;
    }

    public void delete(long id) {
        plasticRepository.deleteById(id);
    }

    public PlasticDTO update(Long id, PlasticDTO plasticDTO) {
        Plastic plastic = findById(id);
        plasticDTO.setId(plastic.getId());
        plasticDTO.setPlasticType(plastic.getName());

        Plastic plastic1 = plasticRepository.save(plastic);

        PlasticDTO plasticDTO1 = new PlasticDTO();
        plasticDTO1.setId(plastic1.getId());
        plasticDTO1.setPlasticType(plastic1.getName());

        return plasticDTO1;
    }

    public Integer calculatePointsPlastic(List<PlasticDTO> plastics) {
        int sum = 0;
        for (PlasticDTO plastic : plastics) {
            if (plastic.getPlasticType().name().equals("PET"))
                sum = sum + 30;
            else if (plastic.getPlasticType().name().equals("PVC"))
                sum = sum + 10;
            else if (plastic.getPlasticType().name().equals("PS"))
                sum = sum + 15;
        }
        return sum;
    }
//public Map<String, Integer> calculatePointsPlastic(List<PlasticDTO> plastics) {
//    int sum = 0;
//    for (PlasticDTO plastic : plastics) {
//        if (plastic.getPlasticType().equals("PET"))
//            sum = sum + 30;
//        else if (plastic.getPlasticType().equals("PVC"))
//            sum = sum + 10;
//        else if (plastic.getPlasticType().equals("PS"))
//            sum = sum + 15;
//    }
//
//    Map<String, Integer> response = new HashMap<>();
//    response.put("points", sum);
//
//    return response;
//}



}
