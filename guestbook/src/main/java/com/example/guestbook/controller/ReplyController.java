package com.example.guestbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.guestbook.dto.ReplyDTO;
import com.example.guestbook.service.Board.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  @GetMapping(value="/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {
    log.info("bno: " + bno);

    return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
  }
  
  @PostMapping("")
  public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
    log.info(replyDTO);

    Long rno = replyService.register(replyDTO);

    return new ResponseEntity<>(rno, HttpStatus.OK);
  }

  @DeleteMapping("/{rno}")
  public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
    log.info("RNO : " + rno);

    replyService.remove(rno);

    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @PutMapping("/{rno}")
  public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
    log.info(replyDTO);

    replyService.modify(replyDTO);

    return new ResponseEntity<>("success", HttpStatus.OK);
  }
}
