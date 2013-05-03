/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.controller.demo.test;

import java.text.MessageFormat;

import org.jboss.aerogear.controller.demo.page.CarRegistrationPage;
import org.jboss.aerogear.controller.demo.page.LoginPage;
import org.jboss.aerogear.controller.demo.page.LoginResultPage;
import org.jboss.aerogear.controller.demo.page.RestrictedAdminPage;
import org.jboss.aerogear.controller.demo.page.RestrictedAdminResultsPage;
import org.jboss.aerogear.controller.demo.page.RestrictedDeloreanResultsPage;
import org.jboss.aerogear.controller.demo.page.ResultsPage;
import org.jboss.aerogear.controller.demo.page.UserRegistrationPage;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;

/**
 * The class which contains the test cases for the Aerogear-Controller-Demo.
 *
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 *
 */
public class AerogearControllerDemoTestCase extends AerogearControllerDemoTest {

    /**
     * Injects the car registration page inside the test case.
     */
    @Page
    CarRegistrationPage carRegistrationPage;

    /**
     * Injects the login page inside the test case.
     */
    @Page
    LoginPage loginPage;

    /**
     * Injects the login result page inside the test case.
     */
    @Page
    LoginResultPage loginResultPage;

    /**
     * Injects the user registration page inside the test case.
     */
    @Page
    UserRegistrationPage userRegistrationPage;

    /**
     * Injects the results page inside the test case.
     */
    @Page
    ResultsPage resultsPage;

    /**
     * Injects the restricted delorean results page inside the test case.
     */
    @Page
    RestrictedDeloreanResultsPage restrictedDeloreanResultsPage;

    /**
     * Injects the restricted admin page.
     */
    @Page
    RestrictedAdminPage restrictedAdminPage;

    /**
     * Injects the restricted admin results page.
     */
    @Page
    RestrictedAdminResultsPage restrictedAdminResultsPage;

    /**
     * Specifies the page's path for the test case.
     */
    @Override
    protected String getPagePath() {
        return "";
    }

    /**
     * Tests whether the correct page is opened.
     */
    @Test
    @InSequence(1)
    public void carRegistrationPageTitleTest() {
        // initialize main page
        initializePageUrl();
        // wait until the page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(carRegistrationPage.getCarRegistrationPageHeadingTitle()
                .equals(carRegistrationPage.getHeadingTitle()));
    }

    /**
     * Tests the car's registration functionality.
     */
    @Test
    @InSequence(2)
    public void carRegistrationTest() {
        // initialize main page
        initializePageUrl();
        // register a car with color and brand
        carRegistrationPage.registerHttp(carRegistrationColor, carRegistrationBrand);
        // wait until the result page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getUnrestrictedPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // assert that the text of the first paragraph is correct
        Assert.assertTrue(carRegistrationPage.getCarRegistrationPageBodyMessage().equals(resultsPage.getParagraphText(0)));
        // assert that the text of the second paragraph is correct
        Assert.assertTrue(MessageFormat.format(carRegistrationPage.getCarRegistrationTailMessageFormat(), carRegistrationColor,
                carRegistrationBrand).equals(resultsPage.getParagraphText(1)));
    }

    /**
     * Tests the error handling link.
     */
    @Test
    @InSequence(3)
    public void errorHandlingPageTest() {
        // initialize the main page
        initializePageUrl();
        // wait until the page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // navigate to the error handling page
        carRegistrationPage.navigateToErrorHandlingPage();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getGeneralErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // assert that the paragraph's message is correct
        Assert.assertTrue(resultsPage.getGeneralErrorPageRuntimeMessage().equals(resultsPage.getParagraphText(0)));
    }

    /**
     * Tests the login procedure using wrong credentials.
     */
    @Test
    @InSequence(4)
    public void loginPageWrongCredentialsTest() {
        // login
        login(loginPageWrongUsername, loginPageWrongPassword);
        // wait until the result page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getSecurityErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Tests the registration procedure.
     */
    @Test
    @InSequence(5)
    public void registrationTest() {
        // register user
        registerUser(userRegistrationUsername_1, userRegistrationPassword_1);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the 2 div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), userRegistrationUsername_1)));
        // logout
        loginResultPage.logoutHttp();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getLoggedOutPageTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Tests the login procedure using correct credentials.
     */
    @Test
    @InSequence(6)
    public void loginPageCorrectCredentialsTest() {
        // login
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the first div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), defaultUsername)));
        // logout
        loginResultPage.logoutHttp();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getLoggedOutPageTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Tests the login procedure when the user is already logged in.
     */
    @Test
    @InSequence(7)
    public void loginBeingLoggedInTest() {
        // login
        login(userRegistrationUsername_1, userRegistrationPassword_1);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the first div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), userRegistrationUsername_1)));
        // login
        login(userRegistrationUsername_1, userRegistrationPassword_1);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is corrrect
        Assert.assertTrue(loginResultPage.getSecurityErrorPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the paragraphs text is the specified one
        Assert.assertTrue(loginResultPage.getParagraphText(0) != null
                && loginResultPage.getParagraphText(0).contains(loginResultPage.getAlreadyLoggedInMessage()));
        // logout
        loginResultPage.logoutHttp();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getLoggedOutPageTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Tests the restricted delorean page while being logged out.
     */
    @Test
    @InSequence(8)
    public void restrictedDeloreanPageLoggedOutTest() {
        // initialize the main page
        initializePageUrl();
        // wait until the page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // navigate to the restricted delorean page
        carRegistrationPage.navigateToRestrictedDeloreanPage();
        // wait until the page is loaded
        restrictedDeloreanResultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(restrictedDeloreanResultsPage.getSecurityErrorPageHeadingTitle().equals(
                restrictedDeloreanResultsPage.getHeadingTitle()));
    }

    /**
     * Tests the restricted admin page while being logged in with the credentials of the default existing user.
     */
    @Test
    @InSequence(9)
    public void restrictedDeloreanPageLoggedInTest() {
        // login as default user
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // navigate to the restricted delorean page
        loginResultPage.navigateToRestrictedDeloreanPage();
        // wait until the page is loaded
        restrictedDeloreanResultsPage.waitUntilPageIsLoaded();
        // assert that the page heading title is correct
        Assert.assertTrue(restrictedDeloreanResultsPage.getRestrictedPageHeadingTitle().equals(
                restrictedDeloreanResultsPage.getHeadingTitle()));
        // logout
        logout();
    }

    /**
     * Tests the restricted admin page while being logged out.
     */
    @Test
    @InSequence(10)
    public void restrictedAdminPageLoggedOutTest() {
        // initialize the main page
        initializePageUrl();
        // wait until the page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // navigate to the restricted delorean page
        carRegistrationPage.navigateToRestrictedAdminPage();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getSecurityErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Tests that the restricted admin page is accessible while being logged in with the credentials of the default existing
     * user. In addition it tests that the default user's name is included in the list of registered users.
     */
    @Test
    @InSequence(11)
    public void restrictedAdminPageLoggedInTest() {
        // login as default user
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // navigate to the restricted delorean page
        loginResultPage.navigateToRestrictedAdminPage();
        // wait until page is loaded
        restrictedAdminPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(restrictedAdminPage.getRestrictedAdminPageHeadingTitle()
                .equals(restrictedAdminPage.getHeadingTitle()));
        // assert that a registered user with the default username exists
        Assert.assertTrue(restrictedAdminPage.getRegisteredUser(defaultUsername) != null
                && defaultUsername.equals(restrictedAdminPage.getRegisteredUser(defaultUsername).getText()));
        // logout
        logout();
    }

    /**
     * Tests the registration procedure.
     */
    @Test
    @InSequence(12)
    public void restrictedAdminRegistrationTest() {
        // login as default user
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // navigate to the restricted delorean page
        loginResultPage.navigateToRestrictedAdminPage();
        // wait until page is loaded
        restrictedAdminPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(restrictedAdminPage.getRestrictedAdminPageHeadingTitle()
                .equals(restrictedAdminPage.getHeadingTitle()));
        // assert that a registered user with the default username exists
        Assert.assertTrue(restrictedAdminPage.getRegisteredUser(defaultUsername) != null
                && defaultUsername.equals(restrictedAdminPage.getRegisteredUser(defaultUsername).getText()));
        // register a new user
        restrictedAdminPage.registerHttp(userRegistrationUsername_2, userRegistrationPassword_2);
        // wait for the results page to load
        restrictedAdminResultsPage.waitUntilPageIsLoaded();
        // still the page is the restricted admin page
        // assert that a registered user with the new username
        Assert.assertTrue(restrictedAdminResultsPage.getRegisteredUser(defaultUsername) != null
                && userRegistrationUsername_2.equals(restrictedAdminResultsPage.getRegisteredUser(userRegistrationUsername_2)
                        .getText()));
        // logout
        logout();
        // try to login with the new user
        login(userRegistrationUsername_2, userRegistrationPassword_2);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // logout
        logout();
    }

    /**
     * Tests that the deletion functionality works properly.
     */
    @Test
    @InSequence(13)
    public void deleteRegisteredUser() {
        // register user
        registerUser(userRegistrationUsername_3, userRegistrationPassword_3);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the 2 div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), userRegistrationUsername_3)));
        // logout
        loginResultPage.logoutHttp();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getLoggedOutPageTitle().equals(resultsPage.getHeadingTitle()));
        // login as default user
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // navigate to the restricted delorean page
        loginResultPage.navigateToRestrictedAdminPage();
        // wait until page is loaded
        restrictedAdminPage.waitUntilPageIsLoaded();
        // assert that the heading titile is correct
        Assert.assertTrue(restrictedAdminPage.getRestrictedAdminPageHeadingTitle()
                .equals(restrictedAdminPage.getHeadingTitle()));
        // assert that a registered user with the username exists
        Assert.assertTrue(restrictedAdminPage.getRegisteredUser(userRegistrationUsername_3) != null
                && userRegistrationUsername_3.equals(restrictedAdminPage.getRegisteredUser(userRegistrationUsername_3)
                        .getText()));
        // select the default user
        restrictedAdminPage.clickRegisteredUserLink(userRegistrationUsername_3);
        // wait until the page is loaded
        restrictedAdminResultsPage.waitUntilPageIsLoaded();
        // assert that the heading titile is correct
        Assert.assertTrue(restrictedAdminResultsPage.getRestrictedAdminPageHeadingTitle().equals(
                restrictedAdminResultsPage.getHeadingTitle()));
        // delete the selected user
        restrictedAdminResultsPage.deleteUser();
        // wait until the page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading titile is correct
        Assert.assertTrue(restrictedAdminResultsPage.getRestrictedAdminPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // assert that the message which indicates that the user was removed appears
        Assert.assertTrue(resultsPage.getParagraphText(1) != null
                && resultsPage.getParagraphText(1).contains(resultsPage.getRestrictedAdminPageUserRemovedMsg()));
        // logout
        logout();
        // try to login as the user who was deleted and verify that the user cannot login
        login(userRegistrationUsername_3, userRegistrationPassword_3);
        // wait until the result page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getSecurityErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // try to login as another user
        login(defaultUsername, defaultPassword);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the 2 div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), defaultUsername)));
        // logout
        loginResultPage.logoutHttp();

    }

    /**
     * Tests that the registration of existing user fails.
     */
    @Test
    @InSequence(14)
    public void registerExistingUser() {
        // register user
        registerUser(userRegistrationUsername_4, userRegistrationPassword_4);
        // wait until the results page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the 2 div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1) != null
                && loginResultPage.getDivText(1).contains(
                        MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), userRegistrationUsername_4)));
        // logout
        loginResultPage.logoutHttp();
        // register user
        registerUser(userRegistrationUsername_4, userRegistrationPassword_4);
        // wait until the results page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getGeneralErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // assert that the user exist message appears
        // assert that the heading title is correct
        Assert.assertTrue(resultsPage.getParagraphText(0) != null
                && resultsPage.getParagraphText(0).contains(
                        MessageFormat.format(resultsPage.getUserExistsErrorMsg(), userRegistrationUsername_4)));
        // logout
        logout();
    }

    /**
     * Tests the Google Authenticator using a wrong OTP and asserts that one Quick Response Code is produced when the link is
     * clicked.
     */
    @Test
    @InSequence(15)
    public void googleAuthenticatorWrongOtpTest() {
        // login
        login(userRegistrationUsername_4, userRegistrationPassword_4);
        // wait until the page is loaded
        loginResultPage.waitUntilPageIsLoaded();
        // assert that heading title is correct
        Assert.assertTrue(loginResultPage.getLoggedInPageHeadingTitle().equals(loginResultPage.getHeadingTitle()));
        // assert that the 2 div contains a specified message
        Assert.assertTrue(loginResultPage.getDivText(1).contains(
                MessageFormat.format(loginResultPage.getLoggedInPageTailMessage(), userRegistrationUsername_4)));
        // generate Quick Response Code
        loginResultPage.generateQuickResponseCode();
        // assert that one canvas is created
        Assert.assertTrue(loginResultPage.getQrCodeList().size() == 1);
        // generate a second Quick Response Code
        loginResultPage.generateQuickResponseCode();
        // assert that one canvas exist
        Assert.assertTrue(loginResultPage.getQrCodeList().size() == 1);
        // authenticate otp
        loginResultPage.authenticateOtpHttp(wrongOTP);
        // wait until the results page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is corrrect
        Assert.assertTrue(resultsPage.getGeneralErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
        // assert that the paragraphs text is the specified one
        Assert.assertTrue(resultsPage.getGeneralErrorPageOtpMessage().equals(resultsPage.getParagraphText(0)));
        // logout
        logout();
    }

    /**
     * Tests that registration using empty username & password fails.
     */
    @Test
    @InSequence(16)
    public void registerUsingEmptyFields() {
        // login
        login(null, null);
        // wait until the results page is loaded
        resultsPage.waitUntilPageIsLoaded();
        // assert that the heading title is corrrect
        Assert.assertTrue(resultsPage.getSecurityErrorPageHeadingTitle().equals(resultsPage.getHeadingTitle()));
    }

    /**
     * Logouts the user when the logout link is not present on the page.
     */
    private void logout() {
        // logout
        navigateToURL(logoutPath);
    }

    /**
     * Performs the login steps.
     *
     * @param username The username.
     * @param password The password.
     */
    private void login(String username, String password) {
        // initialize main page
        initializePageUrl();
        // wait until page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // navigate to login page
        carRegistrationPage.navigateToLoginPage();
        // wait until the page is loaded
        loginPage.waitUntilPageIsLoaded();
        // assert that the heading titile is correct
        Assert.assertTrue(loginPage.getLoginPageHeadingTitle().equals(loginPage.getHeadingTitle()));
        // login using the credentials of the default user
        loginPage.loginHttp(username, password);
    }

    /**
     * Performs the registration steps.
     *
     * @param username The username.
     * @param password The password.
     */
    private void registerUser(String username, String password) {
        // initialize main page
        initializePageUrl();
        // wait until the page is loaded
        carRegistrationPage.waitUntilPageIsLoaded();
        // navigate to the register page
        carRegistrationPage.navigateToRegisterPageLink();
        // wait until the page is loaded
        userRegistrationPage.waitUntilPageIsLoaded();
        // assert that the heading title is correct
        Assert.assertTrue(userRegistrationPage.getUserRegistrationPageHeadingTitle().equals(
                userRegistrationPage.getHeadingTitle()));
        // register
        userRegistrationPage.registerHttp(username, password);
    }

    /* -- Data used to test the Aerogear-Controller-Demo begin -- */

    /**
     * The URL path for the logout action.
     */
    private final String logoutPath = "logout";

    /**
     * The color to be used in car registration.
     */
    private final String carRegistrationColor = "myColor";

    /**
     * The brand to be used in car registration.
     */
    private final String carRegistrationBrand = "myBrand";

    /**
     * A wrong username.
     */
    private final String loginPageWrongUsername = "Wrong_Username";

    /**
     * A wrong password.
     */
    private final String loginPageWrongPassword = "Wrong_Password";

    /**
     * Username to be used for registration.
     */
    private final String userRegistrationUsername_1 = "User1";

    /**
     * Password to be used for registration.
     */
    private final String userRegistrationPassword_1 = "Pass1";

    /**
     * Username to be used for registration.
     */
    private final String userRegistrationUsername_2 = "User2";

    /**
     * Password to be used for registration.
     */
    private final String userRegistrationPassword_2 = "Pass2";

    /**
     * Username to be used for registration.
     */
    private final String userRegistrationUsername_3 = "User3";

    /**
     * Password to be used for registration.
     */
    private final String userRegistrationPassword_3 = "Pass3";

    /**
     * Username to be used for registration.
     */
    private final String userRegistrationUsername_4 = "User4";

    /**
     * Password to be used for registration.
     */
    private final String userRegistrationPassword_4 = "Pass4";

    /**
     * Username for default existing user.
     */
    private final String defaultUsername = "john";

    /**
     * Password for default existing user.
     */
    private final String defaultPassword = "123";

    /**
     * A random wrong OTP.
     */
    private final String wrongOTP = "1234";

    /* -- Data used to test the Aerogear-Controller-Demo end -- */

}
