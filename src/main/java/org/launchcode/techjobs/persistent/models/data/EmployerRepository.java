package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Added repository annotation
//this repository extends from SpringData CrudRepository interface
//this repository is referenced to send object info through the EmployerController
@Repository
public interface EmployerRepository extends CrudRepository <Employer, Integer>{
}
