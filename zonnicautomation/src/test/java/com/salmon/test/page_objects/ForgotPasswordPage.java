package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class ForgotPasswordPage extends PageObject {
    public static final By PASSWORD_RESET_HEADER = By.cssSelector(".bat-form.bat-form--request-password-reset .bat-form-block .bat-form-block-text h3");
    public static final By PASSWORD_RESET_TEXT = By.cssSelector(".bat-form.bat-form--request-password-reset .bat-form-block .bat-form-block-text p");
    public static final By PASSWORD_RESET_ERROR = By.cssSelector(".bat-form-field.bat-form--password-email .bat-message.bat-form-msg.error-msg.error");
    public static final By PASSWORD_RESET = By.cssSelector(".bat-form-field.bat-form--password-email input");
    public static final By PASSWORD_RESET_BUTTON = By.cssSelector(".bat-form-field.bat-form--password-submit button");
    public static final By CREATE_NEW_PASSWORD_HEADER = By.cssSelector(".bat-form.bat-form--create-new-password div[class='bat-form-block-text'] h3");
    public static final By CREATE_NEW_PASSWORD_TEXT = By.cssSelector(".bat-form.bat-form--create-new-password div[class='bat-form-block-text'] p");
    public static final By CREATE_NEW_PASSWORD_TEXT2 = By.cssSelector(".bat-form-field.bat-form--password-messages p");
    public static final By CREATE_NEW_PASSWORD_EMAIL_HEADER = By.cssSelector(".bat-form-field.bat-form--password-email label");
    public static final By CREATE_NEW_PASSWORD_PASSWORD_HEADER = By.cssSelector(".bat-form-field.bat-form--password-password label");
    public static final By EMAIL_TEXT_BOX = By.cssSelector(".bat-form-field.bat-form--password-email input");
    public static final By ENTER_PASSWORD_TEXT_BOX = By.cssSelector(".bat-form-field.bat-form--password-password input");
    public static final By EMAIL_TEXT_BOX_ERROR = By.cssSelector(".bat-form-field.bat-form--password-email .bat-message.bat-form-msg.error-msg.error");
    public static final By PASSWORD_TEXT_BOX_ERROR = By.cssSelector(".bat-form-field.bat-form--password-password .bat-message.bat-form-msg.error-msg.error");
    public static final By RE_ENTER_PASSWORD_TEXT_BOX = By.xpath("//label[@for='pwdresetConfirmPassword']/parent::div//input");
    public static final By UPDATE_PASSWORD_BUTTON = By.cssSelector(".bat-form.bat-form--create-new-password button");
    public static final By CANT_RESET_PASSWORD_ERROR = By.cssSelector(".bat-messagebar--zonnic-message-error.active");
}
