package indi.yuri.antispam.controller;

import indi.yuri.antispam.service.AntiSpamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yurizhang
 * @date 2020/9/1 17:42
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Resource
    private AntiSpamService antiSpamService;

    @RequestMapping(value = "/antiSpam/string")
    public ResponseEntity antiSpam(@RequestParam(value = "input", required = false) String input){
        return ResponseEntity.ok(antiSpamService.checkBadWords(input));
    }

    @RequestMapping(value = "/badWord/set")
    public ResponseEntity getBadWordSet(@RequestParam(value = "input", required = false) String input){
        return ResponseEntity.ok(antiSpamService.getMatchedString(input));
    }

    @RequestMapping(value = "/ready")
    public ResponseEntity ready(){
        return ResponseEntity.ok("ready");
    }
}
