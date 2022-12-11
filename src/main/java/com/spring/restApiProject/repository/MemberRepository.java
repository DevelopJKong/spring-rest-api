package com.spring.restApiProject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.restApiProject.domain.entity.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    /**
     * - 회원 저장 api
     * 
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * - 회원 검색 api
     * 
     * @param id
     * @return result
     */
    public Member findOne(Long id) {
        Member result = em.find(Member.class, id);
        return result;
    }

    /**
     * - 회원 전체 검색 api
     * 
     * @return results
     */
    public List<Member> findAll() {
        List<Member> results = em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
        return results;
    }

    /**
     * - 회원 이름으로 검색 하는 api
     * 
     * @param name
     * @return results
     */
    public List<Member> findByName(String name) {
        List<Member> results = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return results;
    }

    /**
     * - 회원 이메일으로 회원을 검색하는 api
     * 
     * @param email
     * @return result
     */
    public Member findByEmail(String email) {
        Member result = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
        return result;
    }

}
