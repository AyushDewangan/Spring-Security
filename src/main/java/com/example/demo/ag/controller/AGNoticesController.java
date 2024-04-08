package com.example.demo.ag.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGNotice;
import com.example.demo.ag.repository.AGNoticeRepository;

@RestController
@RequestMapping("/ag")
public class AGNoticesController {

    @Autowired
    private AGNoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<AGNotice>> getNotices() {
//        List<AGNotice> notices = noticeRepository.findAllActiveNotices();
    	List<AGNotice> notices = noticeRepository.findAll();
        if (notices != null ) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }else {
            return null;
        }
    }
    
    @PostMapping("/notices/save")
    public ResponseEntity<AGNotice> saveNotices(@RequestBody AGNotice agNotice) {
//        List<AGNotice> notices = noticeRepository.findAllActiveNotices();
    	AGNotice notices = noticeRepository.save(agNotice);
        if (notices != null ) {
            return ResponseEntity.ok().body(agNotice);
        }else {
            return null;
        }
    }

}
