package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends JpaRepository<Hostel,Long> {
}
