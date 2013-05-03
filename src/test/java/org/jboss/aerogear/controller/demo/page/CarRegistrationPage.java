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

import org.jboss.aerogear.controller.demo.page.dto.NameValuePair;
import org.jboss.aerogear.controller.demo.page.fragment.FormContainer;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents the car registration page which is the home page of the app.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class CarRegistrationPage extends AerogearControllerDemoPage {

    /**
     * Locator for the form container.
     */
    @FindBy(className = "container")
    private FormContainer carRegistrationFormContainer;

    /**
     * The name attribute's value for the color field.
     */
    private static final String colorFieldName = "car.color";

    /**
     * The name attribute's value for the car field.
     */
    private static final String brandFieldName = "car.brand";

    /**
     * Locator for the restricted delorean page link.
     */
    @FindBy(jquery = "a[href=\"delorean\"]")
    private WebElement restrictedDeloreanPageLink;

    /**
     * Locator for the restricted admin page link.
     */
    @FindBy(jquery = "a[href=\"admin\"]")
    private WebElement restrictedAdminPageLink;

    /**
     * Locator for the login page link.
     */
    @FindBy(jquery = "a[href=\"login\"]")
    private WebElement loginPageLink;

    /**
     * Locator for the register page link.
     */
    @FindBy(jquery = "a[href=\"register\"]")
    private WebElement registerPageLink;

    /**
     * Locator for the error handling page link.
     */
    @FindBy(jquery = "a[href=\"throwException\"]")
    private WebElement errorHandlingPageLink;

    /**
     * The heading title for the car registration page.
     */
    private final String carRegistrationPageHeadingTitle = "Simple page";

    /**
     * The body message of the car registration result.
     */
    private final String carRegistrationPageBodyMessage = "hello, you just saved a car with the following characteristics:";

    /**
     * The tail message of the car registration result.
     */
    private final String carRegistrationTailMessageFormat = "the color is {0} and the brand is {1}";

    /**
     * Gets the tail message of the car registration page.
     * 
     * @return tail msg
     */
    public String getCarRegistrationTailMessageFormat() {
        return carRegistrationTailMessageFormat;
    }

    /**
     * Gets the body msg appeared on the car registration page.
     * 
     * @return body message
     */
    public String getCarRegistrationPageBodyMessage() {
        return carRegistrationPageBodyMessage;
    }

    /**
     * Waits until the car registration page is loaded.
     */
    @Override
    public void waitUntilPageIsLoaded() {
        carRegistrationFormContainer.waitUntilFormIsVisible();
    }

    /**
     * Performs a car registration. The submission results in an Http request.
     * 
     * @param carColor The car's color.
     * @param carBrand The car's brand.
     */
    public void registerHttp(String carColor, String carBrand) {
        final NameValuePair[] nameValuePairs = { new NameValuePair(colorFieldName, carColor),
                new NameValuePair(brandFieldName, carBrand) };
        carRegistrationFormContainer.fillFormAndSubmitHttp(nameValuePairs);
    }

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
     * Navigates to the login page.
     */
    public void navigateToLoginPage() {
        guardHttp(loginPageLink).click();
    }

    /**
     * Navigates to the register page link.
     */
    public void navigateToRegisterPageLink() {
        guardHttp(registerPageLink).click();
    }

    /**
     * Navigates to the error handling page.
     */
    public void navigateToErrorHandlingPage() {
        guardHttp(errorHandlingPageLink).click();
    }

    /**
     * Gets the page heading title of the Car registration page.
     * 
     * @return Page heading title.
     */
    public String getCarRegistrationPageHeadingTitle() {
        return carRegistrationPageHeadingTitle;
    }
}
