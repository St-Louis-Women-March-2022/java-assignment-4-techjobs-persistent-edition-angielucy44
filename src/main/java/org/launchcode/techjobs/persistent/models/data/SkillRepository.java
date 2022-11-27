package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Added repository annotation
//this repository extends from SpringData CrudRepository interface
@Repository
public interface SkillRepository extends CrudRepository <Skill, Integer>{
}
