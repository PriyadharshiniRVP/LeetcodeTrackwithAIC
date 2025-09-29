package com.priya.leetcodetrackwithai.service;



import com.priya.leetcodetrackwithai.model.ProgressSnapshot;
import com.priya.leetcodetrackwithai.repository.ProgressSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgressSnapshotService {

    @Autowired
    private ProgressSnapshotRepository repository;

    public List<ProgressSnapshot> getProgressByUsername(String username) {
        return repository.findByUsername(username);
    }

    public ProgressSnapshot addSnapshot(ProgressSnapshot snapshot) {
        return repository.save(snapshot);
    }
}
