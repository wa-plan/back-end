package com.example.dodakki.cheering.application;

import com.example.dodakki.cheering.application.dto.CheeringResponse;
import com.example.dodakki.cheering.domain.Cheering;
import com.example.dodakki.cheering.domain.repository.CheeringRepository;
import com.example.dodakki.cheering.exception.CheeringException;
import com.example.dodakki.cheering.exception.CheeringExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheeringService {

    private final CheeringRepository cheeringRepository;

    public CheeringResponse getRandomCheering(){
        Cheering cheering = cheeringRepository.findById((long) (Math.random() * cheeringRepository.count())).orElseThrow(() -> new CheeringException(
            CheeringExceptionType.NOT_FOUND_CHEERING));
        return new CheeringResponse(cheering.getMessage(), cheering.getAuthor());
    }
}
