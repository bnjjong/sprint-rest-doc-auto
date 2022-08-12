package io.jjong.sprintrestdocauto.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberApi {

  /**
   * retrieve member by member id.
   *
   * @param memberId member identity.
   * @return Member response info.
   */
  @GetMapping(value = "/{memberId}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberResponse> findById(
      @PathVariable(value = "memberId") final Long memberId) {

    final MemberResponse memberResponse = new MemberResponse("jongsang@google.com", "JongSang Han",
        "010-222-3333");

    return new ResponseEntity<>(memberResponse, HttpStatus.OK);
  }

  /**
   * create member.
   *
   * @param request
   * @return
   */
  @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberResponse> create(
      @Valid @RequestBody final MemberCreationRequest request) {
    log.info("request : {}", request);

    final MemberResponse memberResponse = new MemberResponse(
        request.getEmail(),
        request.getName(),
        request.getPhoneNumber());

    return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
  }
}
