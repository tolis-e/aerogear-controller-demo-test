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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents a common structure that the result pages of the Aerogear-Controller-Demo pages have.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class ResultsPage extends AerogearControllerDemoPage {

    /**
     * Locator for the divs.
     */
    @FindBy(jquery = "div[class=\"sixteen columns\"]")
    private List<WebElement> divs;

    /**
     * Locator for the paragraphs.
     */
    @FindBy(jquery = "div[class=\"sixteen columns\"] p")
    private List<WebElement> paragraphs;

    /**
     * Retrieves the paragraphs.
     * 
     * @return A {@link List} of {@link WebElement}
     */
    public List<WebElement> getParagraphs() {
        return paragraphs;
    }

    /**
     * Retrieves a specific paragraph given the index position.
     * 
     * @param index The index position.
     * @return The {@link WebElement} or null.
     */
    public WebElement getParagraph(int index) {
        return !CollectionUtils.isEmpty(paragraphs) && paragraphs.size() >= index ? paragraphs.get(index) : null;
    }

    /**
     * Gets the paragraph's text.
     * 
     * @param index The index position of the paragraph.
     * @return The paragraph's text.
     */
    public String getParagraphText(int index) {
        return getText(getParagraph(index));
    }

    /**
     * Retrieves the divs.
     * 
     * @return A {@link List} of {@link WebElement}
     */
    public List<WebElement> getDivs() {
        return divs;
    }

    /**
     * Retrieves a specific div given the index position.
     * 
     * @param index The index position.
     * @return The {@link WebElement} or null.
     */
    public WebElement getDiv(int index) {
        return !CollectionUtils.isEmpty(divs) && divs.size() >= index ? divs.get(index) : null;
    }

    /**
     * Gets the div's text.
     * 
     * @param index The index position of the div.
     * @return The paragraph's text.
     */
    public String getDivText(int index) {
        return getText(getDiv(index));
    }

    /**
     * Extracts the text from a {@link WebElement}
     * 
     * @param w The {@link WebElement}
     * @return The text of the passed {@link WebElement}.
     */
    public String getText(WebElement w) {
        return w != null ? w.getText() : null;
    }
}