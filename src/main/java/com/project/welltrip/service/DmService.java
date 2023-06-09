package com.project.welltrip.service;

import java.util.List;
import java.util.Optional;

import com.project.welltrip.domain.Dm;
import com.project.welltrip.repository.DmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DmService {

    private final DmRepository dmRepository;

    public void insertDm(Dm dm) {
        dmRepository.save(dm);
    }

    public Dm getDm(long dmId){
        Optional<Dm> result = dmRepository.findById(dmId);
        if(result.isPresent())
            return result.get();

        return null;

    }

    public List<Dm> getDmByUserID(long userId) {
        List<Dm> result = dmRepository.findByUser_Id(userId);
        if(!result.isEmpty()) {
            return result;
        }
        return null;
    }

    public List<Dm> getDmByReceiver(String receiver) {
        List<Dm> result = dmRepository.findByReceiver(receiver);
        if(!result.isEmpty()) {
            return result;
        }
        return null;
    }

    public void deleteDm(long dmId) {
        dmRepository.deleteById(dmId);
    }

//    public List<Dm> getDmList(String receiver, long userId){
//        List<Dm> result = dmRepository.findByReceiverAndUser_Id(receiver, userId);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;
//    }


}
