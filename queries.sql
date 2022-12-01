-- Part 1: Test it with SQL
 id = int PK
 employer = varchar(255)
 name = varchar(255)
 skills = varchar(255)

-- Part 2: Test it with SQL
select name from employer where location = "St. Louis City";

-- Part 3: Test it with SQL
drop table job;
-- Part 4: Test it with SQL
select *
from skill
inner join job_skills on skill_id = job_skills.skills_id
where job_skills.jobs_id is not null
order by name asc;

--part 4 to make sql query work in sql you will need to make line 15 say "on skills_id"
--with skills plural. It has to be singular in order to pass autograder
