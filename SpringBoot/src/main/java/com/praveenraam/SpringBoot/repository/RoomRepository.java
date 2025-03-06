package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
