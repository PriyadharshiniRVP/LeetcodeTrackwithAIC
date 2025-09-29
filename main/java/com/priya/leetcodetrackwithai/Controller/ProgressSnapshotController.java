package com.priya.leetcodetrackwithai.Controller;

import com.priya.leetcodetrackwithai.model.ProgressSnapshot;
import com.priya.leetcodetrackwithai.service.ProgressSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressSnapshotController {

    @Autowired
    private ProgressSnapshotService service;

    @PostMapping("/add")
    public ResponseEntity<ProgressSnapshot> addSnapshot(@RequestBody ProgressSnapshot snapshot) {
        ProgressSnapshot saved = service.addSnapshot(snapshot);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProgressSnapshot>> getUserProgress(@PathVariable String username) {
        List<ProgressSnapshot> snapshots = service.getProgressByUsername(username);
        return ResponseEntity.ok(snapshots);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Backend is running!");
    }
}
