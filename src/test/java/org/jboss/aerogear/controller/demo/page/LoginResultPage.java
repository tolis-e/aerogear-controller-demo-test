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

package org.jboss.aerogear.controller.demo.page;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.jboss.arquillian.graphene.Graphene.guardXhr;

import java.util.List;

import org.jboss.aerogear.controller.demo.page.dto.NameValuePair;
import org.jboss.aerogear.controller.demo.page.fragment.FormContainer;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents the page which appears after the login procedure.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class LoginResultPage extends ResultsPage {

    /**
     * Locator for the form container.
     */
    @FindBy(className = "container")
    private FormContainer otpFormContainer;

    /**
     * Locator for the logout link.
     */
    @FindBy(jquery = "a[href=\"logout\"]")
    private WebElement logoutLink;

    /**
     * Locator for the Quick Response Code generator link.
     */
    @FindBy(id = "qrcode")
    private WebElement qrCodeGeneratorLink;

    /**
     * Locator for the Quick Response Codes.
     */
    @FindBy(jquery = "#qrcode-div canvas")
    private List<WebElement> qrCodeList;

    /**
     * Locator for the restricted admin page link.
     */
    @FindBy(jquery = "a[href=\"admin\"]")
    private WebElement restrictedAdminPageLink;

    /**
     * The name attribute's value for the OTP field.
     */
    private static final String otpFieldName = "aeroGearUser.otp";

    /**
     * The tail message which appears when a user has performed a successful login.
     */
    private final String loggedInPageTailMessage = "hello {0} to the authentication page!";

    /**
     * The message which appears when the user tries to login when he is already logged in.
     */
    private final String alreadyLoggedInMessage = "Already logged in";

    /**
     * The heading title for the logged in page.
     */
    private final String loggedInPageHeadingTitle = "Logged in";

    /**
     * The heading title for the login results security error page.
     */
    private final String securityErrorPageHeadingTitle = "Security error page";

    /**
     * Gets the heading title for the security error results page.
     * 
     * @return heading title
     */
    public String getSecurityErrorPageHeadingTitle() {
        return securityErrorPageHeadingTitle;
    }

    /**
     * Gets the heading title for the logged in page.
     * 
     * @return heading title
     */
    public String getLoggedInPageHeadingTitle() {
        return loggedInPageHeadingTitle;
    }

    /**
     * Gets the logged in page tail message.
     * 
     * @return tail msg
     */
    public String getLoggedInPageTailMessage() {
        return loggedInPageTailMessage;
    }

    /**
     * Gets the already logged in message.
     * 
     * @return already logged in msg
     */
    public String getAlreadyLoggedInMessage() {
        return alreadyLoggedInMessage;
    }

    /**
     * Locator for the restricted delorean page link.
     */
    @FindBy(jquery = "a[href=\"delorean\"]")
    private WebElement restrictedDeloreanPageLink;

    /**
     * Navigates to the restricted delorean page.
     */
    public void navigateToRestrictedDeloreanPage() {
        guardHttp(restrictedDeloreanPageLink).click();
    }

    /**
     * Navigates to the admin page.
     */
    public void navigateToRestrictedAdminPage() {
        guardHttp(restrictedAdminPageLink).click();
    }

    /**
     * Performs logout and guards the Http request which is produced as a result of the logout's link click.
     */
    public void logoutHttp() {
        guardHttp(logoutLink).click();
    }

    /**
     * Fills the OTP form and submits it. Guards the Http request which is produced as a result of the submit button click.
     * 
     * @param otpValue The otp value.
     */
    public void authenticateOtpHttp(String otpValue) {
        otpFormContainer.waitUntilFormIsVisible();
        final NameValuePair[] nameValuePairs = { new NameValuePair(otpFieldName, otpValue) };
        otpFormContainer.fillFormAndSubmitHttp(nameValuePairs);
    }

    /**
     * Generates a new Quick Response Code and guards the Xhr request.
     */
    public void generateQuickResponseCode() {
        guardXhr(qrCodeGeneratorLink).click();
    }

    /**
     * Gets the QR Codes.
     * 
     * @return A List of WebElements where each one of the WebElements represent the QR Code canvas HTML object.
     */
    public List<WebElement> getQrCodeList() {
        return qrCodeList;
    }
}
