package example.com.hellospring;

import example.com.hellospring.domain.Member;
import example.com.hellospring.repository.JpaMemberRepository;
import example.com.hellospring.repository.MemberRepository;
import example.com.hellospring.repository.MemoryMemberRepository;
import example.com.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //JPA를 사용하도록 스프링 설정 변경
    private final DataSource dataSource;
    private final EntityManager em;

    public  SpringConfig(DataSource dataSource, EntityManager em){
        this.dataSource = dataSource;
        this.em = em;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}
