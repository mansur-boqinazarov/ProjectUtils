package uz.pdp.projectutils.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.projectutils.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByName(String name);

    Boolean existsTaskByName(String name);
    @Transactional
    @Modifying
//    @Query(" delete from Task t where t.name=?1 and t.deadLine<?2") // JPQL query
    @Query(
            nativeQuery = true,
            value = " delete from task t where t.name=?1 and t.dead_line>?2"
    )
    void deleteByNameAndDeadLine(String name, LocalDate deadLine);


    @Query(
            nativeQuery = true,
            value = "select t.* from Task t") // JPQL query
    List<Task> getTasksForPage(Pageable pageable);



    Page<Task> findAll(Pageable pageable);
}
