package io.jjong.sprintrestdocauto.api;


import static capital.scalable.restdocs.AutoDocumentation.description;
import static capital.scalable.restdocs.AutoDocumentation.embedded;
import static capital.scalable.restdocs.AutoDocumentation.links;
import static capital.scalable.restdocs.AutoDocumentation.methodAndPath;
import static capital.scalable.restdocs.AutoDocumentation.modelAttribute;
import static capital.scalable.restdocs.AutoDocumentation.pathParameters;
import static capital.scalable.restdocs.AutoDocumentation.requestFields;
import static capital.scalable.restdocs.AutoDocumentation.requestParameters;
import static capital.scalable.restdocs.AutoDocumentation.responseFields;
import static capital.scalable.restdocs.AutoDocumentation.section;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * create on 2022/08/11. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = {MemberApi.class})
class MemberApiTest {


  @Autowired
  private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

  private ObjectMapper objectMapper;

  private MockMvc mvc;

  @BeforeEach
  public void setup(WebApplicationContext context, RestDocumentationContextProvider provider) {
    this.objectMapper = new ObjectMapper();
    RestDocumentationResultHandler restDocs = document("{class-name}/{method-name}"
        , preprocessRequest(prettyPrint())
        , preprocessResponse(
            ResponseModifyingPreprocessors.replaceBinaryContent(),
            ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
            prettyPrint())
    );
    this.mvc = initMockMvc(context, provider, restDocs);
  }

  private MockMvc initMockMvc(WebApplicationContext context, RestDocumentationContextProvider provider,
      RestDocumentationResultHandler restDocs) {
    return MockMvcBuilders
        .webAppContextSetup(context)
        .apply(documentationConfiguration(provider)
                .uris().withScheme("http") // default
                .withHost("localhost") // default
                .withPort(8080) // default
//            .and()
//            .operationPreprocessors()
//            .withRequestDefaults(prettyPrint())
//            .withResponseDefaults(prettyPrint())
                .and()
                .snippets().withEncoding("UTF-8") // default
                .withTemplateFormat(TemplateFormats.asciidoctor()) // default
                // auto rest doc
                .withDefaults(curlRequest(),
                    httpRequest(),
                    httpResponse(),
                    requestFields(),
                    responseFields(),
                    pathParameters(),
                    requestParameters(),
                    description(),
                    methodAndPath(),
                    section(),
                    links(),
                    embedded(),
                    modelAttribute(requestMappingHandlerAdapter.getArgumentResolvers())
                )
        )
        .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
        .alwaysDo(restDocs)
        .build();
  }

  final private String baseUrl = "/member";

  @Nested
  class FindById {

    @Test
    void success() throws Exception {
      final Long memberId = 1L;

      mvc.perform(
          get(baseUrl+"/"+memberId)
              .contentType(MediaType.APPLICATION_JSON))
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON),
              jsonPath("$.email").value("jongsang@google.com")
          );
    }
  }


}