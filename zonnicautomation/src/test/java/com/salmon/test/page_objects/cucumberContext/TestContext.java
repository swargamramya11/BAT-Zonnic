package com.salmon.test.page_objects.cucumberContext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TestContext {
    ScenarioContext scenarioContext;
    PageObjectManager pageObjectManager;
}
