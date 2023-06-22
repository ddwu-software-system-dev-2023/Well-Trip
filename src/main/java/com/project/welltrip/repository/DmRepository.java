package com.project.welltrip.repository;

import java.util.List;
import com.project.welltrip.domain.Dm;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DmRepository extends JpaRepository<Dm, Long> {

    List<Dm> findByUser_Id(long userId);

    List<Dm> findByReceiver(String receiver);

    List<Dm> findByReceiverAndUser_Id(String receiver, long userId);


}
