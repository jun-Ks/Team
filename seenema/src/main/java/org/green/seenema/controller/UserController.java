package org.green.seenema.controller;

import java.util.ArrayList;

import org.green.seenema.mapper.ReplyMapper;
import org.green.seenema.user.main.mapper.UserMainMapper;
import org.green.seenema.vo.MovieVO;
import org.green.seenema.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private UserMainMapper mainMapper;
	
	@GetMapping("/main")
	public void main() {}
	
	@GetMapping("/movieDetailView")
	public void movieDetailView(String movieCode, Model model) {
		MovieVO movie = mainMapper.getMovieDetail(movieCode);
		movie.setPlot(movie.getPlot().replaceAll("\n", "<br>"));
		model.addAttribute("movie", movie);
	}
	
	@GetMapping("/getReplyList.do")
	@ResponseBody
	public ArrayList<ReplyVO> getReplyList(int movieCode){
		ArrayList<ReplyVO> list = mapper.getReplyList(movieCode);
		return list;
	}
	
	@PostMapping("/regReply.do")
	@ResponseBody
	public int regReplyDo(ReplyVO reply) {
		int result = mapper.regReply(reply);
		return result;
	}
	
	@PostMapping("/getReplyInfo.do")
	@ResponseBody
	public ReplyVO getReplyInfo(int replyCode) {
		ReplyVO reply = mapper.getReplyInfo(replyCode);
		return reply;
	}
	
	@PostMapping("/updateReply.do")
	@ResponseBody
	public int updateReply(ReplyVO reply) {
		int result = mapper.updateReply(reply);
		log.info(reply.toString());
		return result;
	}
	
	@PostMapping("/deleteReply.do")
	@ResponseBody
	public int deleteReply(int replyCode) {
		int result = mapper.deleteReply(replyCode);
		return result;
	}

	@GetMapping("/header")
	public void header(){
		log.info("헤더");
	}

	@GetMapping("/footer")
	public void footer(){
	}
}
