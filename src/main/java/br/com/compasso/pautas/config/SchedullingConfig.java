package br.com.compasso.pautas.config;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.compasso.pautas.model.PollSession;
import br.com.compasso.pautas.model.PollStatus;
import br.com.compasso.pautas.repository.PollSessionRepository;
@Configuration
@EnableScheduling
public class SchedullingConfig {
	
	
		@Autowired
		private PollSessionRepository pollSessionRepository;
		
		@Scheduled(fixedRate = 10000)
		@Transactional
		private void schedule() {
			List<PollSession> findByFinishDateLessThan = pollSessionRepository.findByFinishDateLessThan(LocalDateTime.now());
			
			findByFinishDateLessThan.forEach(poll ->{
				poll.getVotes();
				if (poll.countNoVotes() == poll.countYesVotes()) {
					poll.setStatus(PollStatus.DRAW);
				}else if(poll.countNoVotes() > poll.countYesVotes()){
					poll.setStatus(PollStatus.REPROVED);
				}
				else {
					poll.setStatus(PollStatus.APROVED);
				}
				pollSessionRepository.save(poll);
			});
		
		
	}
}
