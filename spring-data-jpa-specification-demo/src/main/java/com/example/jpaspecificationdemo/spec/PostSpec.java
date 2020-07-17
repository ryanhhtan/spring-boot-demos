package com.example.jpaspecificationdemo.spec;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import com.example.jpaspecificationdemo.model.Post;
import com.example.jpaspecificationdemo.model.Comment;
import org.springframework.data.jpa.domain.Specification;

/**
 * PostSpec
 */
public class PostSpec {

  public static Specification<Post> commentedBy(final String commentorName) {
    return (root, query, cb) -> {
      query.distinct(true);
      ListJoin<Post, Comment> postComment = root.joinList("comments", JoinType.INNER); 
      return cb.like(postComment.get("name"), makeSqlLikeString(commentorName));
    };
  }

  public static Specification<Post> titleContains(final String text) {
    return (root, query, cb) -> cb.like(root.get("title"), makeSqlLikeString(text));
  }

  public static Specification<Post> bodyContains(final String text) {
    return (root, query, cb) -> cb.like(root.get("body"), makeSqlLikeString(text));
  }

  public static String makeSqlLikeString(final String text) {
    return "%" + text + "%";
  }
}
