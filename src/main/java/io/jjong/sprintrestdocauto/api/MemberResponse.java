package io.jjong.sprintrestdocauto.api;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

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
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MemberResponse {

  /**
   * email.
   */
  @NotBlank
  private String email;
  /**
   * member name.
   */
  @NotBlank
  private String name;
  /**
   * phone number.
   */
  @NotBlank
  private String phoneNumber;


  public MemberResponse(@NonNull String email, @NonNull String name, @NonNull String phoneNumber) {
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
