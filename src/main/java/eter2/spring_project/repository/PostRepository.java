package eter2.spring_project.repository;

import eter2.spring_project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 공고 검색 쿼리
    @Query("SELECT p FROM Post p WHERE " +
            "p.company.name LIKE %:search% OR " +
            "p.company.country LIKE %:search% OR " +
            "p.company.region LIKE %:search% OR " +
            "p.position LIKE %:search% OR " +
            "p.skills LIKE %:search%")
    List<Post> searchPosts(@Param("search") String search);

    // 공고 상세정보 불러오기 시 동일한 회사의 다른 공고 불러오기
    List<Post> findAllByCompanyId(Long companyId);
}
