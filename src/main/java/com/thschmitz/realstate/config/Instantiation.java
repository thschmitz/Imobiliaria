package com.thschmitz.realstate.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.thschmitz.realstate.domain.Comment;
import com.thschmitz.realstate.domain.Post;
import com.thschmitz.realstate.domain.User;
import com.thschmitz.realstate.repository.CommentRepository;
import com.thschmitz.realstate.repository.FeedbackRepository;
import com.thschmitz.realstate.repository.PostRepository;
import com.thschmitz.realstate.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		System.out.println("ENTROU AQUI");
		
		feedbackRepository.deleteAll();
		userRepository.deleteAll(); // AQUI TA DELETANDO TUDOOOO
		postRepository.deleteAll();
		commentRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com", "$2a$10$daJmJ.qCBUfFK4LC91C5be5Lcc6tQufVhrLkSDrGKWAA6XnYNlqri", sdf.parse("21/03/2018"), "https://img.freepik.com/fotos-gratis/mulher-jovem-e-elegante-magnifica-com-grandes-olhos-castanhos-e-um-sorriso-incrivel_291049-2575.jpg?w=2000");
		User alex = new User(null, "Alex Green", "alex@gmail.com", "$2a$10$ZGgMHu.o4fxB4lbR1WyYPOQAMC8obpmxI.mYT68JPcjlv.PQ/AsJ2", sdf.parse("21/03/2018"), "https://img.freepik.com/fotos-gratis/homem-bonito-e-confiante-sorrindo-com-as-maos-cruzadas-no-peito_176420-18743.jpg");
		User bob = new User(null, "Bob Grey", "bob@gmail.com", "$2a$10$DN4fGCOugTAtM/.Emwqt/.hwcA25oksByW1.mAOpEcHnjUkdJw6Jq", sdf.parse("21/03/2018"), "https://img.freepik.com/fotos-premium/homem-de-negocios-novo-consideravel-na-camisa-e-nos-monoculos_85574-6228.jpg?w=360");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("12/07/2022"), "Teste01", "Testando um novo post1", "https://a0.muscache.com/im/pictures/monet/Luxury-570973165437649140/original/704468c1-47cd-44e0-9d1a-3ea3db51a2e6?im_w=720", 100.0, 20.3,  maria.getId(), 2, 2, "20.04", "-32.02", "Alugado");
		Post post2 = new Post(null, sdf.parse("23/10/2022"), "Teste02", "Testando um novo post2", "https://a0.muscache.com/im/pictures/c9a5ad04-bd38-4153-b229-d8f751bb418d.jpg?im_w=720", 130.0, 25.0, alex.getId(), 1, 1, "14.04", "-19.97", "Alugado");
		Post post3 = new Post(null, sdf.parse("05/09/2022"), "Teste03", "Testando um novo post3", "https://a0.muscache.com/im/pictures/miso/Hosting-554025175884504726/original/5b49b3a3-ba26-4506-89d8-1a30748f3e61.jpeg?im_w=720", 150.2, 30.5, bob.getId(), 2, 2, "89.065", "24.03", "Alugado");
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		Comment c1 = new Comment(null, "Post1Comment", sdf.parse("21/05/2019"), alex.getId(), post1.getId());
		Comment c2 = new Comment(null, "Post2Comment", sdf.parse("19/07/2020"), bob.getId(), post2.getId());
		Comment c3 = new Comment(null, "Post3Comment", sdf.parse("11/02/2021"), maria.getId(), post3.getId());
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		commentRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
	}
}
