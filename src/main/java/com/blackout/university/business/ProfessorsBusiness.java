package com.blackout.university.business;

import com.blackout.university.dto.professors.ProfessorsDetailmentDTO;
import com.blackout.university.dto.professors.ProfessorsRegistrationDTO;
import com.blackout.university.dto.professors.ProfessorsUpdateDTO;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Professors;
import com.blackout.university.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorsBusiness {

    @Autowired
    private ProfessorsRepository repository;

    public ProfessorsDetailmentDTO storeProfessor(ProfessorsRegistrationDTO professorsRegistrationDTO){
        Professors newProfessor = new Professors(professorsRegistrationDTO);
        repository.save(newProfessor);

        return new ProfessorsDetailmentDTO(newProfessor);
    }

    public List<Professors> searchForAllProfessors(){
        return repository.findAll();
    }

    public Professors searchProfessorById(Long id) throws NotFoundResourceException {
        Optional<Professors> professor = repository.findById(id);

        if(professor.isPresent()){
            return professor.get();
        } else {
            throw new NotFoundResourceException("Cannot find professor with ID: " + id);
        }
    }

    public Professors updateProfessor(Long id, ProfessorsUpdateDTO professorsUpdateDTO) throws NotFoundResourceException {
        Optional<Professors> professor = repository.findById(id);

        if(professor.isPresent()){
            professor.get().updateProfessor(professorsUpdateDTO);
            repository.save(professor.get());
            return professor.get();
        } else {
            throw new NotFoundResourceException("Cannot find professor with ID: " + id);
        }
    }

    public void deleteProfessor(Long id) throws NotFoundResourceException {
        Optional<Professors> professor = repository.findById(id);
        if (professor.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundResourceException("Cannot find professor with ID: " + id);
        }
    }

}
