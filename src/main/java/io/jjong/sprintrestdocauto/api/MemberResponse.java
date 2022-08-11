package io.jjong.sprintrestdocauto.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
  private String email;
  /**
   * member name.
   */
  private String name;
  /**
   * phone number.
   */
  private String phoneNumber;


  public MemberResponse(String email, String name, String phoneNumber) {
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
