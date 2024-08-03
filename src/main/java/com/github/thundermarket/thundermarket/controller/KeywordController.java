package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.aspect.SessionUserParam;
import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import com.github.thundermarket.thundermarket.dto.KeywordRequest;
import com.github.thundermarket.thundermarket.dto.KeywordResponse;
import com.github.thundermarket.thundermarket.service.KeywordCommandHandler;
import com.github.thundermarket.thundermarket.service.KeywordQueryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordController {

    private final KeywordQueryHandler keywordQueryHandler;
    private final KeywordCommandHandler keywordCommandHandler;

    public KeywordController(KeywordQueryHandler keywordQueryHandler, KeywordCommandHandler keywordCommandHandler) {
        this.keywordQueryHandler = keywordQueryHandler;
        this.keywordCommandHandler = keywordCommandHandler;
    }

    @GetMapping
    public ResponseEntity<List<KeywordResponse>> getAllKeywords(@SessionUserParam SessionUser sessionUser) {
        return new ResponseEntity<>(keywordQueryHandler.findAllByUserId(sessionUser.getId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createKeyword(@SessionUserParam SessionUser sessionUser, @RequestBody KeywordRequest keywordRequest) {
        return new ResponseEntity<>(keywordCommandHandler.save(keywordRequest.to(sessionUser.getId())), HttpStatus.OK);
    }

    @DeleteMapping("/{keywordId}")
    public ResponseEntity<String> deleteKeyword(@PathVariable Long keywordId) {
        keywordCommandHandler.delete(keywordId);
        return new ResponseEntity<>("Delete keyword successful", HttpStatus.OK);
    }
}
