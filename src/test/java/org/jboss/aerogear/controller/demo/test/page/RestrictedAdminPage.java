/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.controller.demo.test.page;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import java.text.MessageFormat;

import org.jboss.aerogear.controller.demo.test.page.dto.NameValuePair;
import org.jboss.aerogear.controller.demo.test.page.fragment.FormContainer;
import org.jboss.arquillian.graphene.enricher.findby.ByJQuery;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents the restricted admin page.
 */
public class RestrictedAdminPage extends AerogearControllerDemoPage {

    /**
     * Locator for the form container.
     */
    @FindBy(className = "container")
    private FormContainer registerFormContainer;

    /**
     * Locator for the list of registered users.
     */
    @FindBy(jquery = "div ul")
    private WebElement registeredUsersUL;

    /**
     * The name attribute's value for the username field.
     */
    private static final String usernameFieldName = "user.loginName";

    /**
     * The name attribute's value for the password field.
     */
    private static final String passwordFieldName = "password";

    /**
     * Format for the href attribute value of the registered user links.
     */
    private static final String registeredUserLinkHrefFormat = "a[href=\"show/{0}\"]";

    /**
     * The heading title for the restricted admin page.
     */
    private static final String restrictedAdminPageHeadingTitle = "Restricted Admin page";

    /**
     * Gets the page heading title for the Restricted Admin page.
     * 
     * @return Page heading title
     */
    public String getRestrictedAdminPageHeadingTitle() {
        return restrictedAdminPageHeadingTitle;
    }

    /**
     * Waits until the login page is loaded.
     */
    @Override
    public void waitUntilPageIsLoaded() {
        registerFormContainer.waitUntilFormIsVisible();
    }

    /**
     * Fills the user registration form and submits it. The submission results in an Http request which is guarded.
     * 
     * @param username The username.
     * @param password The password.
     */
    public void registerHttp(String username, String password) {
        final NameValuePair[] nameValuePairs = { new NameValuePair(usernameFieldName, username),
                new NameValuePair(passwordFieldName, password) };
        registerFormContainer.fillFormAndSubmitHttp(nameValuePairs);
    }

    /**
     * Returns a {@link WebElement} which represents a link of registered user.
     * 
     * @param username The registered user's name.
     * @return a {@link WebElement}
     */
    public WebElement getRegisteredUser(String username) {
        return (registeredUsersUL != null) ? registeredUsersUL.findElement(ByJQuery.jquerySelector(MessageFormat.format(
                registeredUserLinkHrefFormat, username))) : null;
    }

    /**
     * Clicks the link of a registered user and guards the Http request which occurs as a result of the link click.
     * 
     * @param username The username which spoecifies the link which will be clicked.
     */
    public void clickRegisteredUserLink(String username) {
        guardHttp(getRegisteredUser(username)).click();
    }

}
