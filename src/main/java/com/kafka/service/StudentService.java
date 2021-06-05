package com.kafka.service;

import com.kafka.Entity.Student;
import com.kafka.model.StudentDTO;
import com.kafka.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    StudentService(StudentRepository studentRepository,
                   ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    public void saveStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        studentRepository.save(student);
    }
}
