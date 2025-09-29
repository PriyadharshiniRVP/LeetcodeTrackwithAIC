package com.priya.leetcodetrackwithai.repository;



import com.priya.leetcodetrackwithai.model.ProgressSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgressSnapshotRepository extends JpaRepository<ProgressSnapshot, Long> {
    List<ProgressSnapshot> findByUsername(String username);
}
