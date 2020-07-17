package com.example.springcloudcontractdemo.contracts;

import java.nio.charset.StandardCharsets;
import com.example.springcloudcontractdemo.controller.PostController;
import com.example.springcloudcontractdemo.model.Post;
import com.example.springcloudcontractdemo.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * PostsBase
 */

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class PostsBase {
  @BeforeEach
  public void setUp(final RestDocumentationContextProvider restDocumentationContextProvider,
      final TestInfo testInfo) {

    final PostService postService = Mockito.mock(PostService.class);

    BDDMockito.given(postService.create(Mockito.any(Post.class)))
        .willAnswer(args -> args.getArgument(0, Post.class).setId(1L));
    BDDMockito.given(postService.findById(Mockito.anyLong()))
        .willReturn(new Post().setId(1L).setTitle("test post title").setContent("test post title"));

    RestAssuredMockMvc.standaloneSetup(MockMvcBuilders

        .standaloneSetup(new PostController(postService))
        // .addFilter((request, response, chain) ->{
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        // })
        .apply(
            MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider))
        .alwaysDo(MockMvcRestDocumentation.document("{method-name}/", PayloadDocumentation.requestFields())));
  }
}
