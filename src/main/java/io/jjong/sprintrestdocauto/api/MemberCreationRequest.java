
package io.jjong.sprintrestdocauto.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.lang.reflect.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/06/03. create by IntelliJ IDEA.
 *
 * <p> 회원 생성 요청 DTO </p>
 * TODO 사용자 인풋 필드들은 변경될 가능성이 있습니다. 2022.06.20 - by. Yeonha.
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class MemberCreationRequest {

  /**
   * 이메일.
   */
  @Email(message = "A \"email\" field is required email format.")
  @NotBlank(message = "A \"email\" field is missing or its value is blank.")
  private String email;


  /**
   * 이름.
   */
  @NotBlank(message = "A \"name\" field is missing or its value is blank.")
  private String name;

  /**
   * 휴대폰 번호.
   */
  @NotBlank(message = "A \"phone_number\" field is missing or its value is blank.")
  private String phoneNumber;

  public MemberCreationRequest(String email, String name, String phoneNumber) {
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
