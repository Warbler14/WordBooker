package com.lotus.jewel.booker.word.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lotus.jewel.booker.http.Result;
import com.lotus.jewel.booker.word.model.Word;
import com.lotus.jewel.booker.word.service.WordService;


@Controller
@RequestMapping("/word")
public class WordController {

	private final static Logger logger = LoggerFactory.getLogger(WordController.class);
	
	private final static String SUB_PATH = "word/";
	
	@Autowired
	WordService wordService;
	
	@GetMapping("boardList")
	public ModelAndView boardList(Word word, Model model) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "board");
		
		int total = wordService.getCountWord();
		word.setTotal(total);
		word.calcPage();
		
		
		model.addAttribute("paging", word.getPaging());
		
		try {
			List<Word> wordList = wordService.getWordListForPage(word);
			model.addAttribute("wordList", wordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@GetMapping("section")
	public ModelAndView section(Word word, Model model) {
		
		ModelAndView mav = new ModelAndView(SUB_PATH + "section");
		
		String keyWord = word.getWord();
		logger.info("keyWord " + keyWord);
		
		Word wordDetail = wordService.getWord(word.getWord());
		
		model.addAttribute("wordDetail", wordDetail);
		
		return mav;
	}
	
	@GetMapping("/putWord")
	public @ResponseBody Result<Map<String, String>> putWord(HttpServletRequest reqest) throws IOException {
		
		String text = reqest.getParameter("text");
		
		Word word = new Word();
		word.setWord(text);
		
		wordService.putWord(word);
		
		Map<String, String> test = new HashMap<>();
		test.put("wod", word.toString());
		
		return new Result<Map<String, String>>(test);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Result<Map<String, String>> post(HttpServletRequest reqest,
			@ModelAttribute Word word
//			@RequestParam Map<String,String> map
			) throws IOException {
		
		String formMethod = reqest.getParameter("formMethod");
		
		logger.info("formMethod : " +  formMethod);
		
		if("PUT".equals(formMethod)) {
			wordService.putWord(word);
			
		} else if("DELETE".equals(formMethod)) {
			wordService.removeWord(word);
		}
		
		
//		String keyWord = wordDetail.getWord().getWord();
//		logger.info("keyWord " + keyWord);
		
		Map<String, String> test = new HashMap<>();
		test.put("message", new Date().toString());
		
		return new Result<Map<String, String>>(test);
	}
	
//	@GetMapping("/data")
//	public @ResponseBody Result<Map<String, String>> data(Word word) throws IOException {
//		
//		String keyWord = word.getWord();
//		logger.info("keyWord " + keyWord);
//		
//		Map<String, String> test = new HashMap<>();
//		test.put("message", new Date().toString());
//		
//		return new Result<Map<String, String>>(test);
//	}
	
}
