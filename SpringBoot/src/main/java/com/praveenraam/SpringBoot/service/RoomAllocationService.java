package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.model.RoomStudent;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import com.praveenraam.SpringBoot.repository.RoomRepository;
import com.praveenraam.SpringBoot.repository.RoomStudentRepository;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomAllocationService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomStudentRepository roomStudentRepository;

    @Autowired
    private HostelRepository hostelRepository;


    public List<Room> getAllVacantRooms(Long hostelId) {
        List<Room> listOfRooms = roomRepository.findAll();

        List<Room> listOfRoomInHostel = listOfRooms.stream().filter(
                room -> room.getHostel().getId().equals(hostelId)
        ).toList();

        List<Room> listOfRoomWithVacancies = new ArrayList<>();

        for(Room room : listOfRoomInHostel){
            if(room.getAvailableBeds() > 0)
                listOfRoomWithVacancies.add(room);
        }

        return listOfRoomWithVacancies;
    }

    public String bookRoom(Long roomId, Long studentId) {

        Room room = roomRepository.findById(roomId).orElse(null);

        if(room == null) return "Room not found";
        if(room.getAvailableBeds() <= 0){
            return "No vacancy in room";
        }

        Optional<RoomStudent> existingRoomStudent = roomStudentRepository.findByStudentId(studentId);

        if(existingRoomStudent.isPresent()) this.deleteStudentCurrentRoom(studentId);

        Student student = studentRepository.findById(studentId).orElse(null);
        RoomStudent roomStudent = new RoomStudent(room,student);

        roomStudentRepository.save(roomStudent);

        room.setAvailableBeds(room.getAvailableBeds()-1);
        roomRepository.save(room);

        return "Room booked successfully";
    }

    public String bookRoom(RoomStudent roomStudent){

        Long roomId = roomStudent.getRoom().getId();
        Long studentId = roomStudent.getStudent().getId();

        Room room = roomRepository.findById(roomId).orElse(null);
        if(room == null) return "Room not found";

        Student student = studentRepository.findById(studentId).orElse(null);

        Optional<RoomStudent> existingRoomStudent = roomStudentRepository.findByStudentId(studentId);
        if(existingRoomStudent.isPresent()) return updateCurrRoom(existingRoomStudent.get(),room);

        roomStudentRepository.save(roomStudent);

        roomStudent.setAssignedDate(LocalDate.now());
        room.setAvailableBeds(room.getAvailableBeds()-1);
        roomRepository.save(room);

        Hostel hostel = room.getHostel();
        hostel.setTotalVacancy(hostel.getTotalVacancy()-1);
        hostelRepository.save(hostel);

        return "Room booked successfully";
    }

    public String updateCurrRoom(RoomStudent roomStudent,Room newRoom){

        Room oldRoom = roomStudent.getRoom();

        roomStudent.setRoom(newRoom);
        roomStudent.setAssignedDate(LocalDate.now());
        roomStudentRepository.save(roomStudent);

        if(oldRoom != null){
            oldRoom.setAvailableBeds(oldRoom.getAvailableBeds()+1);
            roomRepository.save(oldRoom);

            Hostel hostel = oldRoom.getHostel();
            hostel.setTotalVacancy(hostel.getTotalVacancy()+1);
            hostelRepository.save(hostel);
        }

        newRoom.setAvailableBeds(newRoom.getAvailableBeds()-1);
        roomRepository.save(newRoom);

        Hostel hostel = newRoom.getHostel();
        hostel.setTotalVacancy(hostel.getTotalVacancy()-1);
        hostelRepository.save(hostel);

        return "Room Changed Successfully";
    }

    public String deleteStudentCurrentRoom(Long studentId){

        RoomStudent roomStudent = roomStudentRepository.findByStudentId(studentId).orElse(null);
        if(roomStudent == null) return "Student current not in any rooms";

        Room room = roomStudent.getRoom();
        room.setAvailableBeds(room.getAvailableBeds()+1);
        roomRepository.save(room);

        roomStudentRepository.delete(roomStudent);
        return "Successfully removed";
    }

    public String deleteStudentFromRoom(Long roomId){

        List<RoomStudent> listOfRoomStudent = roomStudentRepository.findByRoomId(roomId);

        for(RoomStudent roomStudent : listOfRoomStudent){
            deleteStudentCurrentRoom(roomStudent.getStudent().getId());
        }

        return "Successfully removed";
    }

    public Page<Room> getRoomPaginated(Long hostelId, Pageable pageable) {

        List<Room> vacantRoomList = roomRepository.findAll().stream()
                .filter(room -> room.getHostel().getId().equals(hostelId))
                .filter(room -> room.getAvailableBeds() > 0)
                .toList();

        int pageSize = pageable.getPageSize();
        int currPage = pageable.getPageNumber();

        int startItem = pageSize*currPage;

        List<Room> forPaginated;
        if(vacantRoomList.size() < startItem){
            forPaginated = List.of();
        }
        else{
            int toItem = Math.min(startItem+pageSize,vacantRoomList.size());
            forPaginated = vacantRoomList.subList(startItem,toItem);
        }

        return new PageImpl<>(forPaginated,pageable,vacantRoomList.size());
    }
}
